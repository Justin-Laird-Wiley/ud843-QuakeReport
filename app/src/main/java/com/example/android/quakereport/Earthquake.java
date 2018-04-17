package com.example.android.quakereport;

import java.util.Calendar;
import java.util.Date;

public class Earthquake {

    private String mLocation;
    private String mDate;
    private double mMagnitude;

    public Earthquake(String vLocation, String vDate, double vMagnitude)
    {
        mLocation = vLocation;
        mDate = vDate;
        mMagnitude = vMagnitude;
    }

    /**
     * Get the name of the Android version
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Get the Android version number
     */
    public String getDate() {
        return mDate;
    }

    /**
     * Get the image resource ID
     */
    public double getMagnitude() {
        return mMagnitude;
    }

}
