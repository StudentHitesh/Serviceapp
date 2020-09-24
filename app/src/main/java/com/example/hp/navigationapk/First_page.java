package com.example.hp.navigationapk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class First_page extends AppCompatActivity implements View.OnClickListener{
ImageView img3;
TextView tv3;
Button login,registration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        tv3=findViewById(R.id.textView3);
        login=findViewById(R.id.button);
        registration=findViewById(R.id.button2);
        login.setOnClickListener(this);
        registration.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;

        if(!connected)
        {
            new AlertDialog.Builder(First_page.this).setTitle("Internet Disable").setMessage( "\n"+"Please Enable Internet").setNeutralButton("Ohk Got it !", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {


                }
            }).show();
        }
        if(connected) {
            if (view == login) {
                Intent intent = new Intent(First_page.this, LoginActivity.class);
                startActivity(intent);
            }
            if (view == registration) {
                Intent intent = new Intent(First_page.this, RegisterActivity1.class);
                startActivity(intent);
            }
        }
    }
}
