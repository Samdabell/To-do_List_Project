package com.example.sam.to_dolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CompletedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key_2), Context.MODE_PRIVATE);

        String completedList = sharedPref.getString("CompletedList", new ArrayList<Task>().toString());
        Gson gson = new Gson();

        TypeToken<ArrayList<Task>> taskArrayList = new TypeToken<ArrayList<Task>>(){};

        ArrayList<Task> list = gson.fromJson(completedList, taskArrayList.getType());

        Task completedTask = (Task) getIntent().getSerializableExtra("completedTask");
        if (completedTask != null) {
            list.add(completedTask);
        }


        SharedPreferences.Editor editor = sharedPref.edit();
        editor.apply();

        editor.putString("CompletedList", gson.toJson(list));
        editor.apply();

        CompleteListAdapter completeListAdapter = new CompleteListAdapter(this, list);

        ListView listView = (ListView) findViewById(R.id.completed_list);
        listView.setAdapter(completeListAdapter);

        if (completedTask != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void getTask(View listItem){
        Task task = (Task) listItem.getTag();
        Intent intent = new Intent(this, TaskInfoActivity.class);
        intent.putExtra("task", task);
        startActivity(intent);
    }

    public void deleteTask(View button){
        Task deletedTask = (Task) button.getTag();

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key_2), Context.MODE_PRIVATE);
        String completedList = sharedPref.getString("CompletedList", new ArrayList<Task>().toString());
        Gson gson = new Gson();
        TypeToken<ArrayList<Task>> taskArrayList = new TypeToken<ArrayList<Task>>(){};
        ArrayList<Task> list = gson.fromJson(completedList, taskArrayList.getType());
        for (Task task : list){
            if (task.getTitle().equals(deletedTask.getTitle())){
                list.remove(task);
                break;
            }
        }
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.apply();
        editor.putString("CompletedList", gson.toJson(list));
        editor.apply();

        finish();
        Intent intent = new Intent(this, CompletedActivity.class);
        startActivity(intent);

        Toast.makeText(this, "Task Deleted", Toast.LENGTH_SHORT).show();
    }
}
