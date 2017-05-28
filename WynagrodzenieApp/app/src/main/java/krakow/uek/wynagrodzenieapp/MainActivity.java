package krakow.uek.wynagrodzenieapp;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void zawodyClick(View view)
    {
        Intent intencjaZawody = new Intent(this, Zawody.class);
        startActivity(intencjaZawody);
    }

    public void krajeClick(View view)
    {
            Intent intencjaKraje = new Intent(this, Kraje.class);
            startActivity(intencjaKraje);
    }

    public void sredniaKrajowaClick(View view)
    {
        Intent intencjaSredniaKrajowa = new Intent(this, SredniaKrajowa.class);
        startActivity(intencjaSredniaKrajowa);
    }

    public void przeliczanieClick(View view)
    {
        Intent intencjaPrzeliczanie = new Intent(this, PrzeliczanieWynagrodzenia.class);
        startActivity(intencjaPrzeliczanie);
    }

    public void wydatkiClick(View view)
    {
        Intent intencjaOdliczenieWydatkow = new Intent(this, OdliczenieWydatkow.class);
        startActivity(intencjaOdliczenieWydatkow);
    }

    public void oszczedzanieClick(View view)
    {
        Intent intencjaOszczedzanie = new Intent(this, Oszczedzanie.class);
        startActivity(intencjaOszczedzanie);
    }

    public void obliczanieClick(View view)
    {
        Intent intencjaObliczanie = new Intent(this, Obliczanie.class);
        startActivity(intencjaObliczanie);
    }

    public void aboutClick(View view)
    {
        Uri urlStrony = Uri.parse("https://plasma0.github.io/salaryproject/");
        Intent stronaIntencja = new Intent(Intent.ACTION_VIEW, urlStrony);
        startActivity(stronaIntencja);
    }
}
