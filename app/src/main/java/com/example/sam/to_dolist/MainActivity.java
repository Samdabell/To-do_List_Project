package com.example.sam.to_dolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String toDoList = sharedPref.getString("ToDoList", new ArrayList<Task>().toString());
        Gson gson = new Gson();

        TypeToken<ArrayList<Task>> taskArrayList = new TypeToken<ArrayList<Task>>(){};

        ArrayList<Task> list = gson.fromJson(toDoList, taskArrayList.getType());

        updateList(list);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.apply();

        editor.putString("ToDoList", gson.toJson(list));
        editor.apply();

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
                if (task.getDate().toString().equals(savedTask.getDateString())) {
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

    public void completeTask(View button){
        Task completedTask = (Task) button.getTag();
        Intent intent = new Intent(this, CompletedActivity.class);
        intent.putExtra("completedTask", completedTask);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String toDoList = sharedPref.getString("ToDoList", new ArrayList<Task>().toString());
        Gson gson = new Gson();
        TypeToken<ArrayList<Task>> taskArrayList = new TypeToken<ArrayList<Task>>(){};
        ArrayList<Task> list = gson.fromJson(toDoList, taskArrayList.getType());
       for (Task task : list){
           if (task.getTitle().equals(completedTask.getTitle())){
               list.remove(task);
               break;
           }
       }
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.apply();
        editor.putString("ToDoList", gson.toJson(list));
        editor.apply();

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_completed){
            Intent intent = new Intent(this, CompletedActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
