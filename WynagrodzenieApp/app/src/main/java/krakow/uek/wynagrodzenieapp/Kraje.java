package krakow.uek.wynagrodzenieapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.design.widget.*;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Kraje extends AppCompatActivity
{
    public ArrayList<String> cells;

    TextView textView_Result_Salary;
    EditText kwotaEditText;


    String adres = "https://www.numbeo.com/cost-of-living/prices_by_country.jsp?displayCurrency=PLN&itemId=105";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kraje);

        new MyTask().execute();
    }
    public void button_Read_Salary_onClick(View view)
    {

        new PorownajTask().execute();
    }

    private class MyTask extends AsyncTask<Void, Void, ArrayList<String> > //params, progress, result
    {
        @Override
        protected ArrayList<String> doInBackground(Void... params)
        {
            kwotaEditText = (EditText) findViewById(R.id.kwotaEditText);
            textView_Result_Salary = (TextView) findViewById(R.id.textView_Result_Salary);
            Document doc = null;
            //ArrayList<String> cells;

            try
            {
                //Document doc = Jsoup.connect("http://espn.go.com/mens-college-basketball/conferences/standings/_/id/2/year/2012/acc-conference").get();
                doc = Jsoup.connect(adres).get();
                cells = new ArrayList<>();
                for (Element table : doc.select("table[id]"))
                {
                    for (Element row : table.select("tr")) {
                        Elements cell = row.select("td");
                        //System.out.println(cell.text());
                        cells.add(cell.text());
                    }
                }
                return cells;

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings)
        {
            super.onPostExecute(strings);
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private class PorownajTask extends AsyncTask<Void, Void, Void > //params, progress, result
    {

        @Override
        protected Void doInBackground(Void... params)
        {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            textView_Result_Salary.setMovementMethod(new ScrollingMovementMethod());
            textView_Result_Salary.setText("");
            Collections.sort(cells.subList(1, cells.size()));
            int i = 0;
            for(String a  : cells)
            {
                textView_Result_Salary.append("\n");
                textView_Result_Salary.append(a);
                a = a.replaceAll("[^\\d.]", "");
                //a = a.replaceAll("","0");
                if (i==0) { i++; continue; }//w pierwszej linii odstep i jest pusta
                double srWynagrodzenie = Double.parseDouble(a);
                double twojeWynagrodzenie = Double.parseDouble(kwotaEditText.getText().toString());
                //if(srWynagrodzenie == 0) continue;
                if(twojeWynagrodzenie > srWynagrodzenie)
                {
                    textView_Result_Salary.append(Html.fromHtml("&nbsp;<font color=blue>powyżej</font>"));
                }
                else if(twojeWynagrodzenie < srWynagrodzenie)
                {
                    textView_Result_Salary.append(Html.fromHtml("&nbsp;<font color=red>poniżej</font>"));
                }
                else if(twojeWynagrodzenie == srWynagrodzenie)
                {
                    textView_Result_Salary.append(Html.fromHtml("&nbsp;<font color=black>na równi</font>"));
                }
            }
        }

    }



}
