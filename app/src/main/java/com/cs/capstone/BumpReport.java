package com.cs.capstone;

import java.io.Serializable;
import java.time.LocalDate;
import android.location.Location;

/**
 * Created by damian on 03-Feb-18.
 */

public class BumpReport implements Serializable {
    private Location coords;
    private String zipcode;
    private LocalDate date;
    private Intensity intensity;
	public BumpReport(Location coords, String zipcode, LocalDate date, Intensity intensity){
	    this.coords = coords;
	    this.zipcode = zipcode;
	    this.date = date;
	    this.intensity = intensity;
    }
    public Location getCoords(){
	    return coords;
    }
    public String getZipcode(){
        return zipcode;
    }
    public LocalDate getDate(){
        return date;
    }
    public Intensity getIntensity(){
        return intensity;
    }

}
