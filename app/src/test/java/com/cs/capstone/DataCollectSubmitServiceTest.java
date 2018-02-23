package com.cs.capstone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by damia on 09-Feb-18.
 */
public class DataCollectSubmitServiceTest {
    private DataCollectSubmitService test;
    private DataCollectSubmitService testHasPolledZeros;
    private DataCollectSubmitService testHasPolledAllSame;
    private DataCollectSubmitService testXCloseToG;
    private DataCollectSubmitService testYCloseToG;
    private DataCollectSubmitService testZCloseToG;
    private float[][] invalidArraySizes;
    private float[][] validArrays;
    private float[][] deltas;
    @Before
    public void setUp() throws Exception {
        test = new DataCollectSubmitService();
        testHasPolledZeros = new DataCollectSubmitService();
        testHasPolledAllSame = new DataCollectSubmitService();
        testXCloseToG = new DataCollectSubmitService();
        testYCloseToG = new DataCollectSubmitService();
        testZCloseToG = new DataCollectSubmitService();

        invalidArraySizes = new float[][]{
                {},
                {26},
                {18, 54},
                {92, 206, 11, 12},
                {10, 23, 500, 0, 99, 256, 98}
        };
        validArrays = new float[][]{
                {0, 0, 0},
                {26, 5, 12},
                {8, 11, 12},
                {13, 10, 11},
                {13, 26, -9},
                {11, 11, 11}
        };
        deltas = new float[][]{
                {26, 5, 12},
                {-18, 6, 0},
                {5, -1, -1},
                {0, 16, -20},
                {-2, -15, 20}
        };
		
        for(int i=0; i<30; i++){
            testHasPolledAllSame.handleCalibrationValues(validArrays[1]);
        }
        for(int i=0; i<30; i++){
            testHasPolledZeros.handleCalibrationValues(validArrays[5]);
        }
        for(int i=0; i<30; i++){
            testXCloseToG.handleCalibrationValues(validArrays[2]);
        }
        for(int i=0; i<30; i++){
            testYCloseToG.handleCalibrationValues(validArrays[3]);
        }
        for(int i=0; i<30; i++){
            testZCloseToG.handleCalibrationValues(validArrays[4]);
        }
    }

    @After
    public void tearDown() throws Exception {
        test = null;
        testHasPolledZeros = null;
        testHasPolledAllSame = null;
        testXCloseToG = null;
        testYCloseToG = null;
        testZCloseToG = null;
        invalidArraySizes = null;
        validArrays = null;
        deltas = null;
    }
    @Test
    public void init() throws Exception {

    }

    @Test
    public void calibrate() throws Exception {

    }

    @Test
    public void handleCalibrationValues() throws Exception {
        //test arrays of improper size
        assertTrue("handleCalibrationValues doesn't fail on empty array.", test.handleCalibrationValues(invalidArraySizes[0]) == -1);
        assertTrue("handleCalibrationValues doesn't fail on one element array.", test.handleCalibrationValues(invalidArraySizes[1]) == -1);
        assertTrue("handleCalibrationValues doesn't fail on two element array.", test.handleCalibrationValues(invalidArraySizes[2]) == -1);
        assertTrue("handleCalibrationValues doesn't fail on four element array.", test.handleCalibrationValues(invalidArraySizes[3]) == -1);
        assertTrue("handleCalibrationValues doesn't fail on n element array.", test.handleCalibrationValues(invalidArraySizes[4]) == -1);
        //test properly sized array
        assertTrue("handleCalibrationValues fails when given an array of proper size.", test.handleCalibrationValues(validArrays[1]) == 0);
        for(int i=test.getCalibrationNextIndex(); i < test.getPollStopAfter(); i++){
            assertTrue("handleCalibrationValues fails when given an array of proper size and less than max readings have occured.", test.handleCalibrationValues(validArrays[1]) == 0);
        }
        assertTrue("handeCalibreationValues doesn't return 1 when the reading array is full.",test.handleCalibrationValues(validArrays[1]) == 1);
    }
	
    @Test
    public void handleNewReading() throws Exception {
        assertTrue(test.handleNewReading(null) == -1);
        assertTrue(test.handleNewReading(invalidArraySizes[0]) == -1);
        assertTrue(test.handleNewReading(invalidArraySizes[1]) == -1);
        assertTrue(test.handleNewReading(invalidArraySizes[2]) == -1);
        assertTrue(test.handleNewReading(invalidArraySizes[3]) == -1);
        assertTrue(test.handleNewReading(invalidArraySizes[4]) == -1);
        assertTrue(test.handleNewReading(validArrays[1]) == 0);
        assertTrue(test.handleNewReading(validArrays[2]) == 0);
        assertTrue(test.handleNewReading(validArrays[3]) == 0);
        assertTrue(test.handleNewReading(validArrays[4]) == 0);
        assertTrue(test.handleNewReading(validArrays[5]) == 0);
    }

    @Test
    public void isDriving() throws Exception {
    
	}
}