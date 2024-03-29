// This class is responsible for initialising the splash screen
package com.example.des.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread mythread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(3000);
                    Intent startMainScreen = new Intent(getApplicationContext(), LoginCheck.class);
                    startActivity(startMainScreen);
                    finish();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        mythread.start();
    }
}
