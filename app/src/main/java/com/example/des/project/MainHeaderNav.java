// This class is responsible for the implementation of the navigation drawer
package com.example.des.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.parse.ParseUser;

public class MainHeaderNav extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_main);

        String currentUser = getIntent().getStringExtra("string");

        TextView textViewToChange = (TextView) findViewById(R.id.current_user);
        textViewToChange.setText(currentUser);
        Log.i("Current User = ", ParseUser.getCurrentUser().getObjectId());
    }
}