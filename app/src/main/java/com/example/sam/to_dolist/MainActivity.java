package com.example.sam.to_dolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToDoList toDoList = new ToDoList();
        ArrayList<Task> list = toDoList.getList();

        updateList(list);

        ToDoListAdapter toDoListAdapter = new ToDoListAdapter(this, list);

        ListView listView = (ListView) findViewById(R.id.main_list);
        listView.setAdapter(toDoListAdapter);
    }



    public void getTask(View listItem){
        Task task = (Task) listItem.getTag();

        Intent intent = new Intent(this, TaskInfoActivity.class);
        intent.putExtra("task", task);
        startActivity(intent);
    }

    public void updateList(ArrayList<Task> list){
        Task savedTask = (Task) getIntent().getSerializableExtra("savedTask");
        boolean updated = false;
        if (savedTask != null) {
            for (Task task : list){
                if (task.getTitle().equals(savedTask.getTitle()) || task.getDescription().equals(savedTask.getDescription())) {
                    task.setTitle(savedTask.getTitle());
                    task.setDescription(savedTask.getDescription());
                    updated = true;
                }
            }
            if (!updated) {
                list.add(savedTask);
            }
        }

    }

    public void addTask(View button){
        Intent intent = new Intent(this, TaskInfoActivity.class);
        startActivity(intent);
    }
}
