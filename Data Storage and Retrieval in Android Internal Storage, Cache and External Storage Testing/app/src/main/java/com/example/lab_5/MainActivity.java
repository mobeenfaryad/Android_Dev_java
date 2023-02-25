package com.example.lab_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    char [] array;// temprary array to dump data into our storage
    int mb_1 = 	1048576;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        array = new char[mb_1];   //200 mB in bytes
        for( int i=0; i<array.length; i++)
        {
            array[i] = '6';
        }


        Button internal, external, cache; // button objects
        internal  = findViewById(R.id.btnInternal);
        external = findViewById(R.id.btnExternal);
        cache = findViewById(R.id.btnCache);

        external.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                    File testfile = new File(dir, "mytestfile.txt");
                    FileWriter fw = new FileWriter(testfile);
                    fw.write( "Data written successfully");
                    fw.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            }
        });



        cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    File dir = getCacheDir();
                    File f = new File(dir,"mycachefile.txt");
                    FileWriter fw = new FileWriter(f);

                    for(int k=0;k<100;k++) //writing 1 mB array 100 times to make 100mB
                    {
                        fw.write(array);

                    }
                    fw.close();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
        internal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread t = new Thread(new Runnable() { // just made thread so main UI don't slow
                    @Override
                    public void run() {
                        try {
                            File dir = getFilesDir();
                            File internalFile = new File(dir, "myinternalfile.txt");
                            FileWriter fw = new FileWriter(internalFile);
                            for(int k=0;k<200;k++) //writing 1 mB array 200 times to make 100mB
                            {
                                fw.write(array);

                            }
                            fw.close();

                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();

            }
        });

    }
}