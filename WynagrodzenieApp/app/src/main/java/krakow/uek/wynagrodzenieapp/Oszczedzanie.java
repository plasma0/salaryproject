package krakow.uek.wynagrodzenieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Oszczedzanie extends AppCompatActivity
{
    EditText kwotaEditText, dochodEditText, wydatkiEditText;
    EditText rezultatZEditText, rezultatBezEditTextt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oszczedzanie);

        kwotaEditText = (EditText) findViewById(R.id.kwotaEditText);
        dochodEditText = (EditText) findViewById(R.id.dochodEditText);
        wydatkiEditText = (EditText) findViewById(R.id.wydatkiEditText);
        rezultatZEditText = (EditText) findViewById(R.id.rezultatZEditText);
        rezultatBezEditTextt = (EditText) findViewById(R.id.rezultatBezEditText);
    }

    public void oblicz(View view)
    {
        double kwota = Double.parseDouble(kwotaEditText.getText().toString());
        double dochod = Double.parseDouble(dochodEditText.getText().toString());
        double wydatki = Double.parseDouble(wydatkiEditText.getText().toString());

        int tempZ = (int) Math.ceil(kwota/(dochod - wydatki)) ;
        int tempBez = (int) Math.ceil(kwota/dochod) ;

        rezultatZEditText.setText(""+tempZ + " miesięcy uwzgledniajac wydatki");
        rezultatBezEditTextt.setText(""+tempBez + " miesięcy bez uwzglednienia wydatków");
    }
}
