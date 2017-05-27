package pl.krakow.uek.porownajpensje2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kania on 27.05.2017.
 */

public class Results extends AppCompatActivity {

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
        setContentView(R.layout.results);
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
    }
}
