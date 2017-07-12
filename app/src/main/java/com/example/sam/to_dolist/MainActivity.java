package com.example.sam.to_dolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;




public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String category = null;
    ArrayList<Task> mainList = null;

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

        Collections.sort(list, Task.PriorityComparator);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.apply();
        editor.putString("ToDoList", gson.toJson(list));
        editor.apply();

        Spinner categories = (Spinner) findViewById(R.id.category_view);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories_view, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(adapter);
        categories.setOnItemSelectedListener(this);

        String savedCategory = PreferenceManager.getDefaultSharedPreferences(this)
                .getString(getString(R.string.preference_file_key_category), null);

        setCategory(savedCategory);

        if (category != null){
            for (int i = 0; i < 5; i++) {
                if (categories.getItemAtPosition(i).toString().equals(category)) {
                    categories.setSelection(i);
                }
            }
        }

        filterList();

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
                    task.setDueDate(savedTask.getDueDate());
                    task.setPriority(savedTask.isPriority());
                    task.setCategory(savedTask.getCategory());
                    updated = true;
                }
            }
            if (!updated) {
                list.add(savedTask);
            }
        }
        mainList = list;
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

        Toast.makeText(this, "Task Completed", Toast.LENGTH_SHORT).show();
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

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (pos == 0){
            setCategory(null);
        }
        else {
            setCategory(parent.getItemAtPosition(pos).toString());
        }
        filterList();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        setCategory(null);
    }

    public void populateList(ArrayList<Task> list){
        ToDoListAdapter toDoListAdapter = new ToDoListAdapter(this, list);

        ListView listView = (ListView) findViewById(R.id.main_list);
        listView.setAdapter(toDoListAdapter);
    }

    public void setCategory(String newCategory){
        category = newCategory;

        PreferenceManager.getDefaultSharedPreferences(this)
                .edit()
                .putString(getString(R.string.preference_file_key_category), category)
                .apply();
    }

    public void filterList(){
        ArrayList<Task> filteredList = new ArrayList<Task>();
        if (category != null && category.equals("Priority")){
            for (Task task : mainList){
                if (task.isPriority()){
                    filteredList.add(task);
                }
            }
            populateList(filteredList);
        }
        else if (category != null){
            for (Task task : mainList){
                if (task.getCategory() != null && task.getCategory().equals(category)){
                    filteredList.add(task);
                }
            }
            populateList(filteredList);
        }
        else {
            populateList(mainList);
        }
    }

}
