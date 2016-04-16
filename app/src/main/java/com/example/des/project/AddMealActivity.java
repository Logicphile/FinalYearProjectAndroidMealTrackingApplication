package com.example.des.project;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddMealActivity extends Activity
{
    public static final int TAKE_PIC_REQUEST_CODE = 0;
    public static final int CHOOSE_PIC_REQUEST_CODE = 1;
    public static final int MEDIA_TYPE_IMAGE = 2;

    static final String BODY_KEY = "body";  // The column name on Parse.com Named "body"
    static final String CALORIES_KEY = "calories";  // The column name on Parse.com Named "body"
    //ImageView imageView;
   // protected ImageView imgMealPhoto;

    public FloatingActionButton addMealFab;
    public ImageView mPreviewImageView;

    public Uri mMediaUri;

    //public ListView listView;

    Spinner spinner1;
    ArrayAdapter<CharSequence> adapter;
    private EditText etD;
    private EditText etC;




   /* public void addListenerOnSpinnerItemSelection() {
        *//*etD = (EditText) findViewById(R.id.etDescrip);
        etD.setText(String.valueOf(spinner1.getSelectedItem()));*//*
       *//* spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());*//*
    }*/

    /*// get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner);
       // btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v)
            {

                Toast.makeText(AddMealActivity.this,"OnClickListener : " +"\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()), Toast.LENGTH_SHORT).show();
            }

        });
    }
}*/








    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        spinner1 = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.foodItems_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);



        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                Toast.makeText(parent.getContext(),
                "On Item Select : \n" + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_LONG).show();

                etD = (EditText) findViewById(R.id.etDescrip);
                etD.setText(parent.getItemAtPosition(pos).toString());

                Log.i("Current User", ParseUser.getCurrentUser().getUsername());

                if (spinner1.getSelectedItem().toString().equals("Apple"))
                {
                    int Cals = 10;
                    etC = (EditText) findViewById(R.id.etCalories);
                    etC.setText("" + Cals + " Calories");

                    Log.i("Current blah", ParseUser.getCurrentUser().getUsername());
                }
                else if (spinner1.getSelectedItem().toString().equals("Banana"))
                {
                    int Cals = 20;
                    etC = (EditText) findViewById(R.id.etCalories);
                    etC.setText("" + Cals + " Calories");
                }
            }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }



        });


        //addListenerOnButton();
       // addListenerOnSpinnerItemSelection();

        //listView = (ListView) findViewById(R.id.listView);
        mPreviewImageView = (ImageView) findViewById(R.id.previewImageView);

        //Parse.initialize(this, "1SzsISGqSK4hDLbLyxQaHVxrPcCpbIeTKDK1xwyi", "zd1Kgbe8oTrbStbHf2QQDzkmUOyoc9pYK1r0KVLl");


        addMealFab = (FloatingActionButton) findViewById(R.id.addMealFab);
        etD = (EditText) findViewById(R.id.etDescrip);
        etC = (EditText) findViewById(R.id.etCalories);

        //listen to upload button click
        addMealFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String data = etD.getText().toString(); //Get text from the etD editText field and set it equal to a String variable named data
                final String data1 = etC.getText().toString();
                ParseObject foodItemDescription = ParseObject.create("FoodItemDescription"); //Creates the table name FoodItemDescription in parse.com

                //foodItemDescription.put(USER_ID_KEY, ParseUser.getCurrentUser().getObjectId());
                /*foodItemDescription.put(BODY_KEY, data);
                foodItemDescription.put(CALORIES_KEY, data1);*/
                foodItemDescription.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        Toast.makeText(AddMealActivity.this, "Successfully uploaded food item description to Parse",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                //etD.setText(null);




                //create parse object for image to upload
                final ParseObject imageUpload = new ParseObject("ImageUploads");

                try{
                    //convert image to bytes for upload.
                    byte[] fileBytes = FileHelper.getByteArrayFromFile(AddMealActivity.this, mMediaUri);
                    if(fileBytes == null){
                        //there was an error
                        Toast.makeText(getApplicationContext(), "There was a feckin error!", Toast.LENGTH_LONG).show();
                    }else{

                        fileBytes = FileHelper.reduceImageForUpload(fileBytes);
                        String fileName = FileHelper.getFileName(AddMealActivity.this, mMediaUri, "image");
                        final ParseFile file = new ParseFile(fileName, fileBytes);
                        imageUpload.saveEventually(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e == null){
                                    imageUpload.put(BODY_KEY, data);
                                    imageUpload.put(CALORIES_KEY, data1);
                                    imageUpload.put("imageContent", file);
                                    imageUpload.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            Toast.makeText(getApplicationContext(), "Successfully Uploaded", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }else{
                                    //there was an error
                                    //there was an error
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }

                }catch (Exception e1){
                    Toast.makeText(getApplicationContext(), e1.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void imageCap(View view) {
        //Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       // startActivityForResult(cameraIntent, CAMERA_REQUEST);

        //show dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(AddMealActivity.this);
        builder.setTitle("Upload or Take a photo");

        builder.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //upload image
                Intent choosePictureIntent = new Intent(Intent.ACTION_GET_CONTENT);
                choosePictureIntent.setType("image/*");
                startActivityForResult(choosePictureIntent, CHOOSE_PIC_REQUEST_CODE);

            }
        });
        builder.setNegativeButton("Take Photo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("Current User", "Got here");
                //take photo
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Log.i("Current User", "Got here");
                mMediaUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                Log.i("Current User", "Got here");
                if (mMediaUri == null) {
                    //display error
                    Toast.makeText(getApplicationContext(), "Sorry there was an error! Try again.", Toast.LENGTH_LONG).show();
                } else {
                    Log.i("Current User", "Got here");
                    takePicture.putExtra(MediaStore.EXTRA_OUTPUT, mMediaUri);
                    startActivityForResult(takePicture, TAKE_PIC_REQUEST_CODE);
                    Log.i("Current User", "Got here");
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }




    //inner helper method
    private Uri getOutputMediaFileUri(int mediaTypeImage) {

        if(isExternalStorageAvailable()){
            //get the URI
            //get external storage dir
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "UPLOADIMAGES");
            //create subdirectore if it does not exist
            if(!mediaStorageDir.exists()){
                //create dir
                if(! mediaStorageDir.mkdirs()){

                    return null;
                }
            }
            //create a file name
            //create file
            File mediaFile = null;
            Date now = new Date();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(now);

            String path = mediaStorageDir.getPath() + File.separator;
            if(mediaTypeImage == MEDIA_TYPE_IMAGE){
                mediaFile = new File(path + "IMG_" + timestamp + ".jpg");
            }
            //return file uri
            Log.d("UPLOADIMAGE", "FILE: "+Uri.fromFile(mediaFile));

            return Uri.fromFile(mediaFile);
        }else {

            return null;
        }

    }
    //check if external storage is mounted. helper method
    private boolean isExternalStorageAvailable(){
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Log.i("HELLO","");
            if(requestCode == CHOOSE_PIC_REQUEST_CODE){
                if(data == null){
                    Toast.makeText(getApplicationContext(), "Image cannot be null!", Toast.LENGTH_LONG).show();
                }else{
                    mMediaUri = data.getData();
                    //set previews
                    mPreviewImageView.setImageURI(mMediaUri);
                }
            }else {

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                mediaScanIntent.setData(mMediaUri);
                sendBroadcast(mediaScanIntent);
                //set previews

                mPreviewImageView.setImageURI(mMediaUri);

            }

        }else if(resultCode != RESULT_CANCELED){
            Toast.makeText(getApplicationContext(), "Cancelled!", Toast.LENGTH_LONG).show();
        }
    }


}




