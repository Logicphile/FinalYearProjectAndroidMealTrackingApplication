package com.example.des.project;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddMealActivity extends Activity
{
    public static final int TAKE_PIC_REQUEST_CODE = 0;
    public static final int CHOOSE_PIC_REQUEST_CODE = 1;
    public static final int MEDIA_TYPE_IMAGE = 2;


    //ImageView imageView;
   // protected ImageView imgMealPhoto;

    public FloatingActionButton addMealFab;
    public ImageView mPreviewImageView;

    public Uri mMediaUri;

    //public ListView listView;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //listView = (ListView) findViewById(R.id.listView);
        mPreviewImageView = (ImageView) findViewById(R.id.previewImageView);

        //Parse.initialize(this, "1SzsISGqSK4hDLbLyxQaHVxrPcCpbIeTKDK1xwyi", "zd1Kgbe8oTrbStbHf2QQDzkmUOyoc9pYK1r0KVLl");


        addMealFab = (FloatingActionButton) findViewById(R.id.addMealFab);
        //listen to upload button click
        addMealFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                                    imageUpload.put("imageContent", file);
                                    imageUpload.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            Toast.makeText(getApplicationContext(), "Success Uploading iMage!", Toast.LENGTH_LONG).show();
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




