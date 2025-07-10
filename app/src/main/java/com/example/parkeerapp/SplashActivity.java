package com.example.parkeerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // pastikan ini betul

        // Ambil ImageView dan animasi
        ImageView logo = findViewById(R.id.logoParkeer);
        if (logo != null) {
            logo.animate()
                    .alpha(1f)
                    .setDuration(1500)
                    .withEndAction(() -> {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    });
        } else {
            // Untuk debug jika null
            Toast.makeText(this, "ImageView logoParkeer not found!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}

