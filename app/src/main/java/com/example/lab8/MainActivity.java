package com.example.lab8;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText randomCharacterEditText;
    private BroadcastReceiver broadcastReceiver;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        randomCharacterEditText = findViewById(R.id.editText_randomCharacter);
        broadcastReceiver = new MyBroadcastReceiver();
        serviceIntent = new Intent(getApplicationContext(), RandomCharacterService.class);

        // Привязка кнопок к обработчику событий
        findViewById(R.id.button_start).setOnClickListener(this::onClick);
        findViewById(R.id.button_end).setOnClickListener(this::onClick);
        findViewById(R.id.btn_next).setOnClickListener(this::onClick);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.button_start) {
            startService(serviceIntent);
        } else if (id == R.id.button_end) {
            stopService(serviceIntent);
            randomCharacterEditText.setText("");
        } else if (id == R.id.btn_next) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("my.custom.action.tag.lab6");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(broadcastReceiver, intentFilter, Context.RECEIVER_NOT_EXPORTED);
        } else {
            registerReceiver(broadcastReceiver, intentFilter);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    // Внутренний класс ресивера
    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            char data = intent.getCharExtra("randomCharacter", '?');
            randomCharacterEditText.setText(String.valueOf(data));
        }
    }
}
