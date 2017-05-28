package krakow.uek.wynagrodzenieapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SredniaKrajowa extends AppCompatActivity
{
    EditText kwotaEditText;
    TextView aktualWynagTextView, znajdujeszSieTextView;
    String adres = "https://www.numbeo.com/cost-of-living/prices_by_country.jsp?displayCurrency=PLN&itemId=105";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.srednia_krajowa);

        kwotaEditText = (EditText) findViewById(R.id.kwotaEditText);
        aktualWynagTextView =(TextView) findViewById(R.id.aktualWynagTextView);
        znajdujeszSieTextView = (TextView) findViewById(R.id.znajdujeszSieTextView);
    }

    public void porownaj(View view)
    {
        if(kwotaEditText.getText().toString().matches("")) Toast.makeText(getApplicationContext(), "Podaj wysokość wynagrodzenia", Toast.LENGTH_LONG).show();
        else new MyTask().execute();
    }

    private class MyTask extends AsyncTask<Void, Void, ArrayList<String> > //params, progress, result
    {

        @Override
        protected ArrayList<String> doInBackground(Void... params)
        {
            Document doc = null;
            ArrayList<String> cells;


            try
            {
                doc = Jsoup.connect(adres).get();
                cells = new ArrayList<>();

                for (Element table : doc.select("table[id]"))
                {
                    for (Element row : table.select("tr")) {
                        Elements cell = row.select("td");

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
            int i = 0;
            String pl, plKurs;
            double plKursD, wynagrD;
            for(String a  : strings)
            {

                if(a.contains("Poland"))
                {
                    pl = a;
                    plKurs = pl.replaceAll("[^\\d.]", "");
                    plKursD = Double.parseDouble(plKurs);
                    wynagrD = Double.parseDouble(kwotaEditText.getText().toString());
                    znajdujeszSieTextView.setText("");
                    aktualWynagTextView.setText("");

                    aktualWynagTextView.append("Srednie wynagrodzenie netto wynosi " + plKurs);


                    if (wynagrD > plKursD) znajdujeszSieTextView.append("Znajdujesz sie powyżej sredniej krajowej");
                    else if(wynagrD < plKursD) znajdujeszSieTextView.append("Znajdujesz sie poniżej sredniej krajowej");
                    else if(wynagrD == plKursD) znajdujeszSieTextView.append("Znajdujesz sie na równi ze średnią krajową");
                }
            }
        }

    }
}
