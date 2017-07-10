package com.example.sam.to_dolist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class TaskInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        Task task = (Task) getIntent().getSerializableExtra("task");

        final EditText dueDate = (EditText) findViewById(R.id.due_date);

        if (task != null) {
            EditText title = (EditText) findViewById(R.id.info_task_title);
            title.setText(task.getTitle());

            EditText description = (EditText) findViewById(R.id.task_description);
            description.setText(task.getDescription());

            TextView created = (TextView) findViewById(R.id.date_created);
            created.setText(task.getDate().toString());

            TextView dateLabel = (TextView) findViewById(R.id.date_label);
            dateLabel.setVisibility(View.VISIBLE);

            CheckBox checkBox = (CheckBox) findViewById(R.id.priority_check);
            checkBox.setChecked(task.isPriority());

            dueDate.setText(task.getDueDate());
        }

        dueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal = Calendar.getInstance();
                int mYear = cal.get(Calendar.YEAR);
                int mMonth = cal.get(Calendar.MONTH);
                int mDay = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(TaskInfoActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dueDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        } );
    }

    public void onClick(View button){
        EditText title = (EditText) findViewById(R.id.info_task_title);
        EditText description = (EditText) findViewById(R.id.task_description);
        TextView created = (TextView) findViewById(R.id.date_created);
        EditText dueDate = (EditText) findViewById(R.id.due_date);
        CheckBox priority = (CheckBox) findViewById(R.id.priority_check);
        Task task = new Task(title.getText().toString());
        task.setDescription(description.getText().toString());
        task.setDateString(created.getText().toString());
        task.setDueDate(dueDate.getText().toString());

        if (priority.isChecked()){
            task.setPriority(true);
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("savedTask", task);
        startActivity(intent);

        Toast.makeText(this, "Task saved", Toast.LENGTH_SHORT).show();

    }
}
