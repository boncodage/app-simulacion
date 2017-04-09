package com.imdb.SimulacionCarnet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Martin on 08/04/2017.
 */

public class ProcessAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Process> processes = new ArrayList<>();
    private Context context;
    private LayoutInflater myInflater;

    public ProcessAdapter(ArrayList<Process> processes, Context context) {
        this.processes = processes;
        this.context = context;
        this.myInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.processes.size();
    }

    @Override
    public Process getItem(int position) {
        return this.processes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView = myInflater.inflate(R.layout.process_list_view,null);
            holder = new ViewHolder();
            holder.imageButtonDeleteProcess = (ImageButton)convertView.findViewById(R.id.btnDeleteProcess);
            holder.textViewProcessDescription = (TextView)convertView.findViewById(R.id.textViewProcessDescription);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        final Process process = getItem(position);
        holder.textViewProcessDescription.setText(process.getId() + ": " + process.getDescription());
        holder.imageButtonDeleteProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processes.remove(process);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    static class ViewHolder{
        TextView textViewProcessDescription;
        ImageButton imageButtonDeleteProcess;
    }
}
