package com.example.des.project;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Des on 07/04/2016.
 */
public class QueryImages extends ListActivity
{

   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Log.i("Current User", "And here");
    }*/

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queryImagesFromParse();
   }

    public void queryImagesFromParse(){
        ParseQuery<ParseObject> imagesQuery = new ParseQuery<>("ImageUploads");
        imagesQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> images, ParseException e) {
                if(e == null){

                    MainActivityAdapter adapter = new MainActivityAdapter(QueryImages.this, images);
                    setListAdapter(adapter);

                }else{
                    Toast.makeText(QueryImages.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    /*public void queryImagesFromParse(){
        ParseQuery<ParseObject> imagesQuery = new ParseQuery<>("ImageUploads");
        imagesQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> images, ParseException e) {
                if (e == null) {

                    MainActivityAdapter adapter = new MainActivityAdapter(QueryImages.this, images);
                    setListAdapter(adapter);

                } else {
                    Toast.makeText(QueryImages.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
*/

}
