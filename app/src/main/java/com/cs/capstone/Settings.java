package com.cs.capstone;

import java.io.Serializable;

/**
 * Created by damia on 03-Feb-18.
 */

public class Settings implements Serializable {
    private boolean collect_f;
    private boolean submit_f;
    private static final long serialVersionUID = 1L;
    private static String path = "";
    public Settings(boolean collect_f, boolean submit_f){
        this.collect_f = collect_f;
        this.submit_f = submit_f;
    }
    public Settings(){
        collect_f = false;
        submit_f = false;
    }
    public boolean isCollectEnabled(){
        return collect_f;
    }
    public boolean isSubmitEnabled(){
        return submit_f;
    }
    public void enableSubmit(){

    }
    public void disableSubmit(){

    }
    public void enableCollect(){

    }
    public void disableCollect(){

    }
    public boolean equals(Settings other){
        boolean ret = false;

        return ret;
    }
    public static String toString(Settings self){
        String ret = null;

        return ret;
    }
}
