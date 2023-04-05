package com.example.assign4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Scanner;

public class tripdetail extends AppCompatActivity {

    String txt="";
    FileInputStream fis;
    BufferedReader br;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripdetail);

    }


    @Override
    protected void onResume() {
        super.onResume();


        TextView tripno =findViewById(R.id.textView_tripno);
        TextView date = findViewById(R.id.textView_tripdate);
        LinearLayout l = findViewById(R.id.layoutll);
        //  l.setOrientation(LinearLayout.HORIZONTAL);


        Button btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        File root = getFilesDir();
        File[] files = root.listFiles();
        int count = files.length;

        Intent intent = getIntent();
        int index = intent.getIntExtra("Index" , 0 );

        tripno.setText("Trip No : " + (index+1));

        int lines=0;



        try {
            // create a new file object
            File file = new File(root,"trip" + index + ".txt");
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {
                sc.nextLine();
                lines++;
            }


            // close scanner
            sc.close();
        } catch (Exception e) {
            e.getStackTrace();
        }


        Log.d("*****", "*******trip detail line " +lines );

        TextView[] tvs = new TextView[lines];
        Button[] btns = new Button[lines];

        int i=0;

        try {
            fis = openFileInput("trip" +index + ".txt");
            br = new BufferedReader(new InputStreamReader(fis));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                tvs[i]= new TextView(getApplicationContext());
                btns[i]= new Button(getApplicationContext());
                Log.d("*****", "*******trip detail" +txt );


                String date_ ;
                String msg  ;
                double x;
                double y ;


                String[] parts = line.split(",");
                x = Double.parseDouble(parts[0]);
                y = Double.parseDouble(parts[1]);
                date_ = parts[2] + parts[3];
                msg = parts[4];


                if(i==0)
                {
                    date.setText(parts[2] );
                }

                tvs[i].setText( "" +date_ +" " + msg);




                btns[i].setId(i);
                tvs[i].setId(i);
                tvs[i].setMaxLines(1);
                // tvs[i].setText(line);
                tvs[i].setPadding(100,100,100,100);



                int borderWidth = getResources().getDimensionPixelSize(androidx.cardview.R.dimen.cardview_default_radius); // get the border width from resources
                tvs[i].setBackgroundColor(Color.WHITE);
                GradientDrawable borderDrawable = new GradientDrawable();
                borderDrawable.setStroke(borderWidth, Color.BLACK);
                tvs[i].setBackground(borderDrawable);


                btns[i].setText("View map");


                btns[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", x, y);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(intent);
                    }
                });






                l.addView(tvs[i]);
                l.addView(btns[i]);
                i++;
                sb.append("\n"); // Add newline character after each line
            }

            txt=sb.toString();

            fis.close();
            br.close();
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }


        Button btn_delete= findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                for(int i=index; i<(count-1) ;i++) {
                    try {
                        fis = openFileInput("trip" + (i + 1) + ".txt");
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
                        // shifting from files to last files so the actual number of files now decrese also maintaing indexes
                        FileOutputStream fout = openFileOutput("trip"+i+".txt", MODE_PRIVATE);
                        fout.write(txt.getBytes());
                        fout.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                File root = getFilesDir();
                File f= new File( root+"/trip"+(count-1)+".txt");
                f.delete();
                finish();



            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();

        LinearLayout l = findViewById(R.id.layoutll);
        l.removeAllViews();
    }
}