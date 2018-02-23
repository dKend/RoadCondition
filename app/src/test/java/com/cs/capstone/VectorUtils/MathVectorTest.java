package com.cs.capstone.VectorUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by damia on 15-Feb-18.
 */
public class MathVectorTest {
    private MathVector allZeros;
    private MathVector allOnes;
    private MathVector allPositive;
    private MathVector allNegative;
    private MathVector mixed0;
    private MathVector mixed1;
    private MathVector mixed2;
    private MathVector mixed3;
    private MathVector mixed4;
    private MathVector mixed5;
    @Before
    public void setUp() throws Exception {
        allZeros = new MathVector(0, 0, 0);
        allOnes = new MathVector(1, 1, 1);
        allPositive = new MathVector(22, 16, 500);
        allNegative = new MathVector(-22, -16, -500);
        mixed0 = new MathVector(22, 16, -500);
        mixed1 = new MathVector(22, -16, 500);
        mixed2 = new MathVector(22, -16, -500);
        mixed3 = new MathVector(-22, 16, 500);
        mixed4 = new MathVector(-22, 16, -500);
        mixed5 = new MathVector(-22, -16, 500);

    }

    @After
    public void tearDown() throws Exception {
        allZeros = null;
        allOnes = null;
        allPositive = null;
        allNegative = null;
        mixed0 = null;
        mixed1 = null;
        mixed2 = null;
        mixed3 = null;
        mixed4 = null;
        mixed5 = null;
    }

    @Test
    public void getMagnitude() throws Exception {
        //  this delta value may need to be adjusted
        double delta = 0.5;
        /*
            Test when all vertices are zero
        */
        assertEquals("Failed to calculate value for all zeros.", 0, allZeros.getMagnitude(), 0.0);
        /*
            Test when all vertices are ones
        */
        assertEquals("Failed to calculate magnitude when all ones", Math.sqrt(3), allOnes.getMagnitude(), delta);
        /*
            Test all of the other MathVectors
        */
        double expected = Math.sqrt(Math.pow(22,2) + Math.pow(16, 2) + Math.pow(500, 2));
        assertEquals("Failed to calculate when all positive.", expected, allPositive.getMagnitude(), delta);
        assertEquals("Failed to calculate when all negative.", expected, allNegative.getMagnitude(), delta);
        assertEquals("Failed to calculate when mixed 0.", expected, mixed0.getMagnitude(), delta);
        assertEquals("Failed to calculate when mixed 1.", expected, mixed1.getMagnitude(), delta);
        assertEquals("Failed to calculate when mixed 2.", expected, mixed2.getMagnitude(), delta);
        assertEquals("Failed to calculate when mixed 3.", expected, mixed3.getMagnitude(), delta);
        assertEquals("Failed to calculate when mixed 4.", expected, mixed4.getMagnitude(), delta);
        assertEquals("Failed to calculate when mixed 5.", expected, mixed5.getMagnitude(), delta);

    }

