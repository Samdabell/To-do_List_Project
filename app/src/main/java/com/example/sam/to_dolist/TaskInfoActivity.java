package com.example.sam.to_dolist;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TaskInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        Task task = (Task) getIntent().getSerializableExtra("task");

        if (task != null) {
            EditText title = (EditText) findViewById(R.id.info_task_title);
            title.setText(task.getTitle());

            EditText description = (EditText) findViewById(R.id.task_description);
            description.setText(task.getDescription());
        }
    }

    public void onClick(View button){
        EditText title = (EditText) findViewById(R.id.info_task_title);
        EditText description = (EditText) findViewById(R.id.task_description);
        Task task = new Task(title.getText().toString(), description.getText().toString());

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("savedTask", task);
        startActivity(intent);

        Toast.makeText(this, "Task saved", Toast.LENGTH_SHORT).show();

    }
}
