package com.example.lab_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText et;
    SharedPreferences pref;
    ImageButton sent, reset;
    EditText message;
    String msg;

    @Override
    protected void onResume() {
        super.onResume();

/* task1
        try {

            et = findViewById(R.id.message);
            pref = getSharedPreferences("mydata", 0);
            String txt = pref.getString("mydata", "nahi mila");
            et.setText(txt);



        } */ //task 1


            try {
                FileInputStream fin = openFileInput("merilocalfile.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fin));
                String txt = br.readLine();
                et.setText(txt);
            }

       catch(Exception e)
        {
            e.printStackTrace();
        }
//task1




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.message);
        sent = findViewById(R.id.btn_sent);
        msg = et.getText().toString();
        /* task1
        pref = getSharedPreferences("mydata", 0);
        String txt = pref.getString("mydata", "nahi mila");
        et.setText(txt);


         */ // task1

        reset= findViewById(R.id.btn_reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                /* task1
                msg ="";
                et.setText("");

                //msg = et.getText().toString();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("mydata", msg);
                editor.commit();

*/ //task1
                try {
                    msg ="";
                    et.setText("");
                    FileOutputStream fos = openFileOutput("merilocalfile.txt", MODE_PRIVATE);
                    fos.write(et.getText().toString().getBytes());
                    fos.close();

                }
                catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // save data in preferances files
                /* task1
                msg = et.getText().toString();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("mydata", msg);
                editor.commit();


                 */ // task1
            }


        });

    }

    @Override
    protected void onPause() {
        super.onPause();



        try {
            /* task 1
            // save mesage to preferances files
            msg = et.getText().toString();
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("mydata", msg);
            editor.commit();


             */  //task 1


        /*
           // this is for RAW
            InputStream in = getResources().openRawResource(R.raw.mydata1);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str = br.readLine();
            Log.d("*****","*****DATA FROM RESOURCE FILE:"+str);

*/

            // opening file to write message in text view
           FileOutputStream fos = openFileOutput("merilocalfile.txt", MODE_PRIVATE);
            fos.write(et.getText().toString().getBytes());
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


       // Log.d("********", "*****DATA SAVED: " + et.getText().toString());


    }
}