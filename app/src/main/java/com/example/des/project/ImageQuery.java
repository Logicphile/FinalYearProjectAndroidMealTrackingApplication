package com.example.des.project;

import android.app.ListActivity;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Des on 04/03/2016.
 */
public class ImageQuery extends ListActivity{

    public void queryImagesFromParse(){
        ParseQuery<ParseObject> imagesQuery = new ParseQuery<>("ImageUploads");
        imagesQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> images, ParseException e) {
                if(e == null){

                    MainActivityAdapter adapter = new MainActivityAdapter(ImageQuery.this, images);
                    setListAdapter(adapter);

                }else{
                    Toast.makeText(ImageQuery.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
