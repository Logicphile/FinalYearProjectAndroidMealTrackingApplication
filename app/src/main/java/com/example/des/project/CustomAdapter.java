// This class is responsible for the custom layout of each list item (eg image on left with description, calories and timestamp stacked to the right of the image

package com.example.des.project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

public class CustomAdapter extends ParseQueryAdapter<ParseObject> {

    public CustomAdapter(Context context) {

        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("ImageUploads");
                query.whereEqualTo("user", ParseUser.getCurrentUser().getObjectId()); // Query ImageUploads table only where the column value equal the current user objectId

                return query;
            }
        });
    }

    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.meal_item, null);
        }

        super.getItemView(object, v, parent);

        // Add and download the image
        ParseImageView mealImage = (ParseImageView) v.findViewById(R.id.icon);
        ParseFile imageFile = object.getParseFile("imageContent");
        if (imageFile != null) {
            mealImage.setParseFile(imageFile);
            mealImage.loadInBackground();
        }

        // Add the food item decription view
        TextView itemDescripView = (TextView) v.findViewById(R.id.text1);
        itemDescripView.setText(object.getString("body"));

        // Add the calories view
        TextView caloriesTextView = (TextView) v.findViewById(R.id.text2);
        caloriesTextView.setText(object.getString("calories"));

        // Add a timestamp
        TextView timestampView = (TextView) v.findViewById(R.id.timestamp);
        timestampView.setText(object.getCreatedAt().toString());
        return v;
    }

}
