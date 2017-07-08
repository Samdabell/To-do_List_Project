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

        TextView task_title = (TextView)listItemView.findViewById(R.id.task_title);
        task_title.setText(currentTask.getTitle());

        Button button = (Button)listItemView.findViewById(R.id.complete_button);
        button.setTag(currentTask);

        listItemView.setTag(currentTask);

        return listItemView;
    }
}
