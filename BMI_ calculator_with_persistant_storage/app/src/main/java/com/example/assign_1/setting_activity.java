package com.example.assign_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class setting_activity extends AppCompatActivity {

    SharedPreferences pref1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button btn1 =findViewById(R.id.btn_metric);
        Button btn2 = findViewById(R.id.btn_imperial);

        pref1= getSharedPreferences("SYSTEM", Context.MODE_PRIVATE);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    SharedPreferences.Editor editor = pref1.edit();
                    editor.putString("SYSTEM","METRIC" );
                    editor.commit();
                }

                catch (Exception e)
                {
                    e.getStackTrace();
                }
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    SharedPreferences.Editor editor = pref1.edit();
                    editor.putString("SYSTEM","IMPERIAL" );
                    editor.commit();

                }
                catch (Exception e ){
                    e.getStackTrace();
                }
                finish();// to finish activity
            }
        });
    }
}