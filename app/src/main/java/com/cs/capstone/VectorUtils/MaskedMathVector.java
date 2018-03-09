package com.cs.capstone.VectorUtils;

/**
 * Created by damian on 15-Feb-18.
 */

/**
 * @author Damian
 * @version 1.0
 * @since 1.0
 */
public class MaskedMathVector {
    private MathVector mask;
    private MathVector vector;  //  base vector
    private double target;
    private double error;

    /**
     * Constructor for the MaskedMathVector class.
     * <p>
     *     Constructor for the MaskedMathVector class. Creates a basic MaskedMathVector with a mask
     *     of all negative ones, a blank vector, a target of -1 and an error of 0.5. The negative
     *     mask denotes that the mask has yet to be calculated/defined and the negative target
     *     denotes that the mask has no target. The target is not required if the mask is not going to
     *     be calculated using the calculateWeights method, but is required for this method to
     *     function properly.
     * </p>
     */
    public MaskedMathVector(){
        mask = new MathVector(-1, -1, -1);
        vector = new MathVector();
        target = -1;    //  indicates that the target has not been set yet, the value calculated from the vectorField will always be positive
        error = 0.5;    //  the amount of allowed difference from the target value for a magnitude to be considered a possible solution
    }


    /**
     * Constructor for the MaskedMathVector class which takes a MathVector, and 2 doubles.
     * <p>
     *     Creates a new MaskedMathVector with the provided paramaters as the value of its elements.
     *     This constructor sets the mask to a blank (all zeros) MathVector since it is assumed that
     *     when using this version of the constructor, the calculateWeights method will be called
     *     after constructon.
     * </p>
     * @param vector, an instance of the MathVector class, the primary piece of data of the class.
     * @param target, target magnitude used by calculateWeights to create a mask.
     * @param error, an error range used by the calculateWeights method to approximate the mask.
     */
    public MaskedMathVector(MathVector vector, double target, double error){
        this.vector = vector;
        this.target = target;
        this.error = error;
        mask = new MathVector();
    }

    /**
     * Constructor for the MaskedMathVector class which defines all elements of the class.
     * <p>
     *     Allows for the creation of a MaskedMathVector with entirely user-defined values. This
     *     version assumes that the calculateWeights method will not be used. If the
     *     calculateWeights method is used, it may produce a mask wich is different from the one
     *     defined by the user at creation.
     * </p>
     * @param vector, instance of the MathVector class, the primary piece of data of the class.
     * @param mask, instance of the MathVector class, acts to hide/scale the values of the vector.
     * @param target, target magnitude used by calculateWeights to create a mask.
     * @param error, an error range used by the calculateWeights method to approximate the mask.
     */
    public MaskedMathVector(MathVector vector, MathVector mask, double target, double error){
        this.vector = vector;
        this.mask = mask;
        this.target = target;
        this.error = error;
    }

    /**
     * Constructor for MaskedMathVector, Identical to the version which takes a MathVector and two
     * doubles except that it takes a three element array of floats instead of a MathVector.
     * @param vectorData
     * @param target
     * @param error
     * @throws Exception, See MathVector(float[] data) constructor.
     */
    public MaskedMathVector(float[] vectorData, double target, double error) throws Exception{
        vector = new MathVector(vectorData);
        mask = new MathVector();
        target = Math.abs(target);
        error = Math.abs(error);
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

    public float getX(){
        return vector.getX();
    }

    public float getY(){
        return vector.getY();
    }

    public float getZ(){
        return vector.getZ();
    }

    /**
     * Calculates the the mask for the given vector and target.
     * <p>
     *     Using the error, vector, and target elements, calculateWeights finds which combination(s)
     *     of axes provide a magnitude value which is greater than or equal to target minus
     *     error and less than or equal to target plus error. When the axis combination is found, a
     *     mask is created. The value of the mask's axes is 1 for elements which are part of the
     *     combination and 0 for the elements which are not part of the combination. For example,
     *     let the combination found be x and z, for this combination the resulting mask would be
     *     (1, 0, 1). In this instance, since the mask was found to be (1, 0, 1), this means that
     *     magnitude of vector.scale(mask) is within the range defined earlier in this description.
     * </p>
     * <p>
     *     This method uses brute force to find the combination of axes since there are only 7
     *     possible solutions. If the solution is not found, then the mask is set to (1, 1, 1).
     * </p>
     * @return The new mask vector that is found. The mask element is set to an equal copy of ret.
     */
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

    /**
     * Creates a MaskedMathVector with identical vector and error values, but has a target of
     * negative one and a mask which is the inverse of the calling instance's mask.
     * <p>
     *     Creates a copy of the calling MaskedMathVector with a target of negative one and the
     *     inverse of its mask. For example, if the calling MaskedMathVector has a mask of
     *     (1, 0, 0), then its inverse will have all of the same values except for its mask which
     *     will be  (0, 1, 1) and its target which will be negative one. The target is set to
     *     negative one since the magnitude of the vector.scale(mask) is not guaranteed to be a
     *     value within the range defined in the calculateWeights method.
     * </p>
     * @return
     */
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

    /**
     * Creates a MathVector which is the vector scaled by the mask vector.
     * @return vector with mask applied to it.
     */
    public MathVector filterVector() {
        MathVector ret = vector.scale(mask);
        return ret;
    }

    /**
     * Calculates the magnitude of the result of filterVector
     * @return the magnitude of the masked vector.
     */
    public double getMaskedMagnitude(){
        return vector.scale(mask).getMagnitude();
    }

    /**
     * Determines if two MaskedMathVectors are equivalent by comparing their elements.
     * @param other, MaskedMathVector to compare to the calling MaskedMathVector.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other){
        boolean ret = false;
        if((other != null) && (other instanceof MaskedMathVector)){
            MaskedMathVector o = (MaskedMathVector) other;
            ret = (target == o.getTarget()) && (error == o.getError()) && mask.equals(o.getMask()) && vector.equals(o.getVector());
        }
        return ret;
    }

}
