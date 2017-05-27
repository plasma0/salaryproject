package krakow.uek.wynagrodzenieapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class ZawodyResults extends AppCompatActivity {

    public static double normalDistribution(double average,double arg)
    {
        double avgxarg=arg-average;
        double stddis=1600;
        double result = Math.exp(-(avgxarg*avgxarg)/(2*stddis*stddis)) / (Math.sqrt(2*Math.PI) * stddis);
        return result;
    }
    public static double calculateSub(double arg, double interval, double mean)
    {
        double summary = 0;
        double sumTilReach = 0;
        boolean isAdded=false;
        for (double min = 1459; min < 10000; min=min+interval)
        {
            summary += interval*normalDistribution(mean,min);
            if(min>arg && isAdded==false){sumTilReach=summary;isAdded=true;}
        }
        return sumTilReach/summary;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zawody_results);
        SharedPreferences SP = getSharedPreferences("sprof", Context.MODE_PRIVATE);
        String selection = SP.getString("SELECT","");
        SharedPreferences PROFS = getSharedPreferences("profs",Context.MODE_PRIVATE);
        int defSalary = PROFS.getInt(selection,0);
        int salary = SP.getInt("SALARY",0);
        Double dds = (double)defSalary;
        Double ds = (double)salary;
        Double less = calculateSub(ds,2,dds);
        Double more = 1-less;
        less = less * 100;
        more = more * 100;
        String L = less.toString().substring(0,4)+"%";
        String M = more.toString().substring(0,4)+"%";
        ((TextView)findViewById(R.id.textView8)).setText(L);
        ((TextView)findViewById(R.id.textView9)).setText(M);

        Double cless = calculateSub(ds,2,2800);
        Double cmore = 1-cless;
        cless = cless * 100;
        cmore = cmore * 100;
        String cL = cless.toString().substring(0,4)+"%";
        String cM = cmore.toString().substring(0,4)+"%";
        ((TextView)findViewById(R.id.textView5)).setText(cL);
        ((TextView)findViewById(R.id.textView6)).setText(cM);

        String voivodeship = SP.getString("VOIV","");
        Double vmean;
        switch (voivodeship)
        {
            case "dolnośląskie":vmean=3192.58;break;
            case "kujawsko-pomorskie":vmean=2749.95;break;
            case "lubelskie":vmean=2811.04;break;
            case "lubuskie":vmean=2735.24;break;
            case "łódzkie":vmean=2861.38;break;
            case "małopolskie":vmean=3213.92;break;
            case "mazowieckie":vmean=3914.48;break;
            case "opolskie":vmean=2948.52;break;
            case "podkarpackie":vmean=2688.07;break;
            case "podlaskie":vmean=2708.63;break;
            case "pomorskie":vmean=3352.19;break;
            case "śląskie":vmean=3343.76;break;
            case "świętokrzyskie":vmean=2729.18;break;
            case "warmińsko-mazurskie":vmean=2594.63;break;
            case "wielkopolskie":vmean=2935.61;break;
            case "zachodniopomorskie":vmean=2948.52;break;
            default:vmean=0.0;break;
        }
        Double vless = calculateSub(ds,2,vmean);
        Double vmore = 1 - vless;
        vless = vless * 100;
        vmore = vmore * 100;
        String vL = vless.toString().substring(0,4)+"%";
        String vM = vmore.toString().substring(0,4)+"%";
        ((TextView)findViewById(R.id.textView10)).setText(vL);
        ((TextView)findViewById(R.id.textView11)).setText(vM);
    }
}
