package com.imdb.SimulacionCarnet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TopLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        //Iniciar Cola de Espera
        final Intent intent = new Intent();
        intent.setClass(getApplicationContext(),MainActivity.class);

        Button btnStartQueue = (Button)findViewById(R.id.btnStartQueue);
        btnStartQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(intent);
            }
        });

    }
}
