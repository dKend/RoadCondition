package com.cs.capstone;

import com.cs.capstone.Util.UtilException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import static com.cs.capstone.Util.IO.isSpaceSufficient;
/**
 * Created by damia on 08-Mar-18.
 */

/**
 * Manages the storage of BumpReports in the local filesystem.
 * <p>
 *     The BumpReportManager class manages the storage of BumpReports, the submission of BumpReports
 *     to the server, and the deletion of submitted BumpReports. BumpReports that are passed to it
 *     are stored in a single file specified by the path variable.
 * </p>
 */
public class BumpReportManager {
    public class BumpReportManagerNULLArgumentException extends Exception {
        BumpReportManagerNULLArgumentException(String parameter, String method){
            super("The '"+parameter+"' parameter passed to '"+method+"' was NULL.");
        }
    }
    public class BumpReportManagerInsufficientStorageSpaceException extends Exception{
        BumpReportManagerInsufficientStorageSpaceException(String method){
            super("There was not enough disk space available to complete the method: '"+method+"'.");
        }
    }

    private String path;
    public BumpReportManager(String path) throws BumpReportManagerNULLArgumentException{
        if(path != null){
            this.path = path;
        }else{
            throw new BumpReportManagerNULLArgumentException("path", "Constructor");
        }
    }

    /**
     * Adds report to the end of the report file specified by the path field.
     * <p>
     *     The serialization of the report param is appended to the file at the location specified
     *     by the path field. On the first method call, it will check if the file already exists;
     *     If it does, then the report will be appended to the existing file. If the file does not
     *     already exist, then the method will check if the user has sufficient permissions to save
     *     a file at the specified path; If the user has sufficient permissions, the file will be
     *     created. If the user has insufficient permissions, then an exception will be thrown
     *     indicating this.
     * </p>
     * <p>
     *     Error Conditions:
     *          - Report is NULL.
     *          - There is insufficient disk space available to create or append the file specified
     *          by the path field (throws an exception).
     * </p>
     * <p>
     *     Order of Events:
     *          (1)     Checks that report is not NULL. if not, throw a NULL argument exception.
     *          (2)     Checks if the report file exists, if no then set a flag that indicates a
     *                  file needs to be created.
     *          (3)     Checks if there is sufficient disk space available to perform the needed
     *                  file writes. if not, throw an insufficient disk space exception.*
     *          (4)     Check the file creation flag, if its set, then open for writing in new file
     *                  mode, else open for writing in append mode.
     *          (5)     Serialize the report.
     *          (6)     Write the serialized report to the opened file.
     *          (7)     Close the file.
     *
     *      (*) Unable to be tested at this time. Need to find a solution that creates the
     *          environment necessary to test this step's functionality.
     * </p>
     * <p>
     *     Subject to change since we may be able to store everything as a SQL script that can be
     *     transmitted and executed server side.
     * </p>
     * @param report BumpReport instance to be stored for later use.
     * @throws BumpReportManagerNULLArgumentException
     * @throws BumpReportManagerInsufficientStorageSpaceException
     * @throws IOException
     */
    public void addReport(BumpReport report) throws BumpReportManagerNULLArgumentException, BumpReportManagerInsufficientStorageSpaceException, IOException {
        if(report != null){
            boolean f_append = false;
            File f = new File(path);
            if(f.exists()){
                f_append = true;
            }
            try{
                if(isSpaceSufficient(report, f)){
                    FileOutputStream fout = new FileOutputStream(f, f_append);   //  throws FileNotFoundException
                    ObjectOutputStream out = new ObjectOutputStream(fout);
                    out.writeObject(report);
                    out.flush();
                    out.close();
                    fout.close();
                }else{
                    throw new BumpReportManagerInsufficientStorageSpaceException("addReport");
                }
            }catch(UtilException e){
                System.out.println(e.getMessage());
            }

        }else{
            throw new BumpReportManagerNULLArgumentException("report", "addReport");
        }
    }

    /**
     * Gets the current length of the report file.
     * <p>
     *     Returns the length of the file stored at the path variable. If the file does not exist
     *     then -1 is returned.
     * </p>
     * @return
     */
    public long getReportFileLength(){
        long ret = -1;
        File f = new File(path);
        if(f.exists()){
            ret = f.length();
        }
        return ret;
    }

    /**
     * Submits the reports stored at the path field to the server.
     * <p>
     *     The method first checks if the report file exists and has data in it; If both are true,
     *     the method attempts to connect to the server. If a connection is established via
     *     wi-fi, then the report file is submitted to the server. Once the data is transmitted
     *     successfully (ie the method receives an acknowledgement from the server) the method then
     *     goes on to delete report file.
     * </p>
     * <p>
     *     Above functionality is subject to change since this aspect of the project has not been
     *     started yet. We still need to get a server set up for report storage, and we may be able
     *     to send data directly as SQL statements, eliminating the need to send a file.
     * </p>
     */
    public void submitReports(){

    }
}
