package com.example.assign_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private static DecimalFormat REAL_FORMATTER = new DecimalFormat("0.###");
    SharedPreferences pref1,sys_string , bmi_string , height_string , weight_string ,stat_string;

    String sys; // to trace system type
    double bmi;  // bmi value
    double height_meters;  // to trace height in meters
    double weigh_kg;   // to trace weights in kg
    String status_string;  // to trace the status of user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref1= getSharedPreferences("SYSTEM", Context.MODE_PRIVATE);
        try {
            sys = pref1.getString("SYSTEM", "nahi mila");
        } catch (Exception e) {
            e.getStackTrace();
        }

        TextView height = findViewById(R.id.text_height);
        TextView weight = findViewById(R.id.text_weight);
        Button btn_cal = findViewById(R.id.btn_calculate);
        TextView BMI = findViewById(R.id.textview_bmi);
        TextView status = findViewById( R.id.textview_status);
        ImageButton btn_setting = findViewById(R.id.btn_setting);
        TextView var_tex_height = findViewById(R.id.textView_height_shift);
        TextView var_tex_weight = findViewById(R.id.textView_weight_shift);


        // to show the system units
        if(sys.equals("IMPERIAL"))
        {
            var_tex_height.setText("Inches");
            var_tex_weight.setText("Pounds");
        }
        else
        {
            var_tex_height.setText("Centimeters");
            var_tex_weight.setText("Kilograms");
        }

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this, setting_activity.class);
                startActivity(i);
            }
        });

        // reste button

        ImageButton btn3_reset = findViewById(R.id.btn_reset);
        btn3_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                bmi_string =getSharedPreferences("BMI", Context.MODE_PRIVATE);
                height_string =getSharedPreferences("HEIGHT", Context.MODE_PRIVATE);
                weight_string =getSharedPreferences("WEIGHT", Context.MODE_PRIVATE);
                stat_string=getSharedPreferences("STATUS", Context.MODE_PRIVATE);

                height.setText("");
                weight.setText("");
                BMI.setText("");
                status.setText("");

                sys="METRIC";
                bmi=0;
                weigh_kg=0;
                height_meters=0;


                var_tex_height.setText("Centimeters");
                var_tex_weight.setText("Kilograms");

                //bmi ccommited in save file
                SharedPreferences.Editor editor = bmi_string.edit();
                editor.putString("BMI","" );
                editor.commit();

                //BMI commited
                editor = height_string.edit();
                editor.putString("HEIGHT","" );
                editor.commit();

                //weight commited
                editor = weight_string.edit();
                editor.putString("WEIGHT","");
                editor.commit();

                //STatus commited
                editor = stat_string.edit();
                editor.putString("STATUS","" );
                editor.commit();

                editor = pref1.edit();
                editor.putString("SYSTEM", "");
                editor.commit();


            }
        });






        // caalculation button
        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sys.equals("IMPERIAL"))
                {
              try{
                  double weigh_punds = Double.parseDouble(weight.getText().toString());
                  double height_inches = Double.parseDouble(height.getText().toString());
                  double height_meters = height_inches*0.0254;
                  weigh_kg =weigh_punds*0.453592;
                  bmi = weigh_kg/(height_meters*height_meters);
                  BMI.setText(REAL_FORMATTER.format(bmi) +"");

              }
              catch (Exception e)
              {
                  Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Correct Numbers", Toast.LENGTH_SHORT);
                  toast.show();
                  e.getStackTrace();
              }
                }
                else
                {
                    try{
                        double weigh_kg = Double.parseDouble(weight.getText().toString());
                        double height_centi = Double.parseDouble(height.getText().toString());
                        height_meters= height_centi/100;
                        bmi = weigh_kg/(height_meters*height_meters);
                        BMI.setText(REAL_FORMATTER.format(bmi) +"");
                    }
                    catch (Exception e)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Correct Numbers", Toast.LENGTH_SHORT);
                        toast.show();
                        e.getStackTrace();
                    }
                }
                if (bmi <18.5)
                {
                    status.setText("Under Weight ");
                    status_string ="Under Weight ";
                }
                else if ( bmi >=18.5 & bmi <=24.9)
                {
                    status.setText("Normal Weight ");
                    status_string= "Normal Weight ";
                }
                else if ( bmi >=25 & bmi <=29.9)
                {
                    status.setText("Over Weight ");
                    status_string= "Over Weight ";
                }
                else if ( bmi >30)
                {
                    status.setText("Obesity ");
                    status_string="Obesity ";
                }
            }
        });
    }



    //on pause save all variable in our sharedprefrances
    @Override
    protected void onPause() {
        super.onPause();
        TextView height = findViewById(R.id.text_height);
        TextView weight = findViewById(R.id.text_weight);
        Button btn_cal = findViewById(R.id.btn_calculate);
        TextView BMI = findViewById(R.id.textview_bmi);
        TextView status = findViewById( R.id.textview_status);
        ImageButton btn_setting = findViewById(R.id.btn_setting);
        TextView var_tex_height = findViewById(R.id.textView_height_shift);
        TextView var_tex_weight = findViewById(R.id.textView_weight_shift);




        // reste button

        ImageButton btn3_reset = findViewById(R.id.btn_reset);
        btn3_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                bmi_string =getSharedPreferences("BMI", Context.MODE_PRIVATE);
                height_string =getSharedPreferences("HEIGHT", Context.MODE_PRIVATE);
                weight_string =getSharedPreferences("WEIGHT", Context.MODE_PRIVATE);
                stat_string=getSharedPreferences("STATUS", Context.MODE_PRIVATE);

                height.setText("");
                weight.setText("");
                BMI.setText("");
                status.setText("");

                sys="METRIC";
                bmi=0;
                weigh_kg=0;
                height_meters=0;


                var_tex_height.setText("Centimeters");
                var_tex_weight.setText("Kilograms");

                //bmi ccommited in save file
                SharedPreferences.Editor editor = bmi_string.edit();
                editor.putString("BMI","" );
                editor.commit();

                //BMI commited
                editor = height_string.edit();
                editor.putString("HEIGHT","" );
                editor.commit();

                //weight commited
                editor = weight_string.edit();
                editor.putString("WEIGHT","");
                editor.commit();

                //STatus commited
                editor = stat_string.edit();
                editor.putString("STATUS","" );
                editor.commit();

                editor = pref1.edit();
                editor.putString("SYSTEM", "");
                editor.commit();


            }
        });



        // making all sharedprefernace variable and saving then to call back in resume
        bmi_string =getSharedPreferences("BMI", Context.MODE_PRIVATE);
        height_string =getSharedPreferences("HEIGHT", Context.MODE_PRIVATE);
        weight_string =getSharedPreferences("WEIGHT", Context.MODE_PRIVATE);
        stat_string=getSharedPreferences("STATUS", Context.MODE_PRIVATE);

        //saving system setting
        SharedPreferences.Editor editor = pref1.edit();
        editor.putString("SYSTEM",sys );
        editor.commit();

        //bmi ccommited in save file
        editor = bmi_string.edit();
        editor.putString("BMI",BMI.getText().toString() );
        editor.commit();

       //BMI commited
        editor = height_string.edit();
        editor.putString("HEIGHT",height.getText().toString() );
        editor.commit();

       //weight commited
        editor = weight_string.edit();
        editor.putString("WEIGHT",weight.getText().toString() );
        editor.commit();

        //STatus commited
        editor = stat_string.edit();
        editor.putString("STATUS",status.getText().toString() );
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView height = findViewById(R.id.text_height);
        TextView weight = findViewById(R.id.text_weight);
        Button btn_cal = findViewById(R.id.btn_calculate);
        TextView BMI = findViewById(R.id.textview_bmi);
        TextView status = findViewById( R.id.textview_status);
        ImageButton btn_setting = findViewById(R.id.btn_setting);
        TextView var_tex_height = findViewById(R.id.textView_height_shift);
        TextView var_tex_weight = findViewById(R.id.textView_weight_shift);







        //   making all sharedprefrances variables to get back al saved data
        bmi_string =getSharedPreferences("BMI", Context.MODE_PRIVATE);
        height_string =getSharedPreferences("HEIGHT", Context.MODE_PRIVATE);
        weight_string =getSharedPreferences("WEIGHT", Context.MODE_PRIVATE);
        stat_string=getSharedPreferences("STATUS", Context.MODE_PRIVATE);
        sys = pref1.getString("SYSTEM", "nahi mila");


        BMI.setText(   bmi_string.getString("BMI",""));
        height.setText(height_string.getString("HEIGHT", "") );
        weight.setText(weight_string.getString("WEIGHT", "") );
        status.setText(stat_string.getString("STATUS", ""));

        pref1= getSharedPreferences("SYSTEM", Context.MODE_PRIVATE);
        try {
            sys = pref1.getString("SYSTEM", "");
        } catch (Exception e) {
            e.getStackTrace();
        }
        if(sys.equals("IMPERIAL"))
        {
            var_tex_height.setText("Inches");
            var_tex_weight.setText("Pounds");
        }
        else
        {
            var_tex_height.setText("Centimeters");
            var_tex_weight.setText("Kilograms");
        }

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this, setting_activity.class);
                startActivity(i);
            }
        });
        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sys.equals("IMPERIAL"))
                {
                    try{
                        double weigh_punds = Double.parseDouble(weight.getText().toString());
                        double height_inches = Double.parseDouble(height.getText().toString());
                        double height_meters = height_inches*0.0254;
                        weigh_kg =weigh_punds*0.453592;
                        bmi = weigh_kg/(height_meters*height_meters);
                        BMI.setText(REAL_FORMATTER.format(bmi) +"");

                    }
                    catch (Exception e)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Correct Numbers", Toast.LENGTH_SHORT);
                        toast.show();
                        e.getStackTrace();

                    }
                }
                else {

                    try {

                        double weigh_kg = Double.parseDouble(weight.getText().toString());
                        double height_centi = Double.parseDouble(height.getText().toString());
                        height_meters = height_centi / 100;
                        bmi = weigh_kg / (height_meters * height_meters);
                        BMI.setText(REAL_FORMATTER.format(bmi) + "");
                    } catch (Exception e) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Correct Numbers", Toast.LENGTH_SHORT);
                        toast.show();
                        e.getStackTrace();
                    }
                }
                if (bmi <18.5)
                {
                    status.setText("Under Weight ");
                    status_string ="Under Weight ";
                }
                else if ( bmi >=18.5 & bmi <=24.9)
                {
                    status.setText("Normal Weight ");
                    status_string= "Normal Weight ";
                }
                else if ( bmi >=25 & bmi <=29.9)
                {
                    status.setText("Over Weight ");
                    status_string= "Over Weight ";
                }
                else if ( bmi >30)
                {
                    status.setText("Obesity ");
                    status_string="Obesity ";
                }
            }
        });


        // reste button

        ImageButton btn3_reset = findViewById(R.id.btn_reset);
        btn3_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                bmi_string =getSharedPreferences("BMI", Context.MODE_PRIVATE);
                height_string =getSharedPreferences("HEIGHT", Context.MODE_PRIVATE);
                weight_string =getSharedPreferences("WEIGHT", Context.MODE_PRIVATE);
                stat_string=getSharedPreferences("STATUS", Context.MODE_PRIVATE);

                height.setText("");
                weight.setText("");
                BMI.setText("");
                status.setText("");

                sys="METRIC";
                bmi=0;
                weigh_kg=0;
                height_meters=0;


                var_tex_height.setText("Centimeters");
                var_tex_weight.setText("Kilograms");

                //bmi ccommited in save file
                SharedPreferences.Editor editor = bmi_string.edit();
                editor.putString("BMI","" );
                editor.commit();

                //BMI commited
                editor = height_string.edit();
                editor.putString("HEIGHT","" );
                editor.commit();

                //weight commited
                editor = weight_string.edit();
                editor.putString("WEIGHT","");
                editor.commit();

                //STatus commited
                editor = stat_string.edit();
                editor.putString("STATUS","" );
                editor.commit();

                editor = pref1.edit();
                editor.putString("SYSTEM", "");
                editor.commit();


            }
        });
    }

}
