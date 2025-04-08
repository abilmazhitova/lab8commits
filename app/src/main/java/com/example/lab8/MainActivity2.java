package com.example.lab8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private Intent musicServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        musicServiceIntent = new Intent(this, MyService.class);

        findViewById(R.id.btn_start_music).setOnClickListener(this::onClick);
        findViewById(R.id.btn_stop_music).setOnClickListener(this::onClick);
        findViewById(R.id.btn_back).setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_start_music) {
            startService(musicServiceIntent);
        } else if (id == R.id.btn_stop_music) {
            stopService(musicServiceIntent);
        } else if (id == R.id.btn_back) {
            finish();
        }
    }
}
