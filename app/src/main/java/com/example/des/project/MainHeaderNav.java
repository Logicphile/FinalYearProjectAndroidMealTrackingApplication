package com.example.des.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseUser;

public class MainHeaderNav extends AppCompatActivity {

    //public String currentUser = ParseUser.getCurrentUser().getUsername();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_main);

        String currentUser = getIntent().getStringExtra("string");

        TextView textViewToChange = (TextView) findViewById(R.id.current_user);
        textViewToChange.setText(currentUser);
        Log.i("Current User", ParseUser.getCurrentUser().getObjectId());


        // = (EditText) findViewById(R.id.current_user);
        //nme.setText("united");
        /*
        nme.setText(ParseUser.getCurrentUser().getUsername());*/

        //TextView current_user = (TextView) findViewById(R.id.current_user);


       /* TextView currentUser = (TextView) findViewById(R.id.current_user);
        //currentUser.setText(currentUser);

       *//* if (currentUser == null) {
            //Log.i("The current user is null", "null");
        }*/

        //currentUser.getText(ParseUser.getCurrentUser().getUsername());


    }
}