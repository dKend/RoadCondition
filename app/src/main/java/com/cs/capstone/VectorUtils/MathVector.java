package com.cs.capstone.VectorUtils;

import android.support.annotation.NonNull;
import java.lang.Exception;

/**
* Created by damian on 14-Feb-18.
*/
 
public class MathVector {

	private float x;
	private float y;
	private float z;

	public MathVector(float[] data) throws Exception{
		if((data != null) && (data.length == 3)){
			x = data[0];
			y = data[1];
			z = data[2];
		}else{
			throw new Exception("Expected a float array of exactly length 3, actual length :" + data.length + ".");
		}

	}

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

	public static MathVector sum(MathVector self, MathVector other){
		MathVector ret = null;
		if(self != null && other != null){
			ret = self.sum(other);
		}
		return ret;
	}

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

	public MathVector scalarProduct(float scalar){
		return new MathVector(x*scalar, y*scalar, z*scalar);
	}

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
	@NonNull
	public static MathVector createI(){
		return new MathVector(1,0, 0);
	}

	@NonNull
	public static MathVector createJ(){
		return new MathVector(0, 1, 0);
	}

	@NonNull
	public static MathVector createK(){
		return new MathVector(0, 0, 1);
	}

	@Override
	public String toString(){
		return "<x: "+x+", y: "+y+", z: "+z+">";
	}
}