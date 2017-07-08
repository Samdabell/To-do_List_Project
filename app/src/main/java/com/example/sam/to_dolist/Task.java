package com.example.sam.to_dolist;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sam on 06/07/2017.
 */

public class Task implements Serializable{

    private String title;
    private String description;
    private Date date;
    private String dateString;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        date = new Date();
        dateString = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
