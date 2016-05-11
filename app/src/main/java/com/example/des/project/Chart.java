package com.example.des.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class Chart extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ParseQuery query = new ParseQuery("ImageUploads");

        //The following is to produce the data for the graph
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                //////////// Hor Vert  ////////
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
        //////////////////////////////////////////////////////
    }
/*

    public ParseQuery create() {
        ParseQuery query = new ParseQuery("ImageUploads");
        query.whereEqualTo("user", ParseUser.getCurrentUser().getObjectId()); // Query ImageUploads table only where the column value equal the current user objectId

        return query;
    }

    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {

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
    }*/
}