package com.example.assign4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class tripscreen extends AppCompatActivity {

    int count ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripscreen);



    }


    @Override
    protected void onResume() {
        super.onResume();


        LinearLayout l = findViewById(R.id.layoutll);

        File root = getFilesDir();
        File[] files = root.listFiles();

        count = files.length; // to get all total number of files


        Button btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
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
                fis = openFileInput("trip"+i+".txt");
                br = new BufferedReader(new InputStreamReader(fis));


                StringBuilder sb = new StringBuilder();
                String line;
                int j =0;

                String date ;
                String msg  ;

                while ((line = br.readLine()) != null) {
                    if(j==0)
                    {
                        String[] parts = line.split(",");
                        date = parts[2];// + parts[3] ;
                       // msg = parts[4];
                        tvs[i].setText("Trip " +(i+1) +" " +date );//+" " +msg);

                    }
                    j++;
                    sb.append(line);
                    sb.append("\n"); // Add newline character after each line
                }
                txt=sb.toString();

                //tvs[i].setText(txt);
                fis.close();
                br.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            tvs[i].setId(i);
            //tvs[i].setMaxLines(1);

            tvs[i].setTextSize(20);
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
                        Intent i= new Intent(tripscreen.this, tripdetail.class);
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








//
//

    }


    @Override
    protected void onPause() {
        super.onPause();

        LinearLayout l = findViewById(R.id.layoutll);
        l.removeAllViews();
    }


}