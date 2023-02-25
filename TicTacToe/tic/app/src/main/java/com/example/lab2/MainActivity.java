package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int status_array[]={ 2,2,2,2,2,2,2,2,2};
   Button btn_reset ,btn1,btn2,btn3,btn4,btn5,btn6, btn7,btn8,btn9;
   int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1 = (Button)findViewById(R.id.btn_1);
        btn2 = (Button)findViewById(R.id.btn_2);
        btn3 = (Button)findViewById(R.id.btn_3);
        btn4 = (Button)findViewById(R.id.btn_4);
        btn5 = (Button)findViewById(R.id.btn_5);
        btn6 = (Button)findViewById(R.id.btn_6);
        btn7 = (Button)findViewById(R.id.btn_7);
        btn8 = (Button)findViewById(R.id.btn_8);
        btn9 = (Button)findViewById(R.id.btn_9);
        btn_reset= (Button)findViewById(R.id.btn_reset);
 btn_reset.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
          reset();
     }
 });

    }
void disable ()
{

    btn1.setEnabled(false);
    btn2.setEnabled(false);
    btn3.setEnabled(false);
    btn4.setEnabled(false);
    btn5.setEnabled(false);
    btn6.setEnabled(false);
    btn7.setEnabled(false);
    btn8.setEnabled(false);
    btn9.setEnabled(false);
}


    public void reset ( )
    {
        turn_flag=0;
        count=0;
for (int a=0;a<9 ;a++)
{
    status_array[a]=2;
}
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);

        btn1.setText("-");
        btn2.setText("-");
        btn3.setText("-");
        btn4.setText("-");
        btn5.setText("-");
        btn6.setText("-");
        btn7.setText("-");
        btn8.setText("-");
        btn9.setText("-");

    }

public void win_check ()
{
    if(status_array[0]==0 & status_array[1]==0 & status_array[2] ==0)
    {
        Toast.makeText(this," X wins   " , Toast.LENGTH_LONG).show();
        disable();
    }

    else if ( status_array[3]==0 & status_array[4]==0 & status_array[5] ==0)
    {
        Toast.makeText(this," X wins   " , Toast.LENGTH_LONG).show();
        disable();
    }
    else if ( status_array[6]==0 & status_array[7]==0 & status_array[8] ==0)
    {
        Toast.makeText(this," X wins   " , Toast.LENGTH_LONG).show();
        disable();
    }

    else if(status_array[0]==0 & status_array[3]==0 & status_array[6] ==0)
    {
        Toast.makeText(this," X wins   " , Toast.LENGTH_LONG).show();
        disable();
    }

    else if ( status_array[1]==0 & status_array[4]==0 & status_array[7] ==0)
    {
        Toast.makeText(this," X wins   " , Toast.LENGTH_LONG).show();
        disable();
    }
    else if ( status_array[2]==0 & status_array[5]==0 & status_array[8] ==0)
    {
        Toast.makeText(this," X wins   " , Toast.LENGTH_LONG).show();
        disable();
    }



    else if ( status_array[0]==0 & status_array[4]==0 & status_array[8] ==0)
    {
        Toast.makeText(this," X wins   " , Toast.LENGTH_LONG).show();
        disable();
    }
    else if ( status_array[2]==0 & status_array[4]==0 & status_array[6] ==0)
    {
        Toast.makeText(this," X wins   " , Toast.LENGTH_LONG).show();
        disable();
    }
    else if(status_array[0]==1 & status_array[1]==1 & status_array[2] ==1)
    {

        Toast.makeText(this," O wins   " , Toast.LENGTH_LONG).show();
        disable();
    }
    else if(status_array[3]==1 & status_array[4]==1 & status_array[5] ==1)
    {

        Toast.makeText(this," O wins   " , Toast.LENGTH_LONG).show();
        disable();
    }

    else if(status_array[6]==1 & status_array[7]==1 & status_array[8] ==1)
    {

        Toast.makeText(this," O wins   " , Toast.LENGTH_LONG).show();
        disable();
    }
    else if(status_array[0]==1 & status_array[4]==1 & status_array[8] ==1)
    {

        Toast.makeText(this," O wins   " , Toast.LENGTH_LONG).show();
        disable();
    }
    else if(status_array[2]==1 & status_array[4]==1 & status_array[6] ==1)
    {

        Toast.makeText(this," O wins   " , Toast.LENGTH_LONG).show();
        disable();
    }

    else if(status_array[0]==1 & status_array[3]==1 & status_array[6] ==1)
    {
        Toast.makeText(this," O wins   " , Toast.LENGTH_LONG).show();
        disable();
    }

    else if ( status_array[1]==1 & status_array[4]==1 & status_array[7] ==1)
    {
        Toast.makeText(this," O wins   " , Toast.LENGTH_LONG).show();
        disable();

    }
    else if ( status_array[2]==1 & status_array[5]==1 & status_array[8] ==1)
    {
        Toast.makeText(this," O wins   " , Toast.LENGTH_LONG).show();
        disable();

    }
    else if (count ==9 )
    {
        Toast.makeText(this," Draw   " , Toast.LENGTH_LONG).show();
        disable();
    }

}


    int turn_flag =0;// 0 is for X

    public void press_button ( View v )
    {
        Button  temp = (Button) v;
        if(turn_flag==0)
        {
            temp.setText("X");
            String  s = getResources().getResourceName(temp.getId());
            int id = (Integer.parseInt(String.valueOf(s.charAt(s.length()-1)) ));

            status_array[id-1]= 0;
            turn_flag=1;
            temp.setEnabled(false);
        }
        else
        {
            temp.setText("O");
            String  s = getResources().getResourceName(temp.getId());
            int id = (Integer.parseInt(String.valueOf(s.charAt(s.length()-1)) ));
            status_array[id-1]= 1;
            turn_flag=0;
            temp.setEnabled(false);
        }
        count ++ ;
        win_check();
    }
}