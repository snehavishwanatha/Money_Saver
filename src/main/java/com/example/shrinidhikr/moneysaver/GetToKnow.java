package com.example.shrinidhikr.moneysaver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class GetToKnow extends AppCompatActivity {

    Sign_up su;
    EditText et;
    EditText et1;
    Button b;
    static SharedPreferences auth1;
    static SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_to_know);
        final Spinner spinner = (Spinner) findViewById(R.id.snr);
        et = (EditText) findViewById(R.id.editText3);
        et1 = (EditText)findViewById(R.id.editText4);



        String[] items = new String[]{"$ - US dollar", "Euro", "AFN - Afghan Afghani", "A$ - Australian dollar", "Japanese Yen"," KWD - Kuwaiti Dinar", "NZ$ - New Zealand Dollar" , "CA$ - Canadian dollar", "Rs - Indian rupee", "SGD - Singapore dolar", " MYR - Malayasian Ringit", "AED - UAE Dirham"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                       long arg3) {
                String anyvariable=String.valueOf(spinner.getSelectedItem());

                et.setText(anyvariable);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        b= (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et.getText().toString().isEmpty() || et1.getText().toString().isEmpty())
                    Toast.makeText(GetToKnow.this,
                            "Please fill empty fields",
                            Toast.LENGTH_SHORT).show();
                else {


                    SharedPreferences  auth = getApplicationContext().getSharedPreferences(Sign_up.email1, Context.MODE_PRIVATE);
                    editor = auth.edit();
                    String zero = String.valueOf(0);
                    editor.putString("sum",zero);
                    editor.putString("income", et1.getText().toString());
                    editor.apply();


                    Intent intent = new Intent(getApplicationContext(), login_activity.class);
                    intent.putExtra("income",et1.getText().toString());

                    Toast.makeText(GetToKnow.this,
                            "Sign up successful! Please log in again",
                            Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();

                }
            }
        });
    }

}


