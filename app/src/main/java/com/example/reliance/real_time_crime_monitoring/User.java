package com.example.reliance.real_time_crime_monitoring;

import android.location.Location;

public class User {

    private String contact;
    private  String desc;
    Location location;


    private double latitude,longitude;
    String type;
    public User(){}

    public User(String contact, String desc, double latitude, double longitude, String type) {
        this.contact = contact;
        this.desc = desc;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getLatitude() {


        return latitude;
    }

    public void setLatitude(double latitude) {

        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;

    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


