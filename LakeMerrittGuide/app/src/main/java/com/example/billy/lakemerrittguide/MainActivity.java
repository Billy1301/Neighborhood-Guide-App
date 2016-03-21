package com.example.billy.lakemerrittguide;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton favoriteButton;
    TextView activity;
    TextView restaurants;
    Cursor cursor;
    LakeMerrittSQLiteOpenHelper lakeMerrittHelper;
    Intent resultListIntent;

    public final static String TITLE_KEY = "TitleName";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setView();
        setFavoriteButton();

        lakeMerrittHelper = LakeMerrittSQLiteOpenHelper.getInstance(this);
        setLakeMerrittDatabase();

        setOnItemClick();


    }
    public void setOnItemClick(){

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String restaurantTitle = restaurants.getText().toString();
                resultListIntent.putExtra(TITLE_KEY, restaurantTitle);
                startActivity(resultListIntent);

            }
        });

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ActivitiesTitle = activity.getText().toString();
                resultListIntent.putExtra(TITLE_KEY, ActivitiesTitle);
                startActivity(resultListIntent);

            }
        });

    }

    public void setView(){
        favoriteButton = (FloatingActionButton) findViewById(R.id.fab);
        restaurants = (TextView)findViewById(R.id.restaurantsTextView);
        activity = (TextView)findViewById(R.id.activitiesTextView);
        resultListIntent = new Intent(MainActivity.this, ResultListActivity.class);


    }


    /**
     * to pull up favorite list
     */

    public void setFavoriteButton(){

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Create your favorite list!!!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    /**
     * inserting my list into the database
     */
    public void setLakeMerrittDatabase(){
        SQLiteDatabase db = lakeMerrittHelper.getWritableDatabase();
        String count = "SELECT count(*) FROM " + lakeMerrittHelper.COL_LAKE_MERRITT_TABLE_NAME;
        cursor = db.rawQuery(count, null);
        cursor.moveToFirst();
        int checkCount = cursor.getInt(0);

        if(checkCount<=0) {

         /*   lakeMerrittHelper.listInsert(1, "Restaurants", "Portal", "1611 2nd Ave", "510-663-7678", "4 stars", "$$", "American (NEW), Breakfast & Brunch, Burgers", R.string.portal_info);
            lakeMerrittHelper.listInsert(2, "Restaurants", "Jong Ga House", "372 Grand Ave", "510-444-7678", "5 stars", "$$", "Korean", R.string.jongGA_info);
            lakeMerrittHelper.listInsert(3, "Restaurants", "Grand Lake Kitchen", "576 Grand Ave", "510-922-9582", "3 stars", "$$", "American (NEW), Breakfast, Deli", R.string.grandLakeKitchen_info);
            lakeMerrittHelper.listInsert(4, "Restaurants", "The Rockin Crawfish", "211 Foothill Blvd", "510-251-1657", "4 stars", "$$", "Cajun Seafood", R.string.rockinCrawfish_info);
            lakeMerrittHelper.listInsert(5, "Restaurants", "Haddon Hill Cafe", "504 Wesly Ave", null, "5 stars", "$", "Cafe", R.string.haddonHill_info);
            lakeMerrittHelper.listInsert(6, "Restaurants", "Arizmendi Bakery", "3265 Lakeshore Ave", "510-268-8849", "5 stars", "$$", "Bakeries, Pizza", R.string.arizmendiBakery_info);
            lakeMerrittHelper.listInsert(7, "Restaurants", "Michel Bistro", "3343 Lakeshore Ave", "510-836-8737", "4 stars", "$$", "French, Bar", R.string.michelBistro_info);
            lakeMerrittHelper.listInsert(8, "Restaurants", "The Alley", "3325 Grand Ave", "510-444-8505", "4 stars", "$$", "Steakhouse", R.string.theAlley_info);
            lakeMerrittHelper.listInsert(9, "Restaurants", "Off The Grid Food Truck", "1000 Oak St", null, "4 stars", "$$", "Food Trucks", R.string.offTheGrid_info);
            lakeMerrittHelper.listInsert(10, "Activities", "The Gardens", "666 Bellevue Ave", null, null, null, "Parks", R.string.theGardenInfo);
            lakeMerrittHelper.listInsert(11, "Activities", "Japanese Garden", "666 Bellevue Ave", null, null, null, "Parks", R.string.japaneseGardenInfo);
            lakeMerrittHelper.listInsert(12, "Activities", "Palm Garden", "666 Bellevue Ave", null, null, null, "Parks", R.string.palmGardenInfo);
            lakeMerrittHelper.listInsert(13, "Activities", "The Mediterranean Garden", "666 Bellevue Ave", null, null, null, "Parks", R.string.mediterraneanGardenInfo);
            lakeMerrittHelper.listInsert(14, "Activities", "Water sports", "666 Bellevue Ave", null, null, null, "Sports", R.string.waterSportsInfo);
            lakeMerrittHelper.listInsert(15, "Activities", "FairyLand", "699 Bellevue Ave", null, null, null, "Theme Park", R.string.fairyLandInfo);
            lakeMerrittHelper.listInsert(16, "Activities", "Exercise", "Lake Merritt", null, null, null, "Sports", R.string.exerciseInfo);
            lakeMerrittHelper.listInsert(17, "Activities", "Flea Market", "Grand Ave & Lake Park Ave", null, null, null, "Shops", R.string.fleaMarketInfo);
*/



            lakeMerrittHelper.listInsert(1, "Restaurants", "Portal", "1611 2nd Ave", "510-663-7678", "4 stars", "$$", "American (NEW), Breakfast & Brunch, Burgers", getString(R.string.portal_info));
            lakeMerrittHelper.listInsert(2, "Restaurants", "Jong Ga House", "372 Grand Ave", "510-444-7678", "5 stars", "$$", "Korean", getString(R.string.jongGA_info));
            lakeMerrittHelper.listInsert(3, "Restaurants", "Grand Lake Kitchen", "576 Grand Ave", "510-922-9582", "3 stars", "$$", "American (NEW), Breakfast, Deli", getString(R.string.grandLakeKitchen_info));
            lakeMerrittHelper.listInsert(4, "Restaurants", "The Rockin Crawfish", "211 Foothill Blvd", "510-251-1657", "4 stars", "$$", "Cajun Seafood", getString(R.string.rockinCrawfish_info));
            lakeMerrittHelper.listInsert(5, "Restaurants", "Haddon Hill Cafe", "504 Wesly Ave", null, "5 stars", "$", "Cafe", getString(R.string.haddonHill_info));
            lakeMerrittHelper.listInsert(6, "Restaurants", "Arizmendi Bakery", "3265 Lakeshore Ave", "510-268-8849", "5 stars", "$$", "Bakeries, Pizza", getString(R.string.arizmendiBakery_info));
            lakeMerrittHelper.listInsert(7, "Restaurants", "Michel Bistro", "3343 Lakeshore Ave", "510-836-8737", "4 stars", "$$", "French, Bar", getString(R.string.michelBistro_info));
            lakeMerrittHelper.listInsert(8, "Restaurants", "The Alley", "3325 Grand Ave", "510-444-8505", "4 stars", "$$", "Steakhouse", getString(R.string.theAlley_info));
            lakeMerrittHelper.listInsert(9, "Restaurants", "Off The Grid Food Truck", "1000 Oak St", null, "4 stars", "$$", "Food Trucks", getString(R.string.offTheGrid_info));
            lakeMerrittHelper.listInsert(10, "Activities", "The Gardens", "666 Bellevue Ave", null, null, null, "Parks", getString(R.string.theGardenInfo));
            lakeMerrittHelper.listInsert(11, "Activities", "Japanese Garden", "666 Bellevue Ave", null, null, null, "Parks", getString(R.string.japaneseGardenInfo));
            lakeMerrittHelper.listInsert(12, "Activities", "Palm Garden", "666 Bellevue Ave", null, null, null, "Parks", getString(R.string.palmGardenInfo));
            lakeMerrittHelper.listInsert(13, "Activities", "The Mediterranean Garden", "666 Bellevue Ave", null, null, null, "Parks", getString(R.string.mediterraneanGardenInfo));
            lakeMerrittHelper.listInsert(14, "Activities", "Water sports", "666 Bellevue Ave", null, null, null, "Sports", getString(R.string.waterSportsInfo));
            lakeMerrittHelper.listInsert(15, "Activities", "FairyLand", "699 Bellevue Ave", null, null, null, "Theme Park", getString(R.string.fairyLandInfo));
            lakeMerrittHelper.listInsert(16, "Activities", "Exercise", "Lake Merritt", null, null, null, "Sports", getString(R.string.exerciseInfo));
            lakeMerrittHelper.listInsert(17, "Activities", "Flea Market", "Grand Ave & Lake Park Ave", null, null, null, "Shops", getString(R.string.fleaMarketInfo));


        }

    }

}
