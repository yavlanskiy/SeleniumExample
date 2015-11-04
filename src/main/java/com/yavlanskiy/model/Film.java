package com.yavlanskiy.model;

public class Film {

    private String title;
    private String year;

    public String getYear() {
        return year;
    }

    public Film setYear(String year) {
        this.year = year;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Film setTitle(String title) {
        this.title = title;
        return this;
    }

}
