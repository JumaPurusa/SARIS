package com.example.saris.Actvities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.saris.R;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        final String profile = sharedPreferences.getString("profile", null);

        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {

                        if(profile == null)
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        else
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));

                    }
                }
        , 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler != null)
            handler.removeCallbacks(runnable);
    }
}
