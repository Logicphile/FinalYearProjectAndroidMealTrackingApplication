/////////   This class checks if the user is already logged in for example from previously using the app and logging in.
package com.example.des.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.des.project.LoginRegOptions;
import com.example.des.project.MainActivity;
import com.parse.ParseUser;

public class LoginCheck extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Check if there is current user info
        if (ParseUser.getCurrentUser() != null)     //
        {
            // Start an intent for the logged in activity
            startActivity(new Intent(this, MainActivity.class));        // Go to the main activity class if the user is already logged in
        }
        // Else start the activity / class for the registration & login options
        else
        {
            // Start an intent for the logged out activity
            startActivity(new Intent(this, LoginRegOptions.class));     // Go to the registration & login options class is the user is not logged in
        }
    }
}