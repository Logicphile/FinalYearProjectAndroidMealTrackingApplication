// This class is the main class and is responsible for functionality such as displaying only the currently logged in user's meals
package com.example.des.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ParseQueryAdapter<ParseObject> mainAdapter;
    private CustomAdapter mealAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize main ParseQueryAdapter
        mainAdapter = new ParseQueryAdapter<ParseObject>(this, "imageUploads");
        mainAdapter.setTextKey("title");
        mainAdapter.setImageKey("image");

        // Initialize the subclass of ParseQueryAdapter
        mealAdapter = new CustomAdapter(this);

        // Initialize ListView and set initial view to mainAdapter
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(mealAdapter);
        mealAdapter.loadObjects();

        /////////////////////////////////////////////////////////////////////////////////////////////

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        /////////////////////////////////////    LOGOUT OVERFLOW OPTION    ///////////////////////////////////////

        //The below if statement logs the user out of the database if the option to log out is pressed in the overflow menu of the main activity
        if (id == R.id.log_out) {
            ParseUser.getCurrentUser().logOut();
            startActivity(new Intent(MainActivity.this, LoginCheck.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_chronographicSelect) {
            // Handle the daily nav menu action
        } else if (id == R.id.nav_graph)
        {
            // Open the chart screen
            Intent intent = new Intent(MainActivity.this, Chart.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // The below is the method used when a click is registered from the "AddMeal" floating action button.
    public void clickFABAddMeal(View view) {
        Intent i = new Intent(MainActivity.this, AddMealActivity.class);
        startActivity(i);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    // The below is the method used when a click is registered from the floating action buttons
    public void clickFAB(View view) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivity(intent);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    public void clickFABscan(View view) {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        startActivity(intent);
    }


    public void scanQRCode(View v) {
        IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
        integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
    }
}
