package com.cs.capstone;

import android.location.Location;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by damia on 08-Mar-18.
 */
public class BumpReportManagerTest {
    private BumpReportManager m;
    private File f;
    private BumpReport dummy;

    @Before
    public void setUp() throws Exception {
        m = new BumpReportManager("reports.dat");
        f = new File("reports.dat");
        dummy = new BumpReport(null, "", null, Intensity.INTEN_LOW);
    }

    @After
    public void tearDown() throws Exception {
        m = null;
        if(f.exists()){
            f.delete();
        }
        f = null;
    }

    @Test
    public void addReport() throws Exception {
        //  test exceptions thrown
        try{
            m.addReport(null);
            assertFalse(true);
        }catch(BumpReportManager.BumpReportManagerNULLArgumentException e){
            assertEquals("The 'report' parameter passed to 'addReport' was NULL.", e.getMessage());
        }
        //  not sure how to test insufficient space without stuffing my harddrive with garbage files
        //  so we will skip this until we can find a solution.

        /*
        try{
            BumpReport dummy = new BumpReport(null, "", LocalDate.now(), Intensity.INTEN_LOW);
            m.addReport(dummy);
            assertFalse(true);
        }catch(BumpReportManager.BumpReportManagerInsufficientStorageSpaceException e){
            assertEquals("There was not enough disk space available to complete the method: 'addReport'.", e.getMessage());
        }
        */

        //  test functionality.
        long length = 0;


        //  when file doesn't exist
        m.addReport(dummy);
        assertTrue(f.exists());
        assertTrue(length < f.length());
        //  update length value
        length = f.length();

        //  when the file exists, test that the data is appended
        m.addReport(dummy);
        assertTrue(f.exists());
        assertTrue(length > 0);
        assertTrue(length < f.length());
        //  update length value
        length = f.length();

        //  after multiple reports have been stored
        m.addReport(dummy);
        assertTrue(f.exists());
        assertTrue(length > 0);
        assertTrue(length < f.length());

    }

    @Test
    public void getReportFileLength() throws Exception {
        assertEquals(-1, m.getReportFileLength());
        m.addReport(dummy);
        assertTrue(f.exists());
        assertEquals(f.length(), m.getReportFileLength());
    }

    @Test
    public void submitReports() throws Exception {
        assertTrue(false);
    }

}