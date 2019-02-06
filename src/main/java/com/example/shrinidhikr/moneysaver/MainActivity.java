package com.example.shrinidhikr.moneysaver;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity{


    Button logoff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoff = (Button) findViewById(R.id.logout);

        String[] listitems =  {"Balance", "Plans", "Expenses", "Bucket List"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, listitems);

        ListView listView = (ListView) findViewById(R.id.con_list);
        listView.setAdapter(adapter);


        logoff.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), login_activity.class);
                startActivity(intent1);
                finish();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent myintent = new Intent(view.getContext(),Savings.class);
                    startActivityForResult(myintent,0);
                }
                if(i==1){
                    Intent myintent = new Intent(view.getContext(),Plans.class);
                    startActivityForResult(myintent,3);
                }
                if(i==2){
                    Intent myintent = new Intent(view.getContext(),Expenses.class);
                    startActivityForResult(myintent,1);
                }
                if(i==3){
                    Intent myintent = new Intent(view.getContext(),BucketList.class);
                    startActivityForResult(myintent,2);
                }



            }
        });

        AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar =Calendar.getInstance();
        calendar.add(Calendar.SECOND,15);
        Intent intent=new Intent("shashu.umesh.action.DISPLAY_NOTIFICATION");
        PendingIntent broadcast=PendingIntent.getBroadcast(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),broadcast);


    }


}