    @Test
    public void equals() throws Exception {
        assertTrue(allZeros.equals(allZeros));
        assertFalse(allZeros.equals(allOnes));
        assertFalse(allZeros.equals(allPositive));
        assertFalse(allZeros.equals(allNegative));
        assertFalse(allZeros.equals(mixed0));
        assertFalse(allZeros.equals(mixed1));
        assertFalse(allZeros.equals(mixed2));
        assertFalse(allZeros.equals(mixed3));
        assertFalse(allZeros.equals(mixed4));
        assertFalse(allZeros.equals(mixed5));

        assertTrue(allOnes.equals(allOnes));
        assertFalse(allOnes.equals(allZeros));
        assertFalse(allOnes.equals(allPositive));
        assertFalse(allOnes.equals(allNegative));
        assertFalse(allOnes.equals(mixed0));
        assertFalse(allOnes.equals(mixed1));
        assertFalse(allOnes.equals(mixed2));
        assertFalse(allOnes.equals(mixed3));
        assertFalse(allOnes.equals(mixed4));
        assertFalse(allOnes.equals(mixed5));

        assertTrue(allPositive.equals(allPositive));
        assertFalse(allPositive.equals(allZeros));
        assertFalse(allPositive.equals(allOnes));
        assertFalse(allPositive.equals(allNegative));
        assertFalse(allPositive.equals(mixed0));
        assertFalse(allPositive.equals(mixed1));
        assertFalse(allPositive.equals(mixed2));
        assertFalse(allPositive.equals(mixed3));
        assertFalse(allPositive.equals(mixed4));
        assertFalse(allPositive.equals(mixed5));

        assertTrue(allNegative.equals(allNegative));
        assertFalse(allNegative.equals(allZeros));
        assertFalse(allNegative.equals(allOnes));
        assertFalse(allNegative.equals(allPositive));
        assertFalse(allNegative.equals(mixed0));
        assertFalse(allNegative.equals(mixed1));
        assertFalse(allNegative.equals(mixed2));
        assertFalse(allNegative.equals(mixed3));
        assertFalse(allNegative.equals(mixed4));
        assertFalse(allNegative.equals(mixed5));

        assertTrue(mixed0.equals(mixed0));
        assertFalse(mixed0.equals(allZeros));
        assertFalse(mixed0.equals(allOnes));
        assertFalse(mixed0.equals(allNegative));
        assertFalse(mixed0.equals(allPositive));
        assertFalse(mixed0.equals(mixed1));
        assertFalse(mixed0.equals(mixed2));
        assertFalse(mixed0.equals(mixed3));
        assertFalse(mixed0.equals(mixed4));
        assertFalse(mixed0.equals(mixed5));

        assertTrue(mixed1.equals(mixed1));
        assertFalse(mixed1.equals(allZeros));
        assertFalse(mixed1.equals(allOnes));
        assertFalse(mixed1.equals(allNegative));
        assertFalse(mixed1.equals(allPositive));
        assertFalse(mixed1.equals(mixed0));
        assertFalse(mixed1.equals(mixed2));
        assertFalse(mixed1.equals(mixed3));
        assertFalse(mixed1.equals(mixed4));
        assertFalse(mixed1.equals(mixed5));

        assertTrue(mixed2.equals(mixed2));
        assertFalse(mixed2.equals(allZeros));
        assertFalse(mixed2.equals(allOnes));
        assertFalse(mixed2.equals(allNegative));
        assertFalse(mixed2.equals(allPositive));
        assertFalse(mixed2.equals(mixed0));
        assertFalse(mixed2.equals(mixed1));
        assertFalse(mixed2.equals(mixed3));
        assertFalse(mixed2.equals(mixed4));
        assertFalse(mixed2.equals(mixed5));

        assertTrue(mixed3.equals(mixed3));
        assertFalse(mixed3.equals(allZeros));
        assertFalse(mixed3.equals(allOnes));
        assertFalse(mixed3.equals(allNegative));
        assertFalse(mixed3.equals(allPositive));
        assertFalse(mixed3.equals(mixed0));
        assertFalse(mixed3.equals(mixed1));
        assertFalse(mixed3.equals(mixed2));
        assertFalse(mixed3.equals(mixed4));
        assertFalse(mixed3.equals(mixed5));

        assertTrue(mixed4.equals(mixed4));
        assertFalse(mixed4.equals(allZeros));
        assertFalse(mixed4.equals(allOnes));
        assertFalse(mixed4.equals(allNegative));
        assertFalse(mixed4.equals(allPositive));
        assertFalse(mixed4.equals(mixed0));
        assertFalse(mixed4.equals(mixed1));
        assertFalse(mixed4.equals(mixed3));
        assertFalse(mixed4.equals(mixed2));
        assertFalse(mixed4.equals(mixed5));

        assertTrue(mixed5.equals(mixed5));
        assertFalse(mixed5.equals(allZeros));
        assertFalse(mixed5.equals(allOnes));
        assertFalse(mixed5.equals(allNegative));
        assertFalse(mixed5.equals(allPositive));
        assertFalse(mixed5.equals(mixed0));
        assertFalse(mixed5.equals(mixed1));
        assertFalse(mixed5.equals(mixed3));
        assertFalse(mixed5.equals(mixed4));
        assertFalse(mixed5.equals(mixed2));

        assertFalse(allZeros.equals(null));
        assertFalse(allOnes.equals(null));
        assertFalse(allPositive.equals(null));
        assertFalse(allNegative.equals(null));
        assertFalse(mixed0.equals(null));
        assertFalse(mixed1.equals(null));
        assertFalse(mixed2.equals(null));
        assertFalse(mixed3.equals(null));
        assertFalse(mixed4.equals(null));
        assertFalse(mixed5.equals(null));
    }

