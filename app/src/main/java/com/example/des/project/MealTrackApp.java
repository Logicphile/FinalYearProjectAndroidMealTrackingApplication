////This class initialises the parse.com account and connects it to this app.

package com.example.des.project;

import android.app.Application;

import com.parse.Parse;

public class MealTrackApp extends Application
{
    public void onCreate()
    {
        super.onCreate();

        Parse.initialize(this, "1SzsISGqSK4hDLbLyxQaHVxrPcCpbIeTKDK1xwyi", "zd1Kgbe8oTrbStbHf2QQDzkmUOyoc9pYK1r0KVLl");     //This initialises Parse.com. The "App ID", "Client ID" that is used to access Parse.com. The app ID specifies the particular application being used (this app), and the client ID is the ID used to specify the account being used (my account registered using my student email).
    }

}
