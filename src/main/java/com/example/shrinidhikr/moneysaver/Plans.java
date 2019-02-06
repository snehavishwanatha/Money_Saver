package com.example.shrinidhikr.moneysaver;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;



import java.util.Calendar;

public class Plans extends AppCompatActivity {

    static int d,m,ye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans);

        CalendarView view = new CalendarView(this);
        setContentView(view);

        view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView arg0, int year, int month,
                                            int date) {
                d = date;
                m = month;
                ye = year;

                Intent intent = new Intent(getApplicationContext(), DateWisePlan.class);
                int mo=month+1;
                Toast.makeText(getApplicationContext(), date + "/" + mo  + "/" + year, Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();

            }


        });

    }
}

