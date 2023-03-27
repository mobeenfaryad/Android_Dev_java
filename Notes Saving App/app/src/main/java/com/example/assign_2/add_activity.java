package com.example.assign_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class add_activity extends AppCompatActivity {

    private AdView mAdView;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Get count value passed from MainActivity
        Intent intent = getIntent();
        int count = intent.getIntExtra("Count", 0);

        // Load banner ad
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest1);

        // Load interstitial ad
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, "ca-app-pub-5735037368102204/9062004122", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        mInterstitialAd.show(add_activity.this);
                    }
                });

        // Save button and text view to get entered text
        Button save = findViewById(R.id.btn_save);
        TextView text = findViewById(R.id.entered_text);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String s = text.getText().toString(); // Get entered text
                    if (s.isEmpty()) {
                        // Show message if text is empty
                        Toast.makeText(add_activity.this, "Enter Something", Toast.LENGTH_SHORT).show();
                    } else {
                        // Save text to file
                        FileOutputStream fout = openFileOutput("note" + count + ".txt", MODE_PRIVATE);
                        fout.write(s.getBytes());
                        fout.close();
                        finish(); // Close the activity
                    }
                } catch (Exception e) {
                    e.getStackTrace();
                    Toast.makeText(add_activity.this, "Enter Something", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
