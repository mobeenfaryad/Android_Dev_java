package com.example.assign4;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    int count;
    private static final DecimalFormat REAL_FORMATTER = new DecimalFormat("0.###");
    int permisisonCode = 555;
    String formattedDateTime;


    private boolean isRunning = false;
    double longi;
    double lati;
    private float[] accelerationValues = new float[3];
    private float[] lowPassAccelerationValues = new float[3];
    private float[] previousAccelerationValues = new float[3];
    private float[] nextAccelerationValues = new float[3];
    private float threshold = 10.0f;

    double speed;
    String  message ="";
    double X,Y,Z;
    double previousSpeed = 0;
    long previousTime = 0;
    LocationListener locationListener;
    SensorEventListener lstner;
    File root; //= getFilesDir();
    File[] files ;//= root.listFiles();
    String temp="";
    TextView txt_internal;
    LocalDateTime currentDateTime;// = LocalDateTime.now();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();








         txt_internal = findViewById(R.id.textview_internalmsg);
        Button trip =findViewById(R.id.btn_trips);



        trip.setOnClickListener(view -> {

            Intent i= new Intent(MainActivity.this, tripscreen.class);
            startActivity(i);
        });


        int a = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        int b = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (a != PackageManager.PERMISSION_GRANTED && b != PackageManager.PERMISSION_GRANTED) {
            String[] thepermissions = new String[1];
            thepermissions[0] = android.Manifest.permission.ACCESS_FINE_LOCATION;
            this.requestPermissions(thepermissions, permisisonCode);
            return;

        }
        LocationManager lm;
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        TextView txt_msg = findViewById(R.id.textViewtripnumber);

       // txt_msg.setText(sys);

        Button startButton = findViewById(R.id.btn_start);
        Button stopButton = findViewById(R.id.btn_stop);



        // Get the SensorManager system service
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get references to the sensors we want to use
        Sensor sensor_accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // Accelerometer sensor
         isRunning=false;

        startButton.setOnClickListener(v -> {
            if (!isRunning) {
                isRunning = true;
                 root = getFilesDir();
                 files = root.listFiles();
                count = files.length; // to get all total number of files
                String t ="Trip : " + (count + 1);
                txt_msg.setText(t);


                if (txt_internal == null) {
                    txt_internal = findViewById(R.id.textview_internalmsg);
                }
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                   // return;
                }
                Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);



                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener= location -> {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        currentDateTime = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma");
                        formattedDateTime = currentDateTime.format(formatter);
                    }



                    longi= location.getLatitude();
                    lati=location.getLongitude();
                    speed= location.getSpeed();
                    speed = speed  * 3.6;

                    speed = Double.parseDouble(REAL_FORMATTER.format(speed));

                    Log.d("*****","*******Last Known LOCATION:"+loc.getLatitude()+","+loc.getLongitude());



                    if ( speed >40 ) {
                        message = message+ longi+","+lati+"," +formattedDateTime +","+"Over Speed "+ speed+"km/h\n";
                       temp =temp+formattedDateTime+"-" + "Over Speed "+ speed+"km/h\n";
                        txt_internal.setText(temp);

                    }
                    sm.registerListener(lstner= new SensorEventListener() {
                        @Override
                        public void onSensorChanged(SensorEvent sensorEvent) {
                            // Update the TextView for the accelerometer sensor with the X, Y, and Z values
                            X= sensorEvent.values[0] ;
                            Y= sensorEvent.values[1];
                            Z= sensorEvent.values[2];

                            // Define a threshold for harsh acceleration
                            double accelerationThreshold = 49253.12; // km/h^2

// Initialize variables to store the previous speed and time



                            // Apply a low pass filter
                            lowPassAccelerationValues[0] += (X - lowPassAccelerationValues[0]) / 5;
                            lowPassAccelerationValues[1] += (Y - lowPassAccelerationValues[1]) / 5;
                            lowPassAccelerationValues[2] += (Z - lowPassAccelerationValues[2]) / 5;

                            // Detect peaks in the acceleration readings
                            if (previousAccelerationValues[0] < lowPassAccelerationValues[0] && lowPassAccelerationValues[0] > nextAccelerationValues[0]) {
                                // A peak in the positive direction indicates a harsh left turn
                                if (lowPassAccelerationValues[0] > threshold) {
                                    message = message+ longi+","+lati+"," +formattedDateTime +","+"Harsh Left Turn  \n";
                                    //txt_internal.append(formattedDateTime+"-" + "Harsh Left Turn  \n");
                                    temp =temp+formattedDateTime+"-" + "Harsh  Left Turn  \n";
                                    txt_internal.setText(temp);
                                }
                            } else if (previousAccelerationValues[0] > lowPassAccelerationValues[0] && lowPassAccelerationValues[0] < nextAccelerationValues[0]) {


                                if (lowPassAccelerationValues[0] < -threshold) {
                                    message = message+ longi+","+lati+"," +formattedDateTime +","+"Harsh Right Turn  \n";
                                    // txt_internal.append(formattedDateTime+"-" + "Harsh  Right Turn  \n");

                                    temp =temp+formattedDateTime+"-" + "Harsh  Right Turn  \n";
                                    txt_internal.setText(temp);
                                }}







// Get the current speed from GPS
                            double currentSpeed = speed; // You'll need to implement this method

// Get the current time
                            long currentTime = System.currentTimeMillis();

// Calculate the time difference between the previous and current speed readings
                            long timeDifference = currentTime - previousTime;

// Calculate the acceleration
                            double acceleration_ = (currentSpeed - previousSpeed) / Math.pow((timeDifference / 3600.0), 2);

// Check if the acceleration exceeds the threshold
                            if (acceleration_ > accelerationThreshold ) {
                                message = message+ longi+","+lati+"," +formattedDateTime +","+"Harsh Acceleration \n";
                                //txt_internal.append(formattedDateTime+"-" + "Harsh Acceleration \n");

                                temp =temp+formattedDateTime+"-" + "Harsh Acceleration \n";
                                txt_internal.setText(temp);
                            }


                            else if (acceleration_ < -accelerationThreshold) //4.572)
                            {
                                message = message+ longi+","+lati+"," +formattedDateTime +","+"Harsh Breaking  \n";
                             //   txt_internal.append(formattedDateTime+"-" + "Harsh Breaking  \n");

                                temp =temp+formattedDateTime+"-" + "Harsh Breaking  \n";
                                txt_internal.setText(temp);

                            }

// Store the current speed and time as the previous values for the next iteration
                            previousSpeed = currentSpeed;
                            previousTime = currentTime;




                        }

                        @Override
                        public void onAccuracyChanged(Sensor sensor, int i) {

                        }
                    }, sensor_accel, SensorManager.SENSOR_DELAY_NORMAL); // Set the delay to NORMAL





                });
            }




        });

        stopButton.setOnClickListener(v -> {

            lm.removeUpdates(locationListener);
         //   sm.unregisterListener(lstner,sensor_accel);


            if (isRunning) {
                isRunning = false;
                try{


                    FileOutputStream fout = openFileOutput("trip" + count + ".txt", MODE_PRIVATE);
                    fout.write(message.getBytes());
                    fout.close();

                }
                catch (Exception e) {
                    e.getStackTrace();
                }
                txt_msg.setText("");
                txt_internal.setText("");
                message="";
                temp="";



            }
        });

    }



}