package com.example.sam.to_dolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;


import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by Sam on 06/07/2017.
 */

public class ToDoListAdapter extends ArrayAdapter<Task> {

    public ToDoListAdapter(Context context, ArrayList<Task> toDoList) {
        super(context, 0, toDoList);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent){
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Task currentTask = getItem(position);

        CheckedTextView task_title = (CheckedTextView)listItemView.findViewById(R.id.task_title);
        task_title.setText(currentTask.getTitle());

        if (currentTask.isPriority()) {
            task_title.setCheckMarkDrawable(R.mipmap.ic_star_black_24dp);
            task_title.setChecked(true);
        }
        else {
            task_title.setCheckMarkDrawable(null);
            task_title.setChecked(false);
        }

        Button button = (Button)listItemView.findViewById(R.id.complete_button);
        button.setTag(currentTask);

        dueDateCheck(currentTask, button);

        listItemView.setTag(currentTask);

        return listItemView;
    }

    public void dueDateCheck(Task currentTask, Button button){
        Calendar today = Calendar.getInstance();
        int day = today.get(Calendar.DAY_OF_MONTH);
        int month = today.get(Calendar.MONTH) + 1;
        int year = today.get(Calendar.YEAR);

        if (currentTask.getDueDate().length()>1) {
            String[] splitDueDate = currentTask.getDueDate().split("/");
            int dueDay = Integer.valueOf(splitDueDate[0]);
            int dueMonth = Integer.valueOf(splitDueDate[1]);
            int dueYear = Integer.valueOf(splitDueDate[2]);

            if (dueYear < year || (dueYear == year && dueMonth < month) || dueYear == year && dueMonth == month && dueDay < day ) {
                button.setBackgroundColor(Color.parseColor("#de5929"));
                button.setText(R.string.warning);
            }

        }
    }
}
