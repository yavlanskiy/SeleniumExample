package com.yavlanskiy.model;

public class Film {

    private String title;
    private String year;

    public String getIMDb() {
        return IMDb;
    }

    public void setIMDb(String IMDb) {
        this.IMDb = IMDb;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String IMDb;
}
