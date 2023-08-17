package com.example.incercarelicenta6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button buttonEngineData = findViewById(R.id.button);
        Button buttonUserCredential = findViewById(R.id.button2);
        buttonEngineData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondaryPage(v);
            }
        });

        buttonUserCredential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCredentialPage(v);
            }
        });

    }

    public void openSecondaryPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openCredentialPage(View view) {
        Intent intent = new Intent(this, UserCredential.class);
        startActivity(intent);
    }
}