package com.example.sam.to_dolist;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sam on 06/07/2017.
 */

public class ToDoListTest {
    @Test
    public void getListTest() throws Exception {
        ToDoList list = new ToDoList();
        assertEquals(3, list.getList().size());
    }
}
