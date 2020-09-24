package com.example.hp.navigationapk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
ImageView img;
TextView tv;
ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        img=findViewById(R.id.imageView2);
        tv=findViewById(R.id.textView2);
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
                    Intent intent=new Intent(SplashActivity.this,First_page.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }
}
