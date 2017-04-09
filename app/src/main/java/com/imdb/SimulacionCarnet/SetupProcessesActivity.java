package com.imdb.SimulacionCarnet;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SetupProcessesActivity extends AppCompatActivity {
    private static ArrayList<Process> processes;
    private ProcessAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_processes);
        processes = Repository.getProcesses();

        ListView listViewProcesses = (ListView)findViewById(R.id.listViewProcesses);
        adapter = new ProcessAdapter(processes,this);
        listViewProcesses.setAdapter(adapter);

        FloatingActionButton FABCreateProcess = (FloatingActionButton)findViewById(R.id.btnCreateProcess);
        FABCreateProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextCreateProcess = (EditText)findViewById(R.id.editTextProcessDescription);
                EditText editTextProcessNumber = (EditText)findViewById(R.id.editTextProcessNumber);

                try {
                    String createdProcess = editTextCreateProcess.getText().toString();
                    String processNumber = editTextProcessNumber.getText().toString();
                    if (!createdProcess.matches("") && !processNumber.matches("")) {
                        Process process = new Process(Integer.parseInt(processNumber), editTextCreateProcess.getText().toString());
                        processes.add(process);
                        Toast.makeText(SetupProcessesActivity.this, "Se agregó el proceso correctamente"  + ": cantidad = " + Repository.getProcesses().size(), Toast.LENGTH_SHORT).show();
                        editTextCreateProcess.setText("");
                        editTextCreateProcess.setFocusable(true);
                        editTextProcessNumber.setText("");
                        editTextProcessNumber.clearFocus();
                    } else {
                        Toast.makeText(SetupProcessesActivity.this, getResources().getString(R.string.msgCreatingProcessError), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(SetupProcessesActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
