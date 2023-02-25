package com.example.lab13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void press_button (View v )
    {
     EditText e = (EditText)findViewById(R.id.number1) ;//geting number 1 object of edit text
     EditText f = (EditText)findViewById(R.id.number2) ;//geting number 2 object of edit text

        String s1 =e.getText().toString();  // so to check is input is corerct or not we have to check all string
        String s2 = f.getText().toString();
        int count1=0;

        for(int i=0 ;i<s1.length() ;i++)
        {
            if(Character.isDigit(s1.charAt(i)))//counting number of digits in string
            {
                count1=count1+1;
            }
        }
        int count2=0;
        for(int i=0 ;i<s2.length() ;i++)
        {
            if(Character.isDigit(s2.charAt(i)))
            {
                count2=count2+1;
            }
        }


        if(count1!=s1.length() || count2!=s2.length())//now matching for total number of digits are equal to total length of strings
        {
            Toast.makeText(this, "You have Enter Other then a number !!", Toast.LENGTH_LONG).show();

        }
        else
        {
            int numb1= (Integer.parseInt( s1));  // converting that string to int
            int numb2 = (Integer.parseInt(s2 ));


            TextView t = findViewById(R.id.answerid);//geting textview to show the answer
            int numb3 = numb1+numb2;
            t.setText("The Sum is : "+numb3);//showing result to text field

        }

    }
}