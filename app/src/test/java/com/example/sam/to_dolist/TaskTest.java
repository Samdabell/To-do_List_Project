package com.example.sam.to_dolist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sam on 06/07/2017.
 */

public class TaskTest {

    Task task;

    @Before
    public void before(){
        task = new Task("Do tests", "Write some tests for the task class");
    }

    @Test
    public void getTitleTest() throws Exception {
        assertEquals("Do tests", task.getTitle());
    }

    @Test
    public void getDescriptionTest() throws Exception {
        assertEquals("Write some tests for the task class", task.getDescription());
    }
}
