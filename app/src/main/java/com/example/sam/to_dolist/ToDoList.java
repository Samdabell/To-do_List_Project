package com.example.sam.to_dolist;

import java.util.ArrayList;

/**
 * Created by Sam on 06/07/2017.
 */

public class ToDoList {

    private ArrayList<Task> list;

    public ToDoList() {
        list = new ArrayList<Task>();
        list.add(new Task("Example Task", "This is an example task, here is where you write a description of your task"));
        list.add(new Task("Finish To-Do List App", "Finish my project, this to-do list app"));
        list.add(new Task("Help Freyja Move", "Help Freyja move her stuf out her flat to take back home"));
    }

    public ArrayList<Task> getList() {
        return list;
    }
}
