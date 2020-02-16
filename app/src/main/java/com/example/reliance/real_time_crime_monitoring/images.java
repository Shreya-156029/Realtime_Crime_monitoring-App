package com.example.reliance.real_time_crime_monitoring;

public class images {

    public String Description,url;

    public images() {

    }

    public images(String Description, String url) {

        this.Description=Description;
        this.url = url;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
