package krakow.uek.wynagrodzenieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class PrzeliczanieWynagrodzenia extends AppCompatActivity
{

    RadioButton zlecenieRadioButton, dzieloRadioButton, praceRadioButton;
    RadioButton nettoRadioButton, bruttoRadioButton, kosztRadioButton;

    EditText kwotaEditText;
    EditText nettoEditText, bruttoEditText, kosztEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.przeliczanie_wynagrodzenia);

        zlecenieRadioButton = (RadioButton) findViewById(R.id.zlecenieRadioButton);
        dzieloRadioButton = (RadioButton) findViewById(R.id.dzieloRadioButton);
        praceRadioButton = (RadioButton) findViewById(R.id.praceRadioButton);

        nettoRadioButton = (RadioButton) findViewById(R.id.nettoRadioButton);
        bruttoRadioButton = (RadioButton) findViewById(R.id.bruttoRadioButton);
        kosztRadioButton = (RadioButton) findViewById(R.id.kosztRadioButton);

        kwotaEditText = (EditText) findViewById(R.id.kwotaEditText);

        nettoEditText = (EditText) findViewById(R.id.nettoEditText);
        bruttoEditText = (EditText) findViewById(R.id.bruttoEditText);
        kosztEditText = (EditText) findViewById(R.id.kosztEditText);
    }

    public void oblicz(View view)
    {
        double ubezpieczenieEmetyralne = 0.13078;
        double ubezpieczenieEmetyralne2 = 0.0976;
        double ubezpieczenieRentowe = 0.0201;
        double ubezpieczenieRentowe2 = 0.065;
        double ubezpieczenieRentowe3 = 0.015;
        double ubezpieczenieChrobowe = 0.03283;
        double ubezpieczenieChrobowe2 = 0.0245;
        double ubezpieczenieZdrowotne = 0.10406;
        double ubezpieczenieZdrowotne2 = 0.0777;
        double ubezpieczenieWypadkowe = 0.0179;
        double FonduszPracy = 0.0245;
        double FGSP = 0.0001;
        double PIT = 0.052;
        double PIT2 = 0.144;
        double PIT3 = 0.09;
        double PIT4 = 0.022;

        String text = kwotaEditText.getText().toString();
        double input = Double.parseDouble(text);

        if(zlecenieRadioButton.isChecked() && nettoRadioButton.isChecked())
        {
            nettoEditText.setText("" + String.format("%.2f", input));
            bruttoEditText.setText("" + String.format("%.2f", input * 1.168));
            kosztEditText.setText("" + String.format("%.2f", ((input * 1.168) + (input * 0.018))));
        }
        if (dzieloRadioButton.isChecked() && nettoRadioButton.isChecked())
        {
            nettoEditText.setText("" + String.format("%.2f", input));
            bruttoEditText.setText("" + String.format("%.2f", input * 1.0989));
            kosztEditText.setText("" + String.format("%.2f", (input * 1.0989)));
        }
        if (praceRadioButton.isChecked() && nettoRadioButton.isChecked())
        {
            nettoEditText.setText("" + String.format("%.2f", input));
            bruttoEditText.setText("" + String.format("%.2f", Math.round((input + (input * ubezpieczenieEmetyralne) + (input * ubezpieczenieRentowe ) +
                    (input * ubezpieczenieChrobowe ) + (input * ubezpieczenieZdrowotne ) + (input * PIT )) * 100d)/100d));
            double temp = input + (input * ubezpieczenieEmetyralne) + (input * ubezpieczenieRentowe ) +
                    (input * ubezpieczenieChrobowe ) + (input * ubezpieczenieZdrowotne ) + (input * PIT );
            kosztEditText.setText("" + String.format("%.2f", Math.round((temp + (input * ubezpieczenieEmetyralne) + temp * ubezpieczenieRentowe2 + temp * ubezpieczenieWypadkowe +
                    temp * FonduszPracy + temp * FGSP) * 100d)/100d));
        }
        if (zlecenieRadioButton.isChecked() && bruttoRadioButton.isChecked())
        {
            nettoEditText.setText("" + String.format("%.2f", (input - input * PIT2)));
            bruttoEditText.setText("" + String.format("%.2f", input));
            kosztEditText.setText("" + String.format("%.2f", input));
        }
        if (dzieloRadioButton.isChecked() && bruttoRadioButton.isChecked())
        {
            nettoEditText.setText("" + String.format("%.2f", (input - input * PIT3)));
            bruttoEditText.setText("" + String.format("%.2f", input));
            kosztEditText.setText("" + String.format("%.2f", input));
        }
        if (praceRadioButton.isChecked() && bruttoRadioButton.isChecked())
        {
            nettoEditText.setText("" + String.format("%.2f", Math.round((input - (input * ubezpieczenieEmetyralne2) - (input * ubezpieczenieRentowe3 ) -
                    (input * ubezpieczenieChrobowe2 ) - (input * ubezpieczenieZdrowotne2 ) - (input * PIT4 )) * 100d)/100d));
            bruttoEditText.setText("" + String.format("%.2f", input));
            kosztEditText.setText("" + String.format("%.2f", Math.round((input + (input * ubezpieczenieEmetyralne2) + (input * ubezpieczenieRentowe2 ) +
                    (input * ubezpieczenieWypadkowe ) + (input * FonduszPracy ) + (input * FGSP )) * 100d)/100d));
        }
        if (zlecenieRadioButton.isChecked() && kosztRadioButton.isChecked())
        {
            nettoEditText.setText("" + String.format("%.2f", Math.round((input - input * PIT2) * 100d)/100d));
            bruttoEditText.setText("" + String.format("%.2f", input));
            kosztEditText.setText("" + String.format("%.2f", input));
        }
        if (dzieloRadioButton.isChecked() && kosztRadioButton.isChecked())
        {
            nettoEditText.setText("" + String.format("%.2f", Math.round((input - input * PIT3) * 100d)/100d));
            bruttoEditText.setText("" + String.format("%.2f", input));
            kosztEditText.setText("" + String.format("%.2f", input));
        }
        if (praceRadioButton.isChecked() && kosztRadioButton.isChecked())
        {
            double temp = (double)Math.round(input/(1 + ubezpieczenieEmetyralne2 + ubezpieczenieRentowe2 + ubezpieczenieWypadkowe + FonduszPracy + FGSP)*100d)/100;

            nettoEditText.setText("" + Math.round((temp - temp * 0.00844 - temp * ubezpieczenieZdrowotne2 - temp * ubezpieczenieChrobowe2 -
                    temp * ubezpieczenieRentowe3 - temp * ubezpieczenieEmetyralne2) * 100d)/100);
            bruttoEditText.setText("" + String.format("%.2f", temp));
            kosztEditText.setText("" + String.format("%.2f", input));
        }



    }
}