    @Test
    public void sum() throws Exception {
        assertEquals(null, allZeros.sum(null));
        assertEquals(null, allOnes.sum(null));
        assertEquals(null, allPositive.sum(null));
        assertEquals(null, allNegative.sum(null));
        assertEquals(null, mixed0.sum(null));
        assertEquals(null, mixed1.sum(null));
        assertEquals(null, mixed2.sum(null));
        assertEquals(null, mixed3.sum(null));
        assertEquals(null, mixed4.sum(null));
        assertEquals(null, mixed5.sum(null));

        assertEquals(allOnes, allZeros.sum(allOnes));
        assertEquals(allZeros, allZeros.sum(allZeros));
        assertEquals(allPositive, allZeros.sum(allPositive));
        assertEquals(allNegative, allZeros.sum(allNegative));
        assertEquals(mixed0, allZeros.sum(mixed0));
        assertEquals(mixed1, allZeros.sum(mixed1));
        assertEquals(mixed2, allZeros.sum(mixed2));
        assertEquals(mixed3, allZeros.sum(mixed3));
        assertEquals(mixed4, allZeros.sum(mixed4));
        assertEquals(mixed5, allZeros.sum(mixed5));

        assertEquals(new MathVector(2,2,2), allOnes.sum(allOnes));
        assertEquals(new MathVector(44, 32, 1000), allPositive.sum(allPositive));
        assertEquals(new MathVector(-44, -32, -1000), allNegative.sum(allNegative));
        assertEquals(new MathVector(44, 32, -1000), mixed0.sum(mixed0));
        assertEquals(new MathVector(44, -32, 1000), mixed1.sum(mixed1));
        assertEquals(new MathVector(44, -32, -1000), mixed2.sum(mixed2));
        assertEquals(new MathVector(-44, 32, 1000), mixed3.sum(mixed3));
        assertEquals(new MathVector(-44, 32, -1000), mixed4.sum(mixed4));
        assertEquals(new MathVector(-44, -32, 1000), mixed5.sum(mixed5));

        assertEquals(new MathVector(23,17,501), allOnes.sum(allPositive));
        assertEquals(new MathVector(-21,-15,-499), allOnes.sum(allNegative));
        assertEquals(new MathVector(23,17,-499), allOnes.sum(mixed0));
        assertEquals(new MathVector(23,-15,501), allOnes.sum(mixed1));
        assertEquals(new MathVector(23,-15,-499), allOnes.sum(mixed2));
        assertEquals(new MathVector(-21,17,501), allOnes.sum(mixed3));
        assertEquals(new MathVector(-21,17,-499), allOnes.sum(mixed4));
        assertEquals(new MathVector(-21,-15,501), allOnes.sum(mixed5));

    }

    @Test
    public void scale() throws Exception {
        assertEquals(null, allZeros.scale(null));

        assertEquals(allZeros, allZeros.scale(allZeros));
        assertEquals(allZeros, allZeros.scale(allOnes));
        assertEquals(allZeros, allZeros.scale(allPositive));
        assertEquals(allZeros, allZeros.scale(allNegative));
        assertEquals(allZeros, allZeros.scale(mixed0));
        assertEquals(allZeros, allZeros.scale(mixed1));
        assertEquals(allZeros, allZeros.scale(mixed2));
        assertEquals(allZeros, allZeros.scale(mixed3));
        assertEquals(allZeros, allZeros.scale(mixed4));
        assertEquals(allZeros, allZeros.scale(mixed5));

        assertEquals(allOnes, allOnes.scale(allOnes));
        assertEquals(allPositive, allOnes.scale(allPositive));
        assertEquals(allNegative, allOnes.scale(allNegative));
        assertEquals(mixed0, allOnes.scale(mixed0));
        assertEquals(mixed1, allOnes.scale(mixed1));
        assertEquals(mixed2, allOnes.scale(mixed2));
        assertEquals(mixed3, allOnes.scale(mixed3));
        assertEquals(mixed4, allOnes.scale(mixed4));
        assertEquals(mixed5, allOnes.scale(mixed5));

        MathVector expected = new MathVector(22*22, 16*16, 500*500);

        assertEquals(expected, allPositive.scale(allPositive));
        assertEquals(expected, allNegative.scale(allNegative));
        assertEquals(expected, mixed0.scale(mixed0));
        assertEquals(expected, mixed1.scale(mixed1));
        assertEquals(expected, mixed2.scale(mixed2));
        assertEquals(expected, mixed3.scale(mixed3));
        assertEquals(expected, mixed4.scale(mixed4));
        assertEquals(expected, mixed5.scale(mixed5));

        assertEquals(new MathVector(-expected.getX(), -expected.getY(), -expected.getZ()), allPositive.scale(allNegative));
        assertEquals(new MathVector(expected.getX(), expected.getY(), -expected.getZ()), allPositive.scale(mixed0));
        assertEquals(new MathVector(expected.getX(), -expected.getY(), expected.getZ()), allPositive.scale(mixed1));
        assertEquals(new MathVector(expected.getX(), -expected.getY(), -expected.getZ()), allPositive.scale(mixed2));
        assertEquals(new MathVector(-expected.getX(), expected.getY(), expected.getZ()), allPositive.scale(mixed3));
        assertEquals(new MathVector(-expected.getX(), expected.getY(), -expected.getZ()), allPositive.scale(mixed4));
        assertEquals(new MathVector(-expected.getX(), -expected.getY(), expected.getZ()), allPositive.scale(mixed5));

        assertEquals(new MathVector(-expected.getX(), -expected.getY(), -expected.getZ()), allNegative.scale(allPositive));
        assertEquals(new MathVector(-expected.getX(), -expected.getY(), expected.getZ()), allNegative.scale(mixed0));
        assertEquals(new MathVector(-expected.getX(), expected.getY(), -expected.getZ()), allNegative.scale(mixed1));
        assertEquals(new MathVector(-expected.getX(), expected.getY(), expected.getZ()), allNegative.scale(mixed2));
        assertEquals(new MathVector(expected.getX(), -expected.getY(), -expected.getZ()), allNegative.scale(mixed3));
        assertEquals(new MathVector(expected.getX(), -expected.getY(), expected.getZ()), allNegative.scale(mixed4));
        assertEquals(new MathVector(expected.getX(), expected.getY(), -expected.getZ()), allNegative.scale(mixed5));
    }

