package com.cs.capstone.VectorUtils;

/**
 * Created by damian on 15-Feb-18.
 */

public class MaskedMathVector {
    private MathVector mask;
    private MathVector vector;  //  base vector
    private double target;
    private double error;

    public MaskedMathVector(){
        mask = new MathVector(-1, -1, -1);
        vector = new MathVector();
        target = -1;    //  indicates that the target has not been set yet, the value calculated from the vectorField will always be positive
        error = 0.5;    //  the amount of allowed difference from the target value for a magnitude to be considered a possible solution
    }



    public MaskedMathVector(MathVector vector, double target, double error){
        this.vector = vector;
        this.target = target;
        this.error = error;
        mask = new MathVector();
    }

    public MaskedMathVector(MathVector vector, MathVector mask, double target, double error){
        this.vector = vector;
        this.mask = mask;
        this.target = target;
        this.error = error;
    }

    public MaskedMathVector(float[] vectorData, double target, double error) throws Exception{
        vector = new MathVector(vectorData);
        mask = new MathVector();
        target = Math.abs(target);
        error = Math.abs(error);
    }

    public MathVector calculateWeights(){
        /*
            find which axes are used in the target vector magnitude
            - we will test all cases since there are only 7 of them
            - if more than one case is found to be within range of
            the target value then:
                - if all of them are found to be possible solutions,
                a solution will be chosen where the axis with the
                lowest value isnt included, then we will check the
                - if
        */
        MathVector ret = null;
        MathVector[] cases = {
                vector.scale(MathVector.createI()),
                vector.scale(MathVector.createJ()),
                vector.scale(MathVector.createK()),
                new MathVector(vector.getX(), vector.getY(), 0),
                new MathVector(vector.getX(), 0, vector.getZ()),
                new MathVector(0, vector.getY(), vector.getZ()),
                vector
        };
        boolean[] isCandidate = {
                false,
                false,
                false,
                false,
                false,
                false,
                false
        };

        //  phase one, find the closest combinations
        int indexIsEqualToTarget = -1;
        for(int i = 0; i < 7; i++) {
            double max = target + error;
            double min = target - error;
            double mag = cases[i].getMagnitude();
            if(mag == target){
                indexIsEqualToTarget = i;
                break;
            }
            isCandidate[i] = (mag == target)||(mag >= min) && (mag <= max);
        }

        //  if it hasnt been determined yet, find the closest case from the candidates
        if(indexIsEqualToTarget == -1){
            for(int i = 0;i < 7;i++){
                if(isCandidate[i] == true){
                    if(indexIsEqualToTarget == -1){
                        indexIsEqualToTarget = i;
                    }else if(cases[i].getMagnitude() < cases[indexIsEqualToTarget].getMagnitude()){
                        indexIsEqualToTarget = i;
                    }
                }
            }
        }

        //  this is ran after indexIsEqualToTargetHasBeenDetermined
        switch(indexIsEqualToTarget){
            case 0:
                mask = MathVector.createI();
                ret = MathVector.createI();
                break;
            case 1:
                mask = MathVector.createJ();
                ret = MathVector.createJ();
                break;
            case 2:
                mask = MathVector.createK();
                ret = MathVector.createK();
                break;
            case 3:
                mask = MathVector.createI().sum(MathVector.createJ());
                ret = MathVector.createI().sum(MathVector.createJ());
                break;
            case 4:
                mask = MathVector.createI().sum(MathVector.createK());
                ret = MathVector.createI().sum(MathVector.createK());
                break;
            case 5:
                mask = MathVector.createJ().sum(MathVector.createK());
                ret = MathVector.createJ().sum(MathVector.createK());
                break;
            case 6:
                mask = MathVector.createI().sum(MathVector.createJ()).sum(MathVector.createK());
                ret = MathVector.createI().sum(MathVector.createJ()).sum(MathVector.createK());
                break;
            default:
                mask = new MathVector();
                ret = new MathVector();
                break;

        }

        return ret;
    }

    public MaskedMathVector getInverseMask() {
        MaskedMathVector ret = null;
        MathVector I = MathVector.createI();
        MathVector J = MathVector.createJ();
        MathVector K = MathVector.createK();
        MathVector IJ = I.sum(J);
        MathVector IK = I.sum(K);
        MathVector JK = J.sum(K);
        MathVector IJK = JK.sum(I);
        if(mask.equals(I)){
            ret = new MaskedMathVector(vector, JK, -1, error);
        }else if(mask.equals(J)){
            ret = new MaskedMathVector(vector, IK, -1, error);
        }else if(mask.equals(K)){
            ret = new MaskedMathVector(vector, IJ, -1, error);
        }else if(mask.equals(IJ)){
            ret = new MaskedMathVector(vector, K, -1, error);
        }else if(mask.equals(IK)){
            ret = new MaskedMathVector(vector, J, -1, error);
        }else if(mask.equals(JK)){
            ret = new MaskedMathVector(vector, I, -1, error);
        }else if(mask.equals(IJK)){
            ret = new MaskedMathVector(vector, new MathVector(), -1, error);
        }else if(mask.equals(new MathVector())){
            ret = new MaskedMathVector(vector, IJK, -1, error);
        }
        return ret;
    }

    public MathVector filterVector() {
        MathVector ret = vector.scale(mask);
        return ret;
    }

    public double getMaskedMagnitude(){
        return vector.scale(mask).getMagnitude();
    }

    public MathVector getMask() {
        return mask;
    }

    public MathVector getVector() {
        return vector;
    }

    public double getTarget() {
        return target;
    }

    public double getError() {
        return error;
    }

    public void setVector(MathVector vector) {
        this.vector = vector;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public void setError(double error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object other){
        boolean ret = false;
        if((other != null) && (other instanceof MaskedMathVector)){
            MaskedMathVector o = (MaskedMathVector) other;
            ret = (target == o.getTarget()) && (error == o.getError()) && mask.equals(o.getMask()) && vector.equals(o.getVector());
        }
        return ret;
    }

    public float getX(){
        return vector.getX();
    }

    public float getY(){
        return vector.getY();
    }

    public float getZ(){
        return vector.getZ();
    }
}
