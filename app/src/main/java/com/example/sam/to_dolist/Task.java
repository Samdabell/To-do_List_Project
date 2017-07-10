package com.example.sam.to_dolist;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Sam on 06/07/2017.
 */

public class Task implements Serializable{

    private String title;
    private String description;
    private Date date;
    private String dateString;
    private boolean priority;
    private String dueDate;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        date = new Date();
        dateString = null;
        priority = false;
        dueDate = null;
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

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public static Comparator<Task> PriorityComparator = new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {
            boolean PriorityCheck1 = t1.isPriority();
            boolean PriorityCheck2 = t2.isPriority();
            return Boolean.valueOf(PriorityCheck1).compareTo(PriorityCheck2);
        }
    };

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
