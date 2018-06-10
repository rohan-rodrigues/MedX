package com.vipul.bit_hotels.utils;

/**
 * Created by rohanrodrigues on 12/13/17.
 */

public class Trip {
    private String mName;
    private int imageString;
    private String date;

    public Trip(String name, int imageString, String date) {
        mName = name;
        this.imageString = imageString;
        this.date = date;
    }

    public String getName() {
        return mName;
    }

    public int getImageString() {
        return imageString;
    }

    public String getDate() {
        return date;
    }
}