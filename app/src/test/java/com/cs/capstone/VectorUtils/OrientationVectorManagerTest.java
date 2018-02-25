package com.cs.capstone.VectorUtils;

import android.graphics.drawable.GradientDrawable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

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

    @Test
    public void isOrientationChanged() throws Exception {
        assertFalse(testFaceDown.isOrientationChanged(testFaceDown.getGravity().getVector()));
        assertFalse(testFaceUp.isOrientationChanged(testFaceUp.getGravity().getVector()));
        assertFalse(testLandscapeLeftUp.isOrientationChanged(testLandscapeLeftUp.getGravity().getVector()));
        assertFalse(testLandscapeRightUp.isOrientationChanged(testLandscapeRightUp.getGravity().getVector()));
        assertFalse(testPortraitRightSideUp.isOrientationChanged(testPortraitRightSideUp.getGravity().getVector()));
        assertFalse(testPortraitUpsideDown.isOrientationChanged(testPortraitUpsideDown.getGravity().getVector()));

        assertTrue(testFaceDown.isOrientationChanged(testFaceUp.getGravity().getVector()));
        assertTrue(testFaceUp.isOrientationChanged(testFaceDown.getGravity().getVector()));
        assertTrue(testLandscapeLeftUp.isOrientationChanged(testLandscapeRightUp.getGravity().getVector()));
        assertTrue(testLandscapeRightUp.isOrientationChanged(testLandscapeLeftUp.getGravity().getVector()));
        assertTrue(testPortraitRightSideUp.isOrientationChanged(testPortraitUpsideDown.getGravity().getVector()));
        assertTrue(testPortraitUpsideDown.isOrientationChanged(testPortraitRightSideUp.getGravity().getVector()));
    }

    @Test
    public void areMasksIdentical() throws Exception {
        assertFalse(testFaceDown.areMasksIdentical(null));
        assertTrue(testFaceDown.areMasksIdentical(testFaceDown.getGravity()));
        assertTrue(testFaceUp.areMasksIdentical(testFaceUp.getGravity()));
        assertTrue(testLandscapeLeftUp.areMasksIdentical(testLandscapeLeftUp.getGravity()));
        assertTrue(testLandscapeRightUp.areMasksIdentical(testLandscapeRightUp.getGravity()));
        assertTrue(testPortraitRightSideUp.areMasksIdentical(testPortraitRightSideUp.getGravity()));
        assertTrue(testPortraitUpsideDown.areMasksIdentical(testPortraitUpsideDown.getGravity()));

    }

    @Test
    public void updateOrientation() throws Exception {
        assertEquals(-1, testFaceDown.updateOrientation(null));
        assertEquals(-1, testFaceUp.updateOrientation(null));
        assertEquals(-1, testLandscapeLeftUp.updateOrientation(null));
        assertEquals(-1, testLandscapeRightUp.updateOrientation(null));
        assertEquals(-1, testPortraitRightSideUp.updateOrientation(null));
        assertEquals(-1, testPortraitUpsideDown.updateOrientation(null));

        assertEquals(1, testFaceDown.updateOrientation(testFaceDown.getGravity().getVector()));
        assertEquals(1, testFaceUp.updateOrientation(testFaceUp.getGravity().getVector()));
        assertEquals(1, testLandscapeLeftUp.updateOrientation(testLandscapeLeftUp.getGravity().getVector()));
        assertEquals(1, testLandscapeRightUp.updateOrientation(testLandscapeRightUp.getGravity().getVector()));
        assertEquals(1, testPortraitRightSideUp.updateOrientation(testPortraitRightSideUp.getGravity().getVector()));
        assertEquals(1, testPortraitUpsideDown.updateOrientation(testPortraitUpsideDown.getGravity().getVector()));

        MathVector[] vectors = {
                testFaceDown.getGravity().getVector(),
                testFaceUp.getGravity().getVector(),
                testLandscapeLeftUp.getGravity().getVector(),
                testLandscapeRightUp.getGravity().getVector(),
                testPortraitRightSideUp.getGravity().getVector(),
                testPortraitUpsideDown.getGravity().getVector(),
        };

        assertEquals(0, testFaceDown.updateOrientation(vectors[1]));
        assertEquals((Object)vectors[1], testFaceDown.getGravity().getVector());
        assertEquals(0, testFaceDown.updateOrientation(vectors[2]));
        assertEquals((Object)vectors[2], testFaceDown.getGravity().getVector());
        assertEquals(0, testFaceDown.updateOrientation(vectors[3]));
        assertEquals((Object)vectors[3], testFaceDown.getGravity().getVector());
        assertEquals(0, testFaceDown.updateOrientation(vectors[4]));
        assertEquals((Object)vectors[4], testFaceDown.getGravity().getVector());
        assertEquals(0, testFaceDown.updateOrientation(vectors[5]));
        assertEquals((Object)vectors[5], testFaceDown.getGravity().getVector());

        assertEquals(0, testFaceUp.updateOrientation(vectors[0]));
        assertEquals((Object)vectors[0], testFaceUp.getGravity().getVector());
        assertEquals(0, testFaceUp.updateOrientation(vectors[1]));
        assertEquals((Object)vectors[1], testFaceUp.getGravity().getVector());
        assertEquals(0, testFaceUp.updateOrientation(vectors[2]));
        assertEquals((Object)vectors[2], testFaceUp.getGravity().getVector());
        assertEquals(0, testFaceUp.updateOrientation(vectors[3]));
        assertEquals((Object)vectors[3], testFaceUp.getGravity().getVector());
        assertEquals(0, testFaceUp.updateOrientation(vectors[4]));
        assertEquals((Object)vectors[4], testFaceUp.getGravity().getVector());
        assertEquals(0, testFaceUp.updateOrientation(vectors[5]));
        assertEquals((Object)vectors[5], testFaceUp.getGravity().getVector());

        assertEquals(0, testLandscapeLeftUp.updateOrientation(vectors[0]));
        assertEquals((Object)vectors[0], testLandscapeLeftUp.getGravity().getVector());
        assertEquals(0, testLandscapeLeftUp.updateOrientation(vectors[1]));
        assertEquals((Object)vectors[1], testLandscapeLeftUp.getGravity().getVector());
        assertEquals(0, testLandscapeLeftUp.updateOrientation(vectors[2]));
        assertEquals((Object)vectors[2], testLandscapeLeftUp.getGravity().getVector());
        assertEquals(0, testLandscapeLeftUp.updateOrientation(vectors[3]));
        assertEquals((Object)vectors[3], testLandscapeLeftUp.getGravity().getVector());
        assertEquals(0, testLandscapeLeftUp.updateOrientation(vectors[4]));
        assertEquals((Object)vectors[4], testLandscapeLeftUp.getGravity().getVector());
        assertEquals(0, testLandscapeLeftUp.updateOrientation(vectors[5]));
        assertEquals((Object)vectors[5], testLandscapeLeftUp.getGravity().getVector());

        assertEquals(0, testLandscapeRightUp.updateOrientation(vectors[0]));
        assertEquals((Object)vectors[0], testLandscapeRightUp.getGravity().getVector());
        assertEquals(0, testLandscapeRightUp.updateOrientation(vectors[1]));
        assertEquals((Object)vectors[1], testLandscapeRightUp.getGravity().getVector());
        assertEquals(0, testLandscapeRightUp.updateOrientation(vectors[2]));
        assertEquals((Object)vectors[2], testLandscapeRightUp.getGravity().getVector());
        assertEquals(0, testLandscapeRightUp.updateOrientation(vectors[3]));
        assertEquals((Object)vectors[3], testLandscapeRightUp.getGravity().getVector());
        assertEquals(0, testLandscapeRightUp.updateOrientation(vectors[4]));
        assertEquals((Object)vectors[4], testLandscapeRightUp.getGravity().getVector());
        assertEquals(0, testLandscapeRightUp.updateOrientation(vectors[5]));
        assertEquals((Object)vectors[5], testLandscapeRightUp.getGravity().getVector());

        assertEquals(0, testPortraitRightSideUp.updateOrientation(vectors[0]));
        assertEquals((Object)vectors[0], testPortraitRightSideUp.getGravity().getVector());
        assertEquals(0, testPortraitRightSideUp.updateOrientation(vectors[1]));
        assertEquals((Object)vectors[1], testPortraitRightSideUp.getGravity().getVector());
        assertEquals(0, testPortraitRightSideUp.updateOrientation(vectors[2]));
        assertEquals((Object)vectors[2], testPortraitRightSideUp.getGravity().getVector());
        assertEquals(0, testPortraitRightSideUp.updateOrientation(vectors[3]));
        assertEquals((Object)vectors[3], testPortraitRightSideUp.getGravity().getVector());
        assertEquals(0, testPortraitRightSideUp.updateOrientation(vectors[4]));
        assertEquals((Object)vectors[4], testPortraitRightSideUp.getGravity().getVector());
        assertEquals(0, testPortraitRightSideUp.updateOrientation(vectors[5]));
        assertEquals((Object)vectors[5], testPortraitRightSideUp.getGravity().getVector());

        assertEquals(0, testPortraitUpsideDown.updateOrientation(vectors[0]));
        assertEquals((Object)vectors[0], testPortraitUpsideDown.getGravity().getVector());
        assertEquals(0, testPortraitUpsideDown.updateOrientation(vectors[1]));
        assertEquals((Object)vectors[1], testPortraitUpsideDown.getGravity().getVector());
        assertEquals(0, testPortraitUpsideDown.updateOrientation(vectors[2]));
        assertEquals((Object)vectors[2], testPortraitUpsideDown.getGravity().getVector());
        assertEquals(0, testPortraitUpsideDown.updateOrientation(vectors[3]));
        assertEquals((Object)vectors[3], testPortraitUpsideDown.getGravity().getVector());
        assertEquals(0, testPortraitUpsideDown.updateOrientation(vectors[4]));
        assertEquals((Object)vectors[4], testPortraitUpsideDown.getGravity().getVector());
        assertEquals(0, testPortraitUpsideDown.updateOrientation(vectors[5]));
        assertEquals((Object)vectors[5], testPortraitUpsideDown.getGravity().getVector());
    }

    @Test
    public void getFilteredReading() throws Exception {
        Random rand = new Random();
        assertEquals(null, testFaceDown.getFilteredReading(null));
        assertEquals(null, testFaceUp.getFilteredReading(null));
        assertEquals(null, testLandscapeLeftUp.getFilteredReading(null));
        assertEquals(null, testLandscapeRightUp.getFilteredReading(null));
        assertEquals(null, testPortraitRightSideUp.getFilteredReading(null));
        assertEquals(null, testPortraitUpsideDown.getFilteredReading(null));

        MathVector thing = new MathVector(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        MathVector[] expected = {
            thing.difference(testFaceDown.getGravity().getVector()),
            thing.difference(testFaceUp.getGravity().getVector()),
            thing.difference(testLandscapeLeftUp.getGravity().getVector()),
            thing.difference(testLandscapeRightUp.getGravity().getVector()),
            thing.difference(testPortraitRightSideUp.getGravity().getVector()),
            thing.difference(testPortraitUpsideDown.getGravity().getVector())
        };
        expected[0] = expected[0].difference(expected[0].scale(testFaceDown.getGravity().getInverseMask().getMask()));
        expected[1] = expected[1].difference(expected[1].scale(testFaceUp.getGravity().getInverseMask().getMask()));
        expected[2] = expected[2].difference(expected[2].scale(testLandscapeLeftUp.getGravity().getInverseMask().getMask()));
        expected[3] = expected[3].difference(expected[3].scale(testLandscapeRightUp.getGravity().getInverseMask().getMask()));
        expected[4] = expected[4].difference(expected[4].scale(testPortraitRightSideUp.getGravity().getInverseMask().getMask()));
        expected[5] = expected[5].difference(expected[5].scale(testPortraitUpsideDown.getGravity().getInverseMask().getMask()));

        MathVector vector0 = thing.difference(testFaceDown.getGravity().getVector());
        MathVector vector1 = vector0.scale(testFaceDown.getGravity().getInverseMask().getMask());
        vector0 = vector0.difference(vector1);

        assertEquals(vector0, testFaceDown.getFilteredReading(thing));
        assertEquals(expected[0], testFaceDown.getFilteredReading(thing));
        assertEquals(expected[1], testFaceUp.getFilteredReading(thing));
        assertEquals(expected[2], testLandscapeLeftUp.getFilteredReading(thing));
        assertEquals(expected[3], testLandscapeRightUp.getFilteredReading(thing));
        assertEquals(expected[4], testPortraitRightSideUp.getFilteredReading(thing));
        assertEquals(expected[5], testPortraitUpsideDown.getFilteredReading(thing));
    }
}