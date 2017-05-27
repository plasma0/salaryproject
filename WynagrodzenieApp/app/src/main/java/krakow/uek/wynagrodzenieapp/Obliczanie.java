package krakow.uek.wynagrodzenieapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Obliczanie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obliczanie);

        ((EditText)findViewById(R.id.editText9)).setVisibility(View.INVISIBLE);
        ((EditText)findViewById(R.id.editText8)).setVisibility(View.INVISIBLE);
        ((EditText)findViewById(R.id.editText7)).setVisibility(View.INVISIBLE);
        ((EditText)findViewById(R.id.editText6)).setVisibility(View.INVISIBLE);
        ((EditText)findViewById(R.id.editText)).setVisibility(View.INVISIBLE);
        ((EditText)findViewById(R.id.editText2)).setVisibility(View.INVISIBLE);
        ((EditText)findViewById(R.id.editText3)).setVisibility(View.INVISIBLE);
        state = 0;
    }

    public Integer state;

    public void adder(View view) {

        if(state<6) {
            state++;
            switch (state) {
                case 1:
                    ((EditText) findViewById(R.id.editText9)).setVisibility(View.VISIBLE);
                    break;
                case 2:
                    ((EditText) findViewById(R.id.editText8)).setVisibility(View.VISIBLE);
                    break;
                case 3:
                    ((EditText) findViewById(R.id.editText7)).setVisibility(View.VISIBLE);
                    break;
                case 4:
                    ((EditText) findViewById(R.id.editText6)).setVisibility(View.VISIBLE);
                    break;
                case 5:
                    ((EditText) findViewById(R.id.editText)).setVisibility(View.VISIBLE);
                    break;
                case 6:
                    ((EditText) findViewById(R.id.editText2)).setVisibility(View.VISIBLE);
                    break;
                case 7:
                    ((EditText) findViewById(R.id.editText3)).setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }

            ((TextView) findViewById(R.id.numx)).setText(state.toString());
        }

    }

    public void deleter(View view) {
        if(state>0)
        {
            state--;
            switch (state)
            {
                case 0:EditText m0 = (EditText)findViewById(R.id.editText9);
                    m0.setVisibility(View.INVISIBLE);
                    m0.setText("0");
                    break;
                case 1:EditText m1 = (EditText)findViewById(R.id.editText8);
                    m1.setVisibility(View.INVISIBLE);
                    m1.setText("0");
                    break;
                case 2: EditText m2 = (EditText)findViewById(R.id.editText7);
                    m2.setVisibility(View.INVISIBLE);
                    m2.setText("0");
                    break;
                case 3:EditText m3 = (EditText)findViewById(R.id.editText6);
                    m3.setVisibility(View.INVISIBLE);
                    m3.setText("0");
                    break;
                case 4:EditText m4 = (EditText)findViewById(R.id.editText);
                    m4.setVisibility(View.INVISIBLE);
                    m4.setText("0");
                    break;
                case 5:EditText m5 = (EditText)findViewById(R.id.editText2);
                    m5.setVisibility(View.INVISIBLE);
                    m5.setText("0");
                    break;
                case 6:EditText m6 = (EditText)findViewById(R.id.editText3);
                    m6.setVisibility(View.INVISIBLE);
                    m6.setText("0");
                default:break;
            }

            ((TextView)findViewById(R.id.numx)).setText(state.toString());
        }
    }

    public void calculate(View view) {
        SharedPreferences SP = this.getSharedPreferences("main",this.MODE_PRIVATE);
        SharedPreferences.Editor SPe = SP.edit();
        float summa = 0;
        summa += Float.parseFloat(((EditText)findViewById(R.id.editText9)).getText().toString());
        summa += Float.parseFloat(((EditText)findViewById(R.id.editText8)).getText().toString());
        summa += Float.parseFloat(((EditText)findViewById(R.id.editText7)).getText().toString());
        summa += Float.parseFloat(((EditText)findViewById(R.id.editText6)).getText().toString());
        summa += Float.parseFloat(((EditText)findViewById(R.id.editText2)).getText().toString());
        summa += Float.parseFloat(((EditText)findViewById(R.id.editText3)).getText().toString());
        summa += Float.parseFloat(((EditText)findViewById(R.id.editText)).getText().toString());
        SPe.putFloat("isalary",summa);
        SPe.putInt("members",state);
        SPe.commit();
        Intent i = new Intent(getApplicationContext(),ObliczanieSummary.class);
        startActivity(i);

    }
}
