package com.example.hp.navigationapk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Splash_activity extends AppCompatActivity {
ProgressBar pb;
TextView tv7;
ImageView img8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);
        tv7=findViewById(R.id.textView7);
        img8=findViewById(R.id.imageView8);
        pb=findViewById(R.id.progressBar);
        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent=new Intent(Splash_activity.this,Home_page.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }
}
