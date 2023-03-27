package com.example.assign_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;




public class MainActivity extends AppCompatActivity {


    private AppOpenAd mappOpenAd;

    int count ;


    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest1);

        AdRequest request = new AdRequest.Builder().build();
        AppOpenAd.load(
                this, "ca-app-pub-5735037368102204/8130150117", request, new AppOpenAd.AppOpenAdLoadCallback() {

                    @Override
                    public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                        mappOpenAd = appOpenAd;
                        mappOpenAd.show(MainActivity.this);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        File root = getFilesDir();
        File[] files = root.listFiles();

        count = files.length; // to get all total number of files


        for(int i=0;i<count;i++)
        {
            Log.d ( "file ", files[i].getAbsolutePath());

        }



        LinearLayout l = findViewById(R.id.layoutll);

        ImageButton add = findViewById(R.id.btn_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i= new Intent(MainActivity.this, add_activity.class);
                i.putExtra("Count", count);
                startActivity(i);

            }
        });

        TextView[] tvs = new TextView[count];
        String txt="";
        FileInputStream fis;
        BufferedReader br;
        for(int i=0; i<count;i++)
        {
            tvs[i]= new TextView(getApplicationContext());
            try {
                fis = openFileInput("note"+i+".txt");
                br = new BufferedReader(new InputStreamReader(fis));


                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n"); // Add newline character after each line
                }
                txt=sb.toString();

                fis.close();
                br.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            tvs[i].setId(i);
            tvs[i].setMaxLines(1);
            tvs[i].setText(txt);
            tvs[i].setPadding(100,100,100,100);

           int borderWidth = getResources().getDimensionPixelSize(androidx.cardview.R.dimen.cardview_default_radius); // get the border width from resources
            tvs[i].setBackgroundColor(Color.WHITE);
            GradientDrawable borderDrawable = new GradientDrawable();
            borderDrawable.setStroke(borderWidth, Color.BLACK);
            tvs[i].setBackground(borderDrawable);

            l.addView(tvs[i]);

            tvs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        int s = view.getId();
                        Intent i= new Intent(MainActivity.this, delete_show.class);
                        i.putExtra("Index", s);
                        startActivity(i);
                }
                    catch (Exception e )
                    {
                        e.getStackTrace();

                    }
                    }

            });

        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        LinearLayout l = findViewById(R.id.layoutll);
        l.removeAllViews();
    }


}