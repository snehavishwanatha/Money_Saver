package com.example.shrinidhikr.moneysaver;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.support.design.widget.FloatingActionButton;

import java.util.ArrayList;



class APlan extends AppCompatActivity
{
    String cate,amt;

    APlan(String a,String c)
    {
        cate=a;
        // da=b;
        amt =c;
    }

    public String toString()

    {
        return "Category : "+cate+"\n"+"Amount : "+amt;
    }

}


public class DateWisePlan extends AppCompatActivity {
    Button b;
    SharedPreferences auth,authupplan;
    SharedPreferences.Editor editor,ed;
    static int ip=0,nop=0,i=0;
    int sum;
    int y=0;
    ArrayList<APlan> listitems;
    ArrayAdapter<APlan> adapter ;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_wise_plan);
        listitems = new ArrayList<APlan>();
        adapter = new ArrayAdapter<APlan>(this,
                R.layout.plist, listitems);
        list = (ListView) findViewById(R.id.list1p);
        list.setAdapter(adapter);



        for(int k=0,y=0;k<nop;k++,y++) {
            String w=String.valueOf(y);
            auth = getApplicationContext().getSharedPreferences(login_activity.email1+Plans.d+Plans.m+Plans.ye+w, Context.MODE_PRIVATE);
            String sav1 = auth.getString("mp", "");
            String sav2 = auth.getString("op", "");

            if (!(sav1.equals("") || sav2.equals(""))) {

                APlan apl = new APlan(sav1, sav2);
                listitems.add(apl);
                adapter.notifyDataSetChanged();


            }
        }



        b = (Button) findViewById(R.id.button2p);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(DateWisePlan.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_dateplan, null);
                final EditText Catp = (EditText) mView.findViewById(R.id.Categoryp);

                final EditText Amountp = (EditText) mView.findViewById(R.id.Amountp);
                Button AddAPlan = (Button) mView.findViewById(R.id.Addp);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();


                AddAPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(Catp.getText().toString().isEmpty() || Amountp.getText().toString().isEmpty())   {
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Add a plan", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }

                        else {
                            dialog.dismiss();
                            nop++;
                            APlan ae = new APlan(Catp.getText().toString(),Amountp.getText().toString());
                            String j = String.valueOf(i);

                            SharedPreferences authsum = getApplicationContext().getSharedPreferences(login_activity.email1, Context.MODE_PRIVATE);
                            String savsum = authsum.getString("sum", "");
                            int savedsum=Integer.parseInt(savsum);

                            sum=savedsum+Integer.parseInt(Amountp.getText().toString());

                            SharedPreferences authupsum = getApplicationContext().getSharedPreferences(login_activity.email1, Context.MODE_PRIVATE);
                            ed =authupsum.edit();
                            String kop = String.valueOf(sum);
                            ed.putString("sum", kop);
                            ed.apply();

                            auth = getApplicationContext().getSharedPreferences(login_activity.email1+Plans.d+Plans.m+Plans.ye+i, Context.MODE_PRIVATE);
                            editor = auth.edit();
                            editor.putString("mp", Catp.getText().toString());
                            editor.putString("op", Amountp.getText().toString());
                            editor.apply();
                            listitems.add(ae);
                            adapter.notifyDataSetChanged();
                            i++;


                        }

                    }
                });
            }
        });



    }
}

