package com.example.des.project;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Des on 19/11/2015.
 */
public class MealTrackApp extends Application
{
    public void onCreate()
    {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "1SzsISGqSK4hDLbLyxQaHVxrPcCpbIeTKDK1xwyi", "zd1Kgbe8oTrbStbHf2QQDzkmUOyoc9pYK1r0KVLl");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }

}
