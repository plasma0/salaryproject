package krakow.uek.wynagrodzenieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OdliczenieWydatkow extends AppCompatActivity
{
    EditText wysWynagEditText, pradEditText, gazEditText, wodaEditText, czynszEditText, telefonEditText;
    EditText  internetEditText, zywnoscEditText, ratyEditText, podatkiEditText, paliwoEditText, odziezEditText;
    EditText chemiaEditText, rezultatEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.odliczenie_wydatkow);

        wysWynagEditText = (EditText) findViewById(R.id.wysWynagEditText);

        pradEditText = (EditText) findViewById(R.id.pradEditText);
        gazEditText = (EditText) findViewById(R.id.gazEditText);
        wodaEditText = (EditText) findViewById(R.id.wodaEditText);
        czynszEditText = (EditText) findViewById(R.id.czynszEditText);
        telefonEditText = (EditText) findViewById(R.id.telefonEditText);
        internetEditText = (EditText) findViewById(R.id.internetEditText);
        zywnoscEditText = (EditText) findViewById(R.id.zywnoscEditText);
        ratyEditText = (EditText) findViewById(R.id.ratyEditText);
        podatkiEditText = (EditText) findViewById(R.id.podatkiEditText);
        paliwoEditText = (EditText) findViewById(R.id.paliwoEditText);
        odziezEditText = (EditText) findViewById(R.id.odziezEditText);
        chemiaEditText = (EditText) findViewById(R.id.chemiaEditText);

        rezultatEditText  = (EditText) findViewById(R.id.rezultatEditText);

    }


    public void obliczOszczednosci(View view)
    {
        if(wysWynagEditText.getText().toString().matches("")) Toast.makeText(this, "Podaj wysokość wynagrodzenia", Toast.LENGTH_SHORT).show();
        if(pradEditText.getText().toString().matches("")) pradEditText.setText("0");
        if(gazEditText.getText().toString().matches("")) gazEditText.setText("0");
        if(wodaEditText.getText().toString().matches("")) wodaEditText.setText("0");
        if(czynszEditText.getText().toString().matches("")) czynszEditText.setText("0");
        if(telefonEditText.getText().toString().matches("")) telefonEditText.setText("0");
        if(internetEditText.getText().toString().matches("")) internetEditText.setText("0");
        if(zywnoscEditText.getText().toString().matches("")) zywnoscEditText.setText("0");
        if(ratyEditText.getText().toString().matches("")) ratyEditText.setText("0");
        if(podatkiEditText.getText().toString().matches("")) podatkiEditText.setText("0");
        if(paliwoEditText.getText().toString().matches("")) paliwoEditText.setText("0");
        if(odziezEditText.getText().toString().matches("")) odziezEditText.setText("0");
        if(chemiaEditText.getText().toString().matches("")) chemiaEditText.setText("0");

        double temp = Double.parseDouble(wysWynagEditText.getText().toString()) - Double.parseDouble(pradEditText.getText().toString()) -
                Double.parseDouble(gazEditText.getText().toString()) - Double.parseDouble(wodaEditText.getText().toString()) -
                Double.parseDouble(czynszEditText.getText().toString()) - Double.parseDouble(telefonEditText.getText().toString()) -
                Double.parseDouble(internetEditText.getText().toString()) - Double.parseDouble(zywnoscEditText.getText().toString()) -
                Double.parseDouble(ratyEditText.getText().toString()) - Double.parseDouble(podatkiEditText.getText().toString()) -
                Double.parseDouble(paliwoEditText.getText().toString()) - Double.parseDouble(odziezEditText.getText().toString()) -
                Double.parseDouble(chemiaEditText.getText().toString()) ;
        rezultatEditText.setText(""+temp);
    }
}
