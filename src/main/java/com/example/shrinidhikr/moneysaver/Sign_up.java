package com.example.shrinidhikr.moneysaver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;



public class Sign_up extends AppCompatActivity {
    Button sign1;
    static EditText emailid, password, name, cpassword;
    SharedPreferences auth,authret;
    static SharedPreferences.Editor editor;
    CheckBox cb;
    private static int i=0,j=0,k=0;
    static String email1;
    static  String pw1,pw2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        cb = (CheckBox) findViewById(R.id.checkBox);
        sign1 = (Button) findViewById(R.id.email_sign_in_button);
        emailid = (EditText) findViewById(R.id.editText2);
        password = (EditText) findViewById(R.id.editText6);
        cpassword = (EditText) findViewById(R.id.editText8);
        name = (EditText) findViewById(R.id.editText);


        sign1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                email1 = emailid.getText().toString();
                pw1 = password.getText().toString();
                pw2 = cpassword.getText().toString();
                Toast t;

authret = getApplicationContext().getSharedPreferences(email1,Context.MODE_PRIVATE);
String eret = authret.getString("q","");

                if (email1.isEmpty() || pw1.isEmpty() || name1.isEmpty() || pw2.isEmpty()) {
                    Snackbar snackbar1 = Snackbar.make(findViewById(android.R.id.content),"Fill the empty fields",Snackbar.LENGTH_LONG);
                    snackbar1.show();
                }
                else if(email1.equals(eret)){
                    Snackbar snackbar1 = Snackbar.make(findViewById(android.R.id.content),"Email id exist",Snackbar.LENGTH_LONG);
                    snackbar1.show();
                }
                else if (!(pw1.equals(pw2))) {
                    Snackbar snackbar1 = Snackbar.make(findViewById(android.R.id.content),"Password did not match",Snackbar.LENGTH_LONG);
                    snackbar1.show();
                } else {

                    auth = getApplicationContext().getSharedPreferences(email1, Context.MODE_PRIVATE);
                    editor = auth.edit();

                    editor.putString("i", name1);
                    editor.putString("q", email1);
                    editor.putString("m", pw1);
                    editor.apply();

                    if (cb.isChecked())
                    {
                        Intent intent = new Intent(getApplicationContext(), GetToKnow.class);
                        startActivity(intent);
                        finish();
                    }
                }


            }

        });
    }

}

