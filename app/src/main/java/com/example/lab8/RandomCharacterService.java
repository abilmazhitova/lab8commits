package com.example.lab8;

import static android.app.Service.START_STICKY;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;

public class RandomCharacterService extends Service {
    private boolean isRandomGeneratorOn;
    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isRandomGeneratorOn = true;
        new Thread(() -> startRandomGenerator()).start();
        return START_STICKY;
    }

    private void startRandomGenerator() {
        while (isRandomGeneratorOn) {
            try {
                Thread.sleep(1000);
                int randomIdx = new Random().nextInt(26);
                char myRandomCharacter = alphabet[randomIdx];

                Intent broadcastIntent = new Intent("my.custom.action.tag.lab6");
                broadcastIntent.putExtra("randomCharacter", myRandomCharacter);
                sendBroadcast(broadcastIntent);
            } catch (InterruptedException ignored) {}
        }
    }

    private void stopRandomGenerator() {
        isRandomGeneratorOn = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomGenerator();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
