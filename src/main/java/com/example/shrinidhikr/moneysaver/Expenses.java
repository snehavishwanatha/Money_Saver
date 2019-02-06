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



class AnExpense extends AppCompatActivity
{
    String cate,da,amt;

    AnExpense(String a,String b,String c)
    {
        cate=a;
        da=b;
        amt =c;
    }
    public int deduct(int incc,int j)
    {
        incc = incc - j;
        return incc;
    }
    public String toString()

    {
        return "Category : "+cate+"\n"+"Date : "+da+"\n"+"Amount : "+amt;
    }

}


public class Expenses extends AppCompatActivity {
    Button b,ab;
    SharedPreferences auth;
    SharedPreferences.Editor editor;
    static int i=0,no=0;
    int y=0,in,up_in;
    ArrayList<AnExpense> listitems;
    ArrayAdapter<AnExpense> adapter ;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        listitems = new ArrayList<AnExpense>();
        adapter = new ArrayAdapter<AnExpense>(this,
                R.layout.exlist, listitems);
        list = (ListView) findViewById(R.id.list1);
        list.setAdapter(adapter);

        SharedPreferences  authin=getApplicationContext().getSharedPreferences(login_activity.email1, Context.MODE_PRIVATE);
        String inc1=authin.getString("income","");
        in = Integer.parseInt(inc1);


        for(int k=0,y=0;k<no;k++,y++) {
            String w = String.valueOf(y);
            auth = getApplicationContext().getSharedPreferences(login_activity.email1 + w, Context.MODE_PRIVATE);
            String sav1 = auth.getString("m", "");
            String sav2 = auth.getString("n", "");
            String sav3 = auth.getString("o", "");

            if (!(sav1.equals("") || sav2.equals("") || sav3.equals(""))) {

                AnExpense anex = new AnExpense(sav1, sav2, sav3);
                listitems.add(anex);
                adapter.notifyDataSetChanged();


            }
        }



        b = (Button) findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Expenses.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_addexpense, null);
                final EditText Cat = (EditText) mView.findViewById(R.id.Category);
                final EditText Date = (EditText) mView.findViewById(R.id.Date);
                final EditText Amount = (EditText) mView.findViewById(R.id.Amount);
                Button AddAnExpense = (Button) mView.findViewById(R.id.Add);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();


                AddAnExpense.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(Cat.getText().toString().isEmpty() || Amount.getText().toString().isEmpty() || Date.getText().toString().isEmpty())   {

                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Please fill empty details", Snackbar.LENGTH_LONG);
                            snackbar.show();

                        }

                        else {
                            dialog.dismiss();
                            no++;
                            AnExpense ae = new AnExpense(Cat.getText().toString(),Date.getText().toString(),Amount.getText().toString());

                            int pri=Integer.parseInt(Amount.getText().toString());
                            if(no==1)
                                up_in =ae.deduct(in,pri);
                            else {

                                SharedPreferences  authupin=getApplicationContext().getSharedPreferences(login_activity.email1, Context.MODE_PRIVATE);
                                String inc1=authupin.getString("income","");
                                up_in = Integer.parseInt(inc1);
                                up_in = ae.deduct(up_in, pri);
                            }
                            SharedPreferences  auth123 = getApplicationContext().getSharedPreferences(login_activity.email1, Context.MODE_PRIVATE);
                            editor = auth123.edit();
                            editor.putString("income", String.valueOf(up_in));
                            editor.apply();


                            SharedPreferences  authgetsum=getApplicationContext().getSharedPreferences(login_activity.email1, Context.MODE_PRIVATE);
                            String sumplan=authgetsum.getString("sum","");
                            int sp = Integer.parseInt(sumplan);

                            if(no==1)
                            {
                                if(in-sp<pri)
                                    Toast.makeText(Expenses.this,
                                            "Check your plans and spend wisely",
                                            Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                if(up_in-sp<pri)
                                    Toast.makeText(Expenses.this,
                                            "Check your plans and spend wisely",
                                            Toast.LENGTH_LONG).show();
                            }



                            if(up_in<0)
                                Toast.makeText(Expenses.this,
                                        "Warning - You have overdues",
                                        Toast.LENGTH_SHORT).show();


                            String j = String.valueOf(i);
                            auth = getApplicationContext().getSharedPreferences(login_activity.email1+j, Context.MODE_PRIVATE);
                            editor = auth.edit();
                            editor.putString("m", Cat.getText().toString());
                            editor.putString("n", Date.getText().toString());
                            editor.putString("o", Amount.getText().toString());
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

