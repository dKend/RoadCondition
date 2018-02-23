package com.cs.capstone.VectorUtils;

import android.graphics.drawable.GradientDrawable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by damia on 18-Feb-18.
 */
public class OrientationVectorManagerTest {
    private OrientationVectorManager testFaceDown;
    private OrientationVectorManager testFaceUp;
    private OrientationVectorManager testLandscapeLeftUp;
    private OrientationVectorManager testLandscapeRightUp;
    private OrientationVectorManager testPortraitRightSideUp;
    private OrientationVectorManager testPortraitUpsideDown;

    @Before
    public void setUp() throws Exception {
        testFaceDown = new OrientationVectorManager(new MathVector(0, 0, (float)-9.81));
        testFaceUp = new OrientationVectorManager(new MathVector(0, 0, (float)9.81));
        testLandscapeLeftUp = new OrientationVectorManager(new MathVector((float)-9.81, 0, 0));
        testLandscapeRightUp = new OrientationVectorManager(new MathVector((float)9.81, 0, 0));
        testPortraitRightSideUp = new OrientationVectorManager(new MathVector(0, (float)9.81, 0));
        testPortraitUpsideDown = new OrientationVectorManager(new MathVector(0, (float)-9.81, 0));
    }

    @After
    public void tearDown() throws Exception {
        testFaceDown = null;
        testFaceUp = null;
        testLandscapeLeftUp = null;
        testLandscapeRightUp = null;
        testPortraitRightSideUp = null;
        testPortraitUpsideDown = null;
    }

    @Test
    public void isVectorEvent() throws Exception {

        assertFalse(testFaceDown.isVectorEvent(null));
        assertFalse(testFaceUp.isVectorEvent(null));
        assertFalse(testLandscapeLeftUp.isVectorEvent(null));
        assertFalse(testLandscapeRightUp.isVectorEvent(null));
        assertFalse(testPortraitRightSideUp.isVectorEvent(null));
        assertFalse(testPortraitUpsideDown.isVectorEvent(null));

        assertFalse(testFaceDown.isVectorEvent(new MathVector(0, 0, (float)-9.81)));
        assertFalse(testFaceDown.isVectorEvent(new MathVector(0,0, (float)-9.80)));
        assertTrue(testFaceDown.isVectorEvent(new MathVector(0, 0, (float)-9.79)));
        assertFalse(testFaceDown.isVectorEvent(new MathVector(0, 0, (float)-9.82)));
        assertTrue(testFaceDown.isVectorEvent(new MathVector(0, 0, (float)-9.83)));
        assertTrue(testFaceDown.isVectorEvent(new MathVector(0, 0, 0)));
        assertFalse(testFaceDown.isVectorEvent(new MathVector(0, 0, (float)9.81)));

        assertFalse(testFaceUp.isVectorEvent(new MathVector(0, 0, (float)9.81)));
        assertFalse(testFaceUp.isVectorEvent(new MathVector(0, 0, (float)9.80)));
        assertTrue(testFaceUp.isVectorEvent(new MathVector(0, 0, (float)9.79)));
        assertFalse(testFaceUp.isVectorEvent(new MathVector(0, 0, (float)9.82)));
        assertTrue(testFaceUp.isVectorEvent(new MathVector(0, 0, (float)9.83)));
        assertTrue(testFaceUp.isVectorEvent(new MathVector(0, 0, 0)));
        assertFalse(testFaceUp.isVectorEvent(new MathVector(0, 0, (float)-9.80)));

        assertFalse(testLandscapeLeftUp.isVectorEvent(new MathVector((float)-9.81, 0, 0)));
        assertFalse(testLandscapeLeftUp.isVectorEvent(new MathVector((float)-9.80, 0, 0)));
        assertTrue(testLandscapeLeftUp.isVectorEvent(new MathVector((float)-9.79, 0, 0)));
        assertFalse(testLandscapeLeftUp.isVectorEvent(new MathVector((float)-9.82, 0, 0)));
        assertTrue(testLandscapeLeftUp.isVectorEvent(new MathVector((float)-9.83, 0, 0)));
        assertTrue(testLandscapeLeftUp.isVectorEvent(new MathVector(0, 0, 0)));
        assertFalse(testLandscapeLeftUp.isVectorEvent(new MathVector((float)9.81, 0, 0)));

        assertFalse(testLandscapeRightUp.isVectorEvent(new MathVector((float)9.81, 0, 0)));
        assertFalse(testLandscapeRightUp.isVectorEvent(new MathVector((float)9.80, 0, 0)));
        assertTrue(testLandscapeRightUp.isVectorEvent(new MathVector((float)9.79, 0, 0)));
        assertFalse(testLandscapeRightUp.isVectorEvent(new MathVector((float)9.82, 0, 0)));
        assertTrue(testLandscapeRightUp.isVectorEvent(new MathVector((float)9.83, 0, 0)));
        assertTrue(testLandscapeRightUp.isVectorEvent(new MathVector(0, 0, 0)));
        assertFalse(testLandscapeRightUp.isVectorEvent(new MathVector((float)-9.81, 0, 0)));

        assertFalse(testPortraitRightSideUp.isVectorEvent(new MathVector(0, (float)9.81, 0)));
        assertFalse(testPortraitRightSideUp.isVectorEvent(new MathVector(0, (float)9.80, 0)));
        assertTrue(testPortraitRightSideUp.isVectorEvent(new MathVector(0, (float)9.79, 0)));
        assertFalse(testPortraitRightSideUp.isVectorEvent(new MathVector(0, (float)9.82, 0)));
        assertTrue(testPortraitRightSideUp.isVectorEvent(new MathVector(0, (float)9.83, 0)));
        assertTrue(testPortraitRightSideUp.isVectorEvent(new MathVector(0, 0, 0)));
        assertFalse(testPortraitRightSideUp.isVectorEvent(new MathVector(0, (float)-9.81, 0)));

        assertFalse(testPortraitUpsideDown.isVectorEvent(new MathVector(0, (float)-9.81, 0)));
        assertFalse(testPortraitUpsideDown.isVectorEvent(new MathVector(0, (float)-9.80, 0)));
        assertTrue(testPortraitUpsideDown.isVectorEvent(new MathVector(0, (float)-9.79, 0)));
        assertFalse(testPortraitUpsideDown.isVectorEvent(new MathVector(0, (float)-9.82, 0)));
        assertTrue(testPortraitUpsideDown.isVectorEvent(new MathVector(0, (float)-9.83, 0)));
        assertTrue(testPortraitUpsideDown.isVectorEvent(new MathVector(0, 0, 0)));
        assertFalse(testPortraitUpsideDown.isVectorEvent(new MathVector(0, (float)9.81, 0)));
    }

}