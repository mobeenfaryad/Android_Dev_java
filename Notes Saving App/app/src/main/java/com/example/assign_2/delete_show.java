package com.example.assign_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class delete_show extends AppCompatActivity {

    String txt="";
    FileInputStream fis;
    BufferedReader br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_show);
        Button btn_back = findViewById(R.id.btn_Back);
        Button btn_delete = findViewById(R.id.btn_delete);
        EditText text = findViewById(R.id.edittext);
        Button btn_edit =findViewById(R.id.btn_edit);

        File root = getFilesDir();
        File[] files = root.listFiles();
        int count = files.length;

        Intent intent = getIntent();
        int index = intent.getIntExtra("Index" , 0 );

            try {
                fis = openFileInput("note" + (index) + ".txt");
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
            catch (Exception e)
            {
                e.getStackTrace();
            }

            text.setText(txt);

            btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish ();
            }
        });


         btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=index; i<(count-1) ;i++) {
                    try {
                        fis = openFileInput("note" + (i + 1) + ".txt");
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
                        FileOutputStream fout = openFileOutput("note"+i+".txt", MODE_PRIVATE);
                        fout.write(txt.getBytes());
                        fout.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                File root = getFilesDir();
                File f= new File( root+"/note"+(count-1)+".txt");
                f.delete();
                finish();

            }
        });

         btn_edit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 try {
                     FileOutputStream fout = openFileOutput("note"+index+".txt", MODE_PRIVATE);
                     String t;
                     t= text.getText().toString();
                         fout.write(t.getBytes());
                         fout.close();
                 }
                 catch (Exception e)
                 {
                     e.getStackTrace();
                 }
             }
         });
    }
}