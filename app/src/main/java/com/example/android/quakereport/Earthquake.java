package com.example.android.quakereport;

import java.util.Calendar;
import java.util.Date;

public class Earthquake {

    // Location (by nearest major city) of the earthquake
    private String mLocation;
    // Date earthquake occurred
    private long mDate;
    // Magnitude of the earthquake
    private double mMagnitude;
    // URL of USGS website about earthquake
    private String mUrl;

    /**
     * Constructor for Earthquake class
     *
     * @param vLocation  is the location (by nearest major city) of the earthquake
     * @param vDate      is the date earthquake occurred
     * @param vMagnitude is the magnitude of the earthquake
     */
    public Earthquake(String vLocation, long vDate, double vMagnitude, String vUrl) {
        mLocation = vLocation;
        mDate = vDate;
        mMagnitude = vMagnitude;
        mUrl = vUrl;
    }

    /**
     * Get the location name of the earthquake
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Get the date of the earthquake
     */
    public long getDate() {
        return mDate;
    }

    /**
     * Get the magnitude of the earthquake
     */
    public double getMagnitude() {
        return mMagnitude;
    }

    /**
     * Get the URL of the earthquake
     */
    public String getUrl() { return mUrl; }

}