    @Test
    public void scalarProduct() throws Exception {
        assertEquals(allZeros, allZeros.scalarProduct(10));
        assertEquals(allZeros, allZeros.scalarProduct(-10));
        assertEquals(allZeros, allZeros.scalarProduct(0));
        assertEquals(allZeros, allOnes.scalarProduct(0));

        assertEquals(new MathVector(2, 2, 2), allOnes.scalarProduct(2));
        assertEquals(new MathVector(-2, -2, -2), allOnes.scalarProduct(-2));
        assertEquals(allZeros, allOnes.scalarProduct(0));

        assertEquals(allPositive, allNegative.scalarProduct(-1));
        assertEquals(new MathVector(44, 32, 1000), allPositive.scalarProduct(2));

        for(int i = 0; i < 100; i++){
            assertEquals(new MathVector(allPositive.getX()*i, allPositive.getY()*i, allPositive.getZ()*i), allPositive.scalarProduct(i));
            assertEquals(new MathVector(allOnes.getX()*i, allOnes.getY()*i, allOnes.getZ()*i), allOnes.scalarProduct(i));
            assertEquals(new MathVector(allNegative.getX()*i, allNegative.getY()*i, allNegative.getZ()*i), allNegative.scalarProduct(i));
        }
    }

    @Test
    public void difference() throws Exception {
        assertEquals(null, allZeros.difference(null));
        assertEquals(allZeros, allZeros.difference(allZeros));
        assertEquals(allOnes, allOnes.difference(allZeros));
        assertEquals(allPositive, allPositive.difference(allZeros));
        assertEquals(allNegative, allNegative.difference(allZeros));
        assertEquals(mixed0, mixed0.difference(allZeros));
        assertEquals(mixed1, mixed1.difference(allZeros));
        assertEquals(mixed2, mixed2.difference(allZeros));
        assertEquals(mixed3, mixed3.difference(allZeros));
        assertEquals(mixed4, mixed4.difference(allZeros));
        assertEquals(mixed5, mixed5.difference(allZeros));

        assertEquals(allOnes.scalarProduct((-1)), allZeros.difference(allOnes));
        assertEquals(allPositive.scalarProduct((-1)), allZeros.difference(allPositive));
        assertEquals(allNegative.scalarProduct((-1)), allZeros.difference(allNegative));
        assertEquals(mixed0.scalarProduct((-1)), allZeros.difference(mixed0));
        assertEquals(mixed1.scalarProduct((-1)), allZeros.difference(mixed1));
        assertEquals(mixed2.scalarProduct((-1)), allZeros.difference(mixed2));
        assertEquals(mixed3.scalarProduct((-1)), allZeros.difference(mixed3));
        assertEquals(mixed4.scalarProduct((-1)), allZeros.difference(mixed4));
        assertEquals(mixed5.scalarProduct((-1)), allZeros.difference(mixed5));
    }
}