package com.imdb.SimulacionCarnet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TopLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        //Start activity of queue Clients
        final Intent intentQueueClients = new Intent();
        intentQueueClients.setClass(getApplicationContext(),MainActivity.class);

        Button btnStartQueue = (Button)findViewById(R.id.btnStartQueue);
        btnStartQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Repository.getProcesses().size()>0){
                    startActivity(intentQueueClients);
                }else{
                    Toast.makeText(TopLevelActivity.this, getResources().getString(R.string.msgStartQueueError), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Start activity of setup processes
        final Intent intentSetupProcesses = new Intent();
        intentSetupProcesses.setClass(getApplicationContext(),SetupProcessesActivity.class);

        Button btnSetupProcesses = (Button)findViewById(R.id.btnSetupProcesses);
        btnSetupProcesses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(intentSetupProcesses);
                } catch (Exception e) {
                    Toast.makeText(TopLevelActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
