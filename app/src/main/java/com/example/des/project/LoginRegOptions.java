// This class is the register & login class to handle functionality of the register & login buttons of the LoginRegOptions screen.
package com.example.des.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.des.project.Login;
import com.example.des.project.R;
import com.example.des.project.Register;

public class LoginRegOptions extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_reg_options);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Sign up button click handler
        ((Button) findViewById(R.id.regbtn)).setOnClickListener(new View.OnClickListener()      // This executes the following code if the Register button (regbtn) is clicked
        {
            public void onClick(View v)
            {
                // Starts an intent for the sign up activity
                startActivity(new Intent(LoginRegOptions.this, Register.class));        // This calls the Register class
            }
        });

        // Log in button click handler
        ((Button) findViewById(R.id.loginbtn)).setOnClickListener(new View.OnClickListener()        // This executes the following code if the Login button (loginbtn) is clicked
        {
            public void onClick(View v)
            {
                // Starts an intent of the log in activity
                startActivity(new Intent(LoginRegOptions.this, Login.class));       // This calls the Login class
            }
        });
    }

}
