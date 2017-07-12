package com.example.sam.to_dolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;



/**
 * Created by Sam on 08/07/2017.
 */

public class CompleteListAdapter extends ArrayAdapter<Task> {

    public CompleteListAdapter(Context context, ArrayList<Task> toDoList) {
        super(context, 0, toDoList);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent){
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.completed_list_item, parent, false);
        }

        Task currentTask = getItem(position);

        TextView taskTitle = (TextView)listItemView.findViewById(R.id.c_task_title);
        taskTitle.setText(currentTask != null ? currentTask.getTitle() : null);

        Button button = (Button)listItemView.findViewById(R.id.delete_button);
        button.setTag(currentTask);

        listItemView.setTag(currentTask);

        return listItemView;
    }
}
