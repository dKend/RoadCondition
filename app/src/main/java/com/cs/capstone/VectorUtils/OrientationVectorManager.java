package com.cs.capstone.VectorUtils;

/**
 * Created by damia on 18-Feb-18.
 */

public class OrientationVectorManager {

    private MaskedMathVector gravity;
    private static final double defaultGravityValue = 9.81;
    private static final double defaultErrorValue = 0.01;
    //  cant determine if were driving via accelerometer since it only give acceleration values and a car isn't always accelerating while driving

    public OrientationVectorManager(MathVector gravity){
        this(gravity, defaultGravityValue, defaultErrorValue);
    }
    public OrientationVectorManager(MathVector gravity, double gravityValue){
        this(gravity, gravityValue, defaultErrorValue);
    }

    public OrientationVectorManager(MathVector gravity, double gravityValue, double errorValue){
        try{
            this.gravity = new MaskedMathVector(gravity, gravityValue, errorValue);
            this.gravity.calculateWeights();
        }catch(Exception e){
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
            System.exit(1);
        }
    }

    public OrientationVectorManager(float[] gravity) {
        this(gravity, defaultGravityValue, defaultErrorValue);
    }

    public OrientationVectorManager(float[] gravity, double gravityValue) {
        this(gravity, gravityValue, defaultErrorValue);
    }

    public OrientationVectorManager(float[] gravity, double gravityValue, double errorValue) {
        try{
            this.gravity = new MaskedMathVector(gravity, gravityValue, errorValue);
            this.gravity.calculateWeights();
        }catch(Exception e){
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
            System.exit(1);
        }

    }

    public MathVector getFilteredReading(MathVector reading){
		MathVector ret = null;
        if(reading != null){
            ret = reading.difference(gravity.getVector());                                      // reading = reading - gravity
            ret = ret.difference(ret.scale(gravity.getInverseMask().getMask()));                // reading = reading - ( invGravityMask * reading)
		}
		return ret;
    }

    public int updateOrientation(MathVector reading){
        int ret = -1;
        if(reading != null){
            if(isOrientationChanged(reading)){
                gravity = new MaskedMathVector(reading, gravity.getTarget(), gravity.getError());
                ret = 0;
            }else{
                ret = 1;
            }
        }
        return ret;
    }

    public boolean isOrientationChanged(MathVector reading){
        boolean ret = false;
        if(reading != null){
            MaskedMathVector m = new MaskedMathVector(reading, gravity.getTarget(), gravity.getError());
            m.calculateWeights();
            if(areMasksIdentical(m)){
                ret = !(gravity.getVector().equals(reading));
            }else{
               ret = true;
            }
        }
        return ret;
    }

    //this should be in MaskedMathVector but im lazy
    public boolean areMasksIdentical(MaskedMathVector reading){
        boolean ret = false;
        if(reading != null){
            ret = gravity.getMask().equals(reading.getMask());
        }
        return ret;
    }

    public boolean isVectorEvent(MathVector vector){
        boolean ret = false;
        if(vector != null){
            MaskedMathVector test = new MaskedMathVector(vector, gravity.getMask(), -1, gravity.getError());
            double min = gravity.getTarget() - gravity.getError();
            double max = gravity.getTarget() + gravity.getError();
            ret = (test.getMaskedMagnitude() < min) || (test.getMaskedMagnitude() > max);
        }
        return ret;
    }

    public MaskedMathVector getGravity() {
        return gravity;
    }
}
