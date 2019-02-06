package com.example.shrinidhikr.moneysaver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Savings extends AppCompatActivity {

    static TextView bal;
    static TextView sav;
    Button ab,done;
    SharedPreferences auth,authbalnew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);
        bal=(TextView)findViewById(R.id.textView2);
        sav= (TextView) findViewById(R.id.gr);

        auth=getApplicationContext().getSharedPreferences(login_activity.email1, Context.MODE_PRIVATE);
        String b=auth.getString("income","");
        bal.setMovementMethod(new ScrollingMovementMethod());
        int retbalance = Integer.parseInt(b);
        bal.setText(b);
        //bal.setTextColor(Color.BLUE);
        String si = b.substring(1);

        if(Expenses.no!=0) {
            if (retbalance >= 0){
                bal.setText(b);
                //bal.setTextColor(Color.BLUE);
            }
            else {

                bal.setText("0");
               // bal.setTextColor(Color.BLUE);
                sav.setText(si);
               // sav.setTextColor(Color.RED);
            }
        }

        ab = (Button) findViewById(R.id.addbal);
        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Savings.this);
                final View mView = getLayoutInflater().inflate(R.layout.dialog_addnewbal, null);
                final EditText newbal = (EditText) mView.findViewById(R.id.textView9);
                done = (Button) mView.findViewById(R.id.Do);
                mBuilder.setView(mView);

                final AlertDialog dialog11 = mBuilder.create();
                dialog11.show();


                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(newbal.getText().toString().isEmpty()  ) {
                            Toast.makeText(Savings.this,
                                    "Feed your income",
                                    Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            dialog11.dismiss();

                            authbalnew=getApplicationContext().getSharedPreferences(login_activity.email1, Context.MODE_PRIVATE);
                            String d=authbalnew.getString("income","");

                            int ub = Integer.parseInt(d);
                            int wo=Integer.parseInt(newbal.getText().toString());

                          /*  Snackbar snackbar = Snackbar.make(mView , "Successfully Added",Snackbar.LENGTH_LONG);
                             snackbar.show(); */

                            ub=ub+wo;

                            SharedPreferences  auth12345 = getApplicationContext().getSharedPreferences(login_activity.email1, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editorbal = auth12345.edit();

                            editorbal.putString("income", String.valueOf(ub));
                            editorbal.apply();
                            bal.setMovementMethod(new ScrollingMovementMethod());
                            bal.setText(String.valueOf(ub));
                            bal.setTextColor(Color.BLUE);

                            Toast.makeText(Savings.this,
                                    "Income added successfully",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);

                            finish();

                           /* int y1 = Integer.parseInt(sav.getText().toString());
                            int y2 = Integer.parseInt(bal.getText().toString());
                            if(y1<y2)
                            {
                                int y3=y2-y1;
                            }
                                sav.setText(Strin);

                            */
                        }
                    }
                });
            }
        });

    }
}

