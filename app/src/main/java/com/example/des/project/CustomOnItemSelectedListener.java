package com.example.des.project;

/**
 * Created by Des on 14/04/2016.
 * This class is for the spinner drop down menu in the AddMealActivity class
 */

import android.widget.AdapterView;

import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class CustomOnItemSelectedListener implements OnItemSelectedListener {
    private Spinner spinner1;
    private EditText etD;

    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id)
    {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
