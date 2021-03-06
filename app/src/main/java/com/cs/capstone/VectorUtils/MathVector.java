package com.cs.capstone.VectorUtils;

import android.support.annotation.NonNull;
import java.lang.Exception;

/**
* Created by damian on 14-Feb-18.
*/

/**
 * @author Damian Kendzior
 * @version 1.0
 * @since 1.0
 */
public class MathVector {

	private float x;
	private float y;
	private float z;

	/**
	 * Constructor for the MathVector class.
	 * <p>
	 *     This version of the constructor is meant to take the data from a sensor reading directly.
	 * </p>
	 * @param data, A three element array  where data[0] is x, data[1] is y, and data[2] is z.
	 * @throws Exception, if the array does not have a length of three.
	 */
	public MathVector(float[] data) throws Exception{
		if((data != null) && (data.length == 3)){
			x = data[0];
			y = data[1];
			z = data[2];
		}else{
			throw new Exception("Expected a float array of exactly length 3, actual length :" + data.length + ".");
		}

	}

	/**
	 * Constructor for the MathVector class which takes three floats instead of an array.
	 * @param x, float x value for the new MathVector.
	 * @param y, float y value for the new MathVector.
	 * @param z, float z value for the new MathVector.
	 */
	public MathVector(float x, float y, float z){
		//	This cant call the other constructor since the call has to be first line for some reason and we cant declare an array in a method body
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MathVector() {
		this(0, 0, 0);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setZ(float z) {
		this.z = z;
	}

	/**
	 *
	 * Calculates the vector's magnitude.
	 * <p>
	 *     Uses the formula " m = ( x^2 + y^2 + z^2 )^(-2) ", and returning 'm' as the result.
	 * </p>
	 * @return	The magnitude of the vector, i.e. the result of ( x^2 + y^2 + z^2 )^(-2).
	 *
	 */
	public double getMagnitude() {
		//calculate the vector magnitude
		double ret = 0;
		/*
			calculate the value of a1^2, a2^2 and a3^2
			where a1 == x, a2 == y and a3 == z
		*/
		double a1 = Math.pow(x, 2);
		double a2 = Math.pow(y, 2);
		double a3 = Math.pow(z, 2);
		/*
			now we calculate the sum of a1, a2 and a3
		*/
		ret = a1 + a2 + a3;
		/*
			lastly, we calculate the square root of the sum
		*/
		ret = Math.sqrt(ret);
		return ret;
	}

	/**
	 * Calcuates the sum of two vectors.
	 * <p>
	 *     Sum calculates the sum of the vectors this and other and places the result in a new MathVector. The derived MathVector is then returned as the result of the addition.
	 * </p>
	 * <p>
	 *     The formula used by sum is defined as: 'ret = <this.x + other.x, this.y + other.y, this.z + other.z>' where a vector is written in the format: <x,y,z>
	 * </p>
	 * @param other, an instance of the MathVector class.
	 * @return A new MathVector which is the result of summing the corresponding elements of this and other.
	 */
	public MathVector sum(MathVector other){
		/*
			calculate the sum of two MathVectors and return as a new MathVector
			or null if other is null.
		*/
		MathVector ret = null;
		if(other != null){
			ret = new MathVector(x + other.getX(), y + other.getY(), z + other.getZ());
		}
		return ret;
	}

	/**
	 * Static version of the sum method, see the non-static version for more details.
	 * @param self, static counterpart of 'this'
	 * @param other, static counterpart of 'other'
	 * @return A new MathVector which is the result of summing the corresponding elements of this and other.
	 */
	public static MathVector sum(MathVector self, MathVector other){
		MathVector ret = null;
		if(self != null && other != null){
			ret = self.sum(other);
		}
		return ret;
	}

	/**
	 * Calculates the vector resulting from multiplying together the corresponding elements of two vectors.
	 * <p>
	 *     Calculates the product of the elements of two MathVectors.
	 *     Given two MathVectors v1(x1, y1, z1) and v2(x2, y2, z2),
	 *     we find a vector v3(x3, y3, z3) such that:
	 *     		x3 = x1*x2
	 *     		y3 = y1*y2
	 *     		z3 = z1*z2
	 * </p>
	 * @param other, instance of the MathVector class
	 * @return A new MathVector which is the result of multiplying together the corresponding elements of two MathVectors.
	 */
	public MathVector scale(MathVector other){
		/*
			Calculate the product of the fields of this multiplied by the fields of other.
			The result is returned as a new MathVector or null if other is false.
		*/
		MathVector ret = null;
		if(other != null){
			ret = new MathVector(x*other.getX(), y*other.getY(), z*other.getZ());
		}
		return ret;
	}

	/**
	 * Calculates the result of a MathVector and a scalar.
	 * <p>
	 *     Given a vector 'v' and a scalar 's', scalarProduct calculates the value of a new vector 'r' which is denoted as:
	 *     	Let v(x,y,z), and s be a valid float value,
	 *     		r(s*x, s*y, s*z)
	 * </p>
	 * @param scalar, a float to multiply each element of this by.
	 * @return	A new MathVector whiich is the result of multiplying scalar by the elements of this.
	 */
	public MathVector scalarProduct(float scalar){
		return new MathVector(x*scalar, y*scalar, z*scalar);
	}

	/**
	 * Calculates the result of the calling vector minus another vector.
	 * <p>
	 *     Calculates the sum of the calling vector and the scalar product of the other vector and -1.
	 * </p>
	 * @param other, instance of the MathVector class.
	 * @return A new MathVector which is the result of the calling vector minus another vector (other).
	 */
	public MathVector difference(MathVector other){
		/*
			calculate the difference of two MathVectors and return as a new MathVector
			or null if other is null.
		*/
		MathVector ret = null;
		if(other != null){
			ret = sum(other.scalarProduct(-1));
		}
		return ret;
	}

	/**
	 * Compares the elements of the calling vector and another vector to determine if they are equivalent.
	 * <p>
	 *     Two vectors v1 and v2 are equivalent if and only if all of their corresponding elements are equivalent. This occurs when if x1 is equal to x2, y1 is equal to y2, z1 is equal to z2. This method overrides the Object class's equals method.
	 * </p>
	 * @param other, instance of the MathVector class.
	 * @return True if the two MathVectors are equivalent and false otherwise.
	 */
	@Override
	public boolean equals(Object other){
		/*
			compare the fields of two MathVectors
		*/
		boolean ret = false;
		if((other != null) && (other instanceof MathVector)){
			MathVector o = (MathVector) other;
			ret = (x == o.getX()) && (y == o.getY()) && (z == o.getZ());
		}
		return ret;
	}

	/*
		These functions generate the unit vector i, j, and k where:
			i = <1, 0, 0>
			j = <0, 1, 0>
			k = <0, 0, 1>
	*/

	/**
	 * Creates a new MathVector which is the unit vector I.
	 * <p>
	 *     I is defined as (1, 0, 0).
	 * </p>
	 * @return Unit vector I.
	 */
	@NonNull
	public static MathVector createI(){
		return new MathVector(1,0, 0);
	}

	/**
	 * Creates a new MathVector which is the unit vector J.
	 * <p>
	 *     J is defined as (0, 1, 0).
	 * </p>
	 * @return Unit vector J.
	 */
	@NonNull
	public static MathVector createJ(){
		return new MathVector(0, 1, 0);
	}

	/**
	 * Creates a new MathVecotr which is the unit vector K.
	 * <p>
	 *     K is defined as (0, 0, 1).
	 * </p>
	 * @return Unit vector K.
	 */
	@NonNull
	public static MathVector createK(){
		return new MathVector(0, 0, 1);
	}

	/**
	 * Creates a string from the x y and z elements of the calling vector
	 * @return A string of the format "<x: this.x, y: this.y, z: this.z>"
	 */
	@Override
	public String toString(){
		return "<x: "+x+", y: "+y+", z: "+z+">";
	}
}