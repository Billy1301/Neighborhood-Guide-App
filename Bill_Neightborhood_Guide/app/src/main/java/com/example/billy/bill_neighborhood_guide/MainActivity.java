package com.example.billy.bill_neighborhood_guide;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button searchButton;
    EditText userSearchInput;
    TextView activity;
    TextView restaurants;
    ImageView mainImage;
    LakeMerrittSQLiteOpenHelper merrittHelper;

    public final static String TITLE_KEY = "TitleName";
    public final static String LIST_KEY = "listKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setView();
        floatButton();
        setOnItemClick();



        merrittHelper = LakeMerrittSQLiteOpenHelper.getInstance(this);

        merrittHelper.listInsert(1, "Restaurants", "Portal", "1611 2nd Ave", "510-663-7678", "4 stars", "$$", "Breakfast");
        merrittHelper.listInsert(2, "Restaurants", "Jong Ga House", "372 Grand Ave", "510-444-7678", "5 stars", "$$", "Korean");
        merrittHelper.listInsert(3, "Restaurants", "Grand Lake Kitchen", "576 Grand Ave", "510-922-9582", "3 stars", "$$", "American");
        merrittHelper.listInsert(4, "Restaurants", "The Rockin Crawfish", "211 Foothill Blvd", "510-251-1657", "4 stars", "$$", "Cajun Seafood");
        merrittHelper.listInsert(5, "Restaurants", "Haddon Hill Cafe", "504 Wesly Ave", null, "5 stars", "$", "Cafe");
        merrittHelper.listInsert(6, "Restaurants", "Arizmendi Bakery", "3265 Lakeshore Ave", "510-268-8849", "5 stars", "$$", "Bakeries");
        merrittHelper.listInsert(7, "Restaurants", "Michel Bistro", "3343 Lakeshore Ave", "510-836-8737", "4 stars", "$$", "French");
        merrittHelper.listInsert(8, "Restaurants", "The Alley", "3325 Grand Ave", "510-444-8505", "4 stars", "$$", "Steakhouse");
        merrittHelper.listInsert(9, "Restaurants", "Off The Grid Food Truck", "1000 Oak St", null, "4 stars", "$$", "Breakfast");
        merrittHelper.listInsert(10, "Activities", "The Gardens", "666 Bellevue Ave", null, null, null, "Parks");
        merrittHelper.listInsert(11, "Activities", "Japanese Garden", "666 Bellevue Ave", null, null, null, "Parks");
        merrittHelper.listInsert(12, "Activities", "Palm Garden", "666 Bellevue Ave", null, null, null, "Parks");
        merrittHelper.listInsert(13, "Activities", "The Mediterranean Garden", "666 Bellevue Ave", null, null, null, "Parks");
        merrittHelper.listInsert(14, "Activities", "Water sports", "666 Bellevue Ave", null, null, null, "Sports");
        merrittHelper.listInsert(15, "Activities", "FairyLand", "699 Bellevue Ave", null, null, null, "Theme Park");
        merrittHelper.listInsert(16, "Activities", "Exercise", "Lake Merritt", null, null, null, "Sports");
        merrittHelper.listInsert(17, "Activities", "Flea Market", "Grand Ave & Lake Park Ave", null, null, null, "Shops");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_main, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }


    /**
     * item click will pull up the Lists of selected category and display the name on the Title
     */

    public void setOnItemClick(){

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultListIntent = new Intent(MainActivity.this, ListResultsActivity.class);
                String restaurantTitle = restaurants.getText().toString();
                resultListIntent.putExtra(TITLE_KEY, restaurantTitle);
                startActivity(resultListIntent);


            }
        });


        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultListIntent = new Intent(MainActivity.this, ListResultsActivity.class);
                String ActivitiesTitle = activity.getText().toString();
                resultListIntent.putExtra(TITLE_KEY, ActivitiesTitle);
                startActivity(resultListIntent);

            }
        });

    }


    /**
     * floatButton pull up favorite list
     *
     */
    public void floatButton(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.float_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }




    public void setView(){
        restaurants = (TextView)findViewById(R.id.restuarant_textview);
        activity = (TextView)findViewById(R.id.activity_textview);
        mainImage = (ImageView)findViewById(R.id.main_image);

    }


}
