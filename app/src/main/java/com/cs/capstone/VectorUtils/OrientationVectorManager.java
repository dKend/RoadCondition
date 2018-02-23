package com.cs.capstone.VectorUtils;

/**
 * Created by damia on 18-Feb-18.
 */

public class OrientationVectorManager {

    private MaskedMathVector gravity;
	private MaskedMathVector reading;
    public static final double defaultGravityValue = 9.81;
    public static final double defaultErrorValue = 0.01;
    //  cant determine if were driving via accelerometer since it only give acceleration values and a car isn't always accelerating while driving

    public OrientationVectorManager(MathVector gravity, MathVector reading){
        this(gravity, reading, defaultGravityValue, defaultErrorValue);
    }
    public OrientationVectorManager(MathVector gravity, MathVector reading, double gravityValue){
        this(gravity, reading, gravityValue, defaultErrorValue);
    }

    public OrientationVectorManager(MathVector gravity, MathVector reading, double gravityValue, double errorValue){
        try{
            this.gravity = new MaskedMathVector(gravity, gravityValue, errorValue);
            this.gravity.calculateWeights();
			this.reading = new MaskedMathVector(reading, -1, 0.0);
        }catch(Exception e){
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
            System.exit(1);
        }
    }

    public OrientationVectorManager(float[] gravity, float[] reading) {
        this(gravity, reading, defaultGravityValue, defaultErrorValue);
    }

    public OrientationVectorManager(float[] gravity, float[] reading, double gravityValue) {
        this(base, gravityValue, defaultErrorValue);
    }

    public OrientationVectorManager(float[] gravity, float[] reading, double gravityValue, double errorValue) {
        try{
            gravity = new MaskedMathVector(base, gravityValue, errorValue);
            gravity.calculateWeights();
        }catch(Exception e){
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
            System.exit(1);
        }

    }

    public MathVector getDelta(MathVector reading){
		if(gravity != null && reading != null){
			MaskedMathVector g = new MaskedMathVector(gravity, 9.81, 0.01);
			gravity.calculateWeights();
			MathVector diff = reading.difference(gravity);
			MathVector inverseGravityMask = g.getInverseMask().getMask();
			diff = diff.difference(reading.scale(inverseGravityMask));
		}
		return diff;
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


}
