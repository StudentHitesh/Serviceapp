package com.example.hp.navigationapk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RegisterDetails extends AppCompatActivity {
TextView use,emai,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_details);
        use=findViewById(R.id.textView);
        emai=findViewById(R.id.textView8);
        phone=findViewById(R.id.textView9);

        Bundle bundle=getIntent().getExtras();
        String u=bundle.getString("HI");
        String e=bundle.getString("HII");

        use.setText(u);

    }
}
