package com.example.android.quakereport;

import java.util.Calendar;
import java.util.Date;

public class Earthquake {

    // Location (by nearest major city) of the earthquake
    private String mLocation;
    // Date earthquake occurred
    private String mDate;
    // Magnitude of the earthquake
    private double mMagnitude;

    /**
     * Constructor for Earthquake class
     *
     * @param vLocation  is the location (by nearest major city) of the earthquake
     * @param vDate      is the date earthquake occurred
     * @param vMagnitude is the magnitude of the earthquake
     */
    public Earthquake(String vLocation, String vDate, double vMagnitude) {
        mLocation = vLocation;
        mDate = vDate;
        mMagnitude = vMagnitude;
    }

    /**
     * Get the name of the location
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Get the date of the earthquake
     */
    public String getDate() {
        return mDate;
    }

    /**
     * Get the magnitude earthquake
     */
    public double getMagnitude() {
        return mMagnitude;
    }
}
