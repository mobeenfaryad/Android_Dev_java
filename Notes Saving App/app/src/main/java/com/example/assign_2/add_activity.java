package com.example.assign_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class add_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Intent intent = getIntent();
        int count = intent.getIntExtra("Count" , 0 );

        Button save = findViewById(R.id.btn_save);
        TextView text = findViewById(R.id.entered_text);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String s ;
                    s= text.getText().toString();// geting data from text view
                        FileOutputStream fout = openFileOutput("note"+count+".txt", MODE_PRIVATE);
                        fout.write(s.getBytes());
                        fout.close();
                }
                catch (Exception e )
                {
                    e.getStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Input ", Toast.LENGTH_SHORT);
                }

                finish();

            }
        });

    }
}