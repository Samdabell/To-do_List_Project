package com.example.sam.to_dolist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Sam on 06/07/2017.
 */

public class TaskTest {

    Task task;

    @Before
    public void before(){
        task = new Task("Do tests");
    }

    @Test
    public void getTitleTest() throws Exception {
        assertEquals("Do tests", task.getTitle());
    }

    @Test
    public void getDescriptionTest() throws Exception {
        task.setDescription("Write some tests for the task class");
        assertEquals("Write some tests for the task class", task.getDescription());
    }

    @Test
    public void isPriorityTest() throws Exception {
        assertFalse(task.isPriority());
        task.setPriority(true);
        assertTrue(task.isPriority());
    }


}
