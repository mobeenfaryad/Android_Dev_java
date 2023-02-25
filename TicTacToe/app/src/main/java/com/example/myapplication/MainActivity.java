package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public abstract class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


//int status_array[]={ 2,2,2,2,2,2,2,2,2};
/*

    Button btn1 = (Button)findViewById(R.id.btn_1);
    Button btn2 = (Button)findViewById(R.id.btn_2);
    Button btn3 = (Button)findViewById(R.id.btn_3);
    Button btn4 = (Button)findViewById(R.id.btn_4);
    Button btn5 = (Button)findViewById(R.id.btn_5);
    Button btn6 = (Button)findViewById(R.id.btn_6);
    Button btn7 = (Button)findViewById(R.id.btn_7);
    Button btn8 = (Button)findViewById(R.id.btn_8);
    Button btn9 = (Button)findViewById(R.id.btn_9);


/*

    public void reset ( )
    {
        for( int a=0 ;a<9 ;a++)
        {
            status_array[a]=2;
        }
        btn1.setText('-');
        btn2.setText('-');
        btn3.setText('-');
        btn4.setText('-');
        btn5.setText('-');
        btn6.setText('-');
        btn7.setText('-');
        btn8.setText('-');
        btn9.setText('-');

    }


    int turn_flag =0;// 0 is for X

    public void press_button (View v )
    {
        Button  temp = (Button) v;
        if(turn_flag==0)
        {
            temp.setText('X');

            String  s = getResources().getResourceName(temp.getId());
            int id = 0;//(int) s.charAt(4) ;
            status_array[id]=0;
            Toast.makeText(this, id, Toast.LENGTH_LONG).show();
            }



        }

/*
        //= (Bu)findViewById(R.id.number1) ;//geting number 1 object of edit text
        //EditText f = (EditText)findViewById(R.id.number2) ;//geting number 2 object of edit text

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

    }*/

}