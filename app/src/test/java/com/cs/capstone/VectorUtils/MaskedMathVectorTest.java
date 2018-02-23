package com.cs.capstone.VectorUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by damia on 16-Feb-18.
 */
public class MaskedMathVectorTest {

    private MaskedMathVector wtestI;
    private MaskedMathVector wtestJ;
    private MaskedMathVector wtestK;
    private MaskedMathVector wtestIJ;
    private MaskedMathVector wtestIK;
    private MaskedMathVector wtestJK;
    private MaskedMathVector wtestIJK;
    private MaskedMathVector[] wtestRandom0;

    private MathVector testI;
    private MathVector testJ;
    private MathVector testK;
    private MathVector testIJ;
    private MathVector testIK;
    private MathVector testJK;
    private MathVector testIJK;
    private MathVector testRandom_base;

    private double targetTestI;
    private double targetTestJ;
    private double targetTestK;
    private double targetTestIJ;
    private double targetTestIK;
    private double targetTestJK;
    private double targetTestIJK;
    private double[] targetTestRandom;

    private MathVector expectedWeighttestI;
    private MathVector expectedWeighttestJ;
    private MathVector expectedWeighttestK;
    private MathVector expectedWeighttestIJ;
    private MathVector expectedWeighttestIK;
    private MathVector expectedWeighttestJK;
    private MathVector expectedWeighttestIJK;
    private MathVector[] expectedWeighttestRandom;

    @Before
    public void setUp() throws Exception {
        Random rand = new Random();
        double error = 0.5;
        testI = MathVector.createI();
        testJ = MathVector.createJ();
        testK = MathVector.createK();
        testIJ = testI.sum(testJ);
        testIK = testI.sum(testK);
        testJK = testJ.sum(testK);
        testIJK = testJK.sum(testI);
        testRandom_base = new MathVector(rand.nextInt(100), rand.nextInt(100), rand.nextInt(100));

        MathVector[] testRandom = {
            testRandom_base.scale(testI),
            testRandom_base.scale(testJ),
            testRandom_base.scale(testK),
            testRandom_base.scale(testIJ),
            testRandom_base.scale(testIK),
            testRandom_base.scale(testJK),
            testRandom_base.scale(testIJK),
        };

        targetTestI = testI.getMagnitude();
        targetTestJ = testJ.getMagnitude();
        targetTestK = testK.getMagnitude();
        targetTestIJ = testIJ.getMagnitude();
        targetTestIK = testIK.getMagnitude();
        targetTestJK = testJK.getMagnitude();
        targetTestIJK = testIJK.getMagnitude();
        targetTestRandom = new double[testRandom.length];
        for(int i = 0; i < testRandom.length; i++){
            targetTestRandom[i] = testRandom[i].getMagnitude();
        }

        wtestI = new MaskedMathVector(testI, targetTestI, error);
        wtestJ = new MaskedMathVector(testJ, targetTestJ, error);
        wtestK = new MaskedMathVector(testK, targetTestK, error);
        wtestIJ = new MaskedMathVector(testIJ, targetTestIJ, error);
        wtestIK = new MaskedMathVector(testIK, targetTestIK, error);
        wtestJK = new MaskedMathVector(testJK, targetTestJK, error);
        wtestIJK = new MaskedMathVector(testIJK, targetTestIJK, error);
        wtestRandom0 = new MaskedMathVector[testRandom.length];
        for(int i = 0; i < testRandom.length; i++){
            wtestRandom0[i] = new MaskedMathVector(testRandom_base, targetTestRandom[i], error);
        }

        expectedWeighttestI = testI;
        expectedWeighttestJ = testJ;
        expectedWeighttestK = testK;
        expectedWeighttestIJ = testIJ;
        expectedWeighttestIK = testIK;
        expectedWeighttestJK = testJK;
        expectedWeighttestIJK = testIJK;
        expectedWeighttestRandom = new MathVector[7];
        expectedWeighttestRandom[0] = testI;
        expectedWeighttestRandom[1] = testJ;
        expectedWeighttestRandom[2] = testK;
        expectedWeighttestRandom[3] = testIJ;
        expectedWeighttestRandom[4] = testIK;
        expectedWeighttestRandom[5] = testJK;
        expectedWeighttestRandom[6] = testIJK;

    }

    @After
    public void tearDown() throws Exception {
        testI = null;
        testJ = null;
        testK = null;
        testIJ = null;
        testIK = null;
        testJK = null;
        testIJK = null;
        testRandom_base = null;

        targetTestRandom = null;

        expectedWeighttestI = null;
        expectedWeighttestJ = null;
        expectedWeighttestK = null;
        expectedWeighttestIJ = null;
        expectedWeighttestIK = null;
        expectedWeighttestJK = null;
        expectedWeighttestIJK = null;
        expectedWeighttestRandom = null;

        wtestI = null;
        wtestJ = null;
        wtestK = null;
        wtestIJ = null;
        wtestIK = null;
        wtestJK = null;
        wtestIJK = null;
        wtestRandom0 = null;
    }

    @Test
    public void calculateWeights() throws Exception {

        assertEquals(expectedWeighttestI, wtestI.calculateWeights());
        assertEquals(expectedWeighttestJ, wtestJ.calculateWeights());
        assertEquals(expectedWeighttestK, wtestK.calculateWeights());
        assertEquals(expectedWeighttestIJ, wtestIJ.calculateWeights());
        assertEquals(expectedWeighttestIK, wtestIK.calculateWeights());
        assertEquals(expectedWeighttestJK, wtestJK.calculateWeights());
        assertEquals(expectedWeighttestIJK, wtestIJK.calculateWeights());
        for(int i = 0; i < 7; i++){
            assertEquals(expectedWeighttestRandom[i], wtestRandom0[i].calculateWeights());
        }
    }

    @Test
    public void getInverseMask() throws Exception {

        wtestI.calculateWeights();
        wtestJ.calculateWeights();
        wtestK.calculateWeights();
        wtestIJ.calculateWeights();
        wtestIK.calculateWeights();
        wtestJK.calculateWeights();
        wtestIJK.calculateWeights();
        for(int i = 0; i < 7; i++){
            wtestRandom0[i].calculateWeights();
        }

        assertEquals(new MaskedMathVector(testI, testJK, -1, 0.5), wtestI.getInverseMask());
        assertEquals(new MaskedMathVector(testJ, testIK, -1, 0.5), wtestJ.getInverseMask());
        assertEquals(new MaskedMathVector(testK, testIJ, -1, 0.5), wtestK.getInverseMask());
        assertEquals(new MaskedMathVector(testIJ, testK, -1, 0.5), wtestIJ.getInverseMask());
        assertEquals(new MaskedMathVector(testIK, testJ, -1, 0.5), wtestIK.getInverseMask());
        assertEquals(new MaskedMathVector(testJK, testI, -1, 0.5), wtestJK.getInverseMask());
        assertEquals(new MaskedMathVector(testRandom_base, testJK, -1, 0.5), wtestRandom0[0].getInverseMask());
        assertEquals(new MaskedMathVector(testRandom_base, testIK, -1, 0.5), wtestRandom0[1].getInverseMask());
        assertEquals(new MaskedMathVector(testRandom_base, testIJ, -1, 0.5), wtestRandom0[2].getInverseMask());
        assertEquals(new MaskedMathVector(testRandom_base, testK, -1, 0.5), wtestRandom0[3].getInverseMask());
        assertEquals(new MaskedMathVector(testRandom_base, testJ, -1, 0.5), wtestRandom0[4].getInverseMask());
        assertEquals(new MaskedMathVector(testRandom_base, testI, -1, 0.5), wtestRandom0[5].getInverseMask());
    }

    @Test
    public void filterVector() throws Exception {
        wtestI.calculateWeights();
        wtestJ.calculateWeights();
        wtestK.calculateWeights();
        wtestIJ.calculateWeights();
        wtestIK.calculateWeights();
        wtestJK.calculateWeights();
        wtestIJK.calculateWeights();
        for(int i = 0; i < 7; i++){
            wtestRandom0[i].calculateWeights();
        }

        assertEquals(testI, wtestI.filterVector());
        assertEquals(testJ, wtestJ.filterVector());
        assertEquals(testK, wtestK.filterVector());
        assertEquals(testIJ, wtestIJ.filterVector());
        assertEquals(testIK, wtestIK.filterVector());
        assertEquals(testIJK, wtestIJK.filterVector());

        assertEquals(testRandom_base.scale(testI), wtestRandom0[0].filterVector());
        assertEquals(testRandom_base.scale(testJ), wtestRandom0[1].filterVector());
        assertEquals(testRandom_base.scale(testK), wtestRandom0[2].filterVector());
        assertEquals(testRandom_base.scale(testIJ), wtestRandom0[3].filterVector());
        assertEquals(testRandom_base.scale(testIK), wtestRandom0[4].filterVector());
        assertEquals(testRandom_base.scale(testJK), wtestRandom0[5].filterVector());
        assertEquals(testRandom_base.scale(testIJK), wtestRandom0[6].filterVector());
    }

    @Test
    public void getMaskedMagnitude() throws Exception {
        wtestI.calculateWeights();
        wtestJ.calculateWeights();
        wtestK.calculateWeights();
        wtestIJ.calculateWeights();
        wtestIK.calculateWeights();
        wtestJK.calculateWeights();
        wtestIJK.calculateWeights();
        for(int i = 0; i < 7; i++){
            wtestRandom0[i].calculateWeights();
        }
        double error = 0.01;
        assertEquals(1, wtestI.getMaskedMagnitude(), error);
        assertEquals(1, wtestJ.getMaskedMagnitude(), error);
        assertEquals(1, wtestK.getMaskedMagnitude(), error);
        assertEquals(Math.sqrt(2), wtestIJ.getMaskedMagnitude(), error);
        assertEquals(Math.sqrt(2), wtestIK.getMaskedMagnitude(), error);
        assertEquals(Math.sqrt(2), wtestJK.getMaskedMagnitude(), error);
        assertEquals(Math.sqrt(3), wtestIJK.getMaskedMagnitude(), error);
        for(int i = 0; i < 7; i++){
            assertEquals(wtestRandom0[i].getTarget(), wtestRandom0[i].getMaskedMagnitude(), error);
        }

    }
}