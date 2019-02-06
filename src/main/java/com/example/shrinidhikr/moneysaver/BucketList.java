package com.example.shrinidhikr.moneysaver;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class ABuck
{
    String cate,da,amt;
    ABuck(String a,String b,String c)
    {
        cate=a;
        da=b;
        amt =c;
    }
    public String toString()
    {
        return "Item: "+cate+"\n"+"Date: "+da+"\n"+"Amount: "+amt;
    }

}

public class BucketList extends AppCompatActivity {
    SharedPreferences auth;
    static int i1=0,no1=0;
    int y=0;
    SharedPreferences.Editor editor;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_list);

        Button b;
        final ArrayList<ABuck> listitems;
        final ArrayAdapter<ABuck> adapter ;


        listitems = new ArrayList<ABuck>();
        adapter = new ArrayAdapter<ABuck>(this,
                R.layout.bucklist, listitems);
        list = (ListView) findViewById(R.id.buckylist);
        list.setAdapter(adapter);


        for(int k=0,y=0;k<no1;k++,y++)
        {
            String w = String.valueOf(y);
            auth = getApplicationContext().getSharedPreferences(login_activity.email1+w+w, Context.MODE_PRIVATE);
            String sav1 = auth.getString("mm","");
            String sav2 = auth.getString("nn","");
            String sav3 = auth.getString("oo","");
            if ((sav1.equals("") || sav2.equals("") || sav3.equals(""))) {
                int a = 0;
            } else {
                ABuck anex = new ABuck(sav1, sav2, sav3);
                listitems.add(anex);
                adapter.notifyDataSetChanged();


            }

        }



        b = (Button) findViewById(R.id.badd);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(BucketList.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_buck,null);
                final EditText Cat = (EditText) mView.findViewById(R.id.CategoryB);
                final EditText Date = (EditText) mView.findViewById(R.id.DateB);
                final EditText Amount = (EditText) mView.findViewById(R.id.AmountB);
                Button AddABucket = (Button) mView.findViewById(R.id.AddB);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();



                AddABucket.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(Cat.getText().toString().isEmpty() || Amount.getText().toString().isEmpty() || Date.getText().toString().isEmpty())   {

                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Please fill all empty fields", Snackbar.LENGTH_LONG);
                            snackbar.show();


                        }
                        else
                        {
                            dialog.dismiss();

                            no1++;

                            SharedPreferences authin = getApplicationContext().getSharedPreferences(login_activity.email1, Context.MODE_PRIVATE);
                            String inc1=authin.getString("income","");
                            String balc1=authin.getString("sum","");
                            int inin = Integer.parseInt(inc1);
                            int plapla = Integer.parseInt(balc1);

                            if(inin-plapla>0)
                            {
                                int amtb=Integer.parseInt(Amount.getText().toString());
                                if((inin-plapla)-amtb>0) {
                                    Toast.makeText(getApplicationContext(), "Currently, this does not affect your plans", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Presently, you cannot afford this", Toast.LENGTH_SHORT).show();
                                }
                            }



                            ABuck ab = new ABuck(Cat.getText().toString(), Date.getText().toString(), Amount.getText().toString());
                            String j = String.valueOf(i1);
                            auth = getApplicationContext().getSharedPreferences(login_activity.email1+j+j, Context.MODE_PRIVATE);
                            editor = auth.edit();

                            editor.putString("mm", Cat.getText().toString());
                            editor.putString("nn", Date.getText().toString());
                            editor.putString("oo", Amount.getText().toString());
                            editor.apply();
                            listitems.add(ab);
                            adapter.notifyDataSetChanged();
                            i1++;
                        }

                    }
                });
            }
        });

    }
}

