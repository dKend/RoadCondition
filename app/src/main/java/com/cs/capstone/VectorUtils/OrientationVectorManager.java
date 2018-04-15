package com.cs.capstone.VectorUtils;

/**
 * Created by damian on 18-Feb-18.
 */

public class OrientationVectorManager {

    private MaskedMathVector gravity;
    private static final double defaultGravityValue = 9.81;
    private static final double defaultErrorValue = 0.01;
    //  cant determine if we're driving via accelerometer since it only gives acceleration values and a car isn't always accelerating while driving

    /**
     * Constructor for OrientationVectorManager with only gravity vector defined
     * @param gravity MathVector
     */
    public OrientationVectorManager(MathVector gravity){
        this(gravity, defaultGravityValue, defaultErrorValue);
    }

    /**
     * Constructor for OrientationVectorManager wth gravity vector and gravityValue defined.
     * @param gravity MathVector.
     * @param gravityValue double.
     */
    public OrientationVectorManager(MathVector gravity, double gravityValue){
        this(gravity, gravityValue, defaultErrorValue);
    }

    /**
     * Constructor for OrientationVectorManager with gravity vector, gravityValue and ErrorValue defined.
     * @param gravity MathVector
     * @param gravityValue double
     * @param errorValue double
     */
    public OrientationVectorManager(MathVector gravity, double gravityValue, double errorValue){
        try{
            this.gravity = new MaskedMathVector(gravity, gravityValue, errorValue);
            this.gravity.calculateWeights();
        }catch(Exception e){
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
            System.exit(1);
        }
    }

    /**
     * Constructor. same as above with float array instead.
     * @param gravity float[]
     */
    public OrientationVectorManager(float[] gravity) {
        this(gravity, defaultGravityValue, defaultErrorValue);
    }

    /**
     * Above.
     * @param gravity float[]
     * @param gravityValue double
     */
    public OrientationVectorManager(float[] gravity, double gravityValue) {
        this(gravity, gravityValue, defaultErrorValue);
    }

    /**
     * Above.
     * @param gravity float[]
     * @param gravityValue double
     * @param errorValue double
     */
    public OrientationVectorManager(float[] gravity, double gravityValue, double errorValue) {
        try{
            this.gravity = new MaskedMathVector(gravity, gravityValue, errorValue);
            this.gravity.calculateWeights();
        }catch(Exception e){
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
            System.exit(1);
        }

    }

    /**
     * Computes the value of reading minus the gravity vector.
     * @param reading  MathVector
     * @return MathVector
     */
    public MathVector getFilteredReading(MathVector reading){
		MathVector ret = null;
        if(reading != null){
            ret = reading.difference(gravity.getVector());                                      // reading = reading - gravity
            ret = ret.difference(ret.scale(gravity.getInverseMask().getMask()));                // reading = reading - ( invGravityMask * reading)
		}
		return ret;
    }

    /**
     * Updates the value of gravity based on the given reading.
     * @param reading MathVector
     * @return int; 0 on success, 1 when the orientation hasnt changed, -1 on failure.
     */
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

    /**
     * Checks if the given reading results in the same mask when placed inside a copy of the gravity
     * vector.
     * @param reading MathVector.
     * @return boolean; true on orientation changed, false otherwise.
     */
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
