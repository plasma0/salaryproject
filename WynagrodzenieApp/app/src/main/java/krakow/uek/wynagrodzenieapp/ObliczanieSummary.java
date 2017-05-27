package krakow.uek.wynagrodzenieapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ObliczanieSummary extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obliczanie_summary);
        SharedPreferences SP = this.getSharedPreferences("main", this.MODE_PRIVATE);
        float inc = SP.getFloat("isalary",0);
        int members = SP.getInt("members",1);
        Float mean = inc / (float)members;
        ((TextView)findViewById(R.id.textView2)).setText(mean.toString());
    }
}
