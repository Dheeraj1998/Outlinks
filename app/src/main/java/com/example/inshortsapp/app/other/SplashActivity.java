package com.example.inshortsapp.app.other;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.inshortsapp.app.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent temp = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(temp);
        finish();
    }
}
