package krakow.uek.wynagrodzenieapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Zawody extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zawody);
        new WebT().execute(2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38);
        String[] voivod = {"dolnośląskie","kujawsko-pomorskie","lubelskie","lubuskie","łódzkie","małopolskie","mazowieckie","opolskie","podkarpackie","podlaskie","pomorskie","śląskie","świętokrzyskie","warmińsko-mazurskie","wielkopolskie","zachodnio-pomorskie"};
        ArrayAdapter<String> voi = new ArrayAdapter<String>(getBaseContext(),R.layout.support_simple_spinner_dropdown_item,voivod);
        ((Spinner)findViewById(R.id.spinner2)).setAdapter(voi);
    }

    public void calculate(View view) {
        String selection = ((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString();
        int salary = Integer.parseInt(((TextView)findViewById(R.id.editText)).getText().toString());
        SharedPreferences SP = getSharedPreferences("sprof", Context.MODE_PRIVATE);
        SharedPreferences.Editor SPe = SP.edit();
        SPe.putString("SELECT",selection);
        SPe.putInt("SALARY",salary);
        String viovodeship = ((Spinner)findViewById(R.id.spinner2)).getSelectedItem().toString();
        SPe.putString("VOIV",viovodeship);
        SPe.commit();
        Intent i = new Intent(this,ZawodyResults.class);
        startActivity(i);
    }

    public class WebT extends AsyncTask<Integer,Void,String[]>
    {
        protected String[] doInBackground(Integer... integers)
        {
            SharedPreferences SP = getSharedPreferences("profs", Context.MODE_PRIVATE);
            SharedPreferences.Editor SPe = SP.edit();
            String x[] = new String[integers.length];
            Connection WC = Jsoup.connect("https://kariera.pracuj.pl/zarobki-i-prawo-pracy/najlepiej-platne-zawody-w-polsce");
            try{
                Document html = WC.get();

                for (int i = 0; i < integers.length; i++) {
                    int pos = integers[i];
                    Elements root = html.select("table");
                    Elements name = root.get(0).getAllElements().get(1).getAllElements().get(0).getAllElements().select("tr");
                    String sname = name.select("td").get(pos).text();
                    String smoney = name.select("td").get(pos+1).text();
                    int money = Integer.parseInt(smoney);
                    SPe.putInt(sname,money);
                    x[i] = sname;

                }
            }catch (IOException e){
                Log.i("JSOUP ERROR: ",e.getMessage());}
            SPe.commit();

            return x;
        }
        protected void onPostExecute(String x[])
        {
            ArrayAdapter <String> proff = new ArrayAdapter<String>(getBaseContext(),R.layout.support_simple_spinner_dropdown_item,x);
            ((Spinner)findViewById(R.id.spinner)).setAdapter(proff);
            Toast.makeText(getApplicationContext(),"Pobrano pensje",Toast.LENGTH_LONG).show();
        }
    }

}
