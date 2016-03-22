package com.example.billy.lakemerrittguide;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView activity;
    private TextView restaurants;
    private Cursor cursor;
    private LakeMerrittSQLiteOpenHelper lakeMerrittHelper;
    private Intent resultListIntent;
    private TextView favorites;

    public final static String TITLE_KEY = "TitleName";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setView();

        lakeMerrittHelper = LakeMerrittSQLiteOpenHelper.getInstance(this);
        setLakeMerrittDatabase();

        setOnItemClick();
        setFavoriteButton();



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
                String activitiesTitle = activity.getText().toString();
                resultListIntent.putExtra(TITLE_KEY, activitiesTitle);
                startActivity(resultListIntent);

            }
        });

    }

    public void setView(){
        restaurants = (TextView)findViewById(R.id.restaurantsTextView);
        activity = (TextView)findViewById(R.id.activitiesTextView);
        resultListIntent = new Intent(MainActivity.this, ResultListActivity.class);
        favorites = (TextView)findViewById(R.id.favoriteTextView);

    }


    /**
     * to pull up favorite list
     */

    public void setFavoriteButton(){
        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String favoriteTitle = favorites.getText().toString();
                resultListIntent.putExtra(TITLE_KEY, favoriteTitle);
                startActivity(resultListIntent);

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


            lakeMerrittHelper.listInsert( "Restaurants", "Portal", "1611 2nd Ave", "510-663-7678", "4 stars", "$$", "American (NEW), Breakfast & Brunch, Burgers", getString(R.string.portal_info), R.drawable.portalicon, R.drawable.portalbrunch1, R.drawable.portalfood4);
            lakeMerrittHelper.listInsert( "Restaurants", "Jong Ga House", "372 Grand Ave", "510-444-7678", "5 stars", "$$", "Korean", getString(R.string.jongGA_info), R.drawable.jonggalogo, R.drawable.jonggafood1, R.drawable.jonggabbq2 );
//            lakeMerrittHelper.listInsert( "Restaurants", "Grand Lake Kitchen", "576 Grand Ave", "510-922-9582", "3 stars", "$$$", "American (NEW), Breakfast, Deli", getString(R.string.grandLakeKitchen_info));
//            lakeMerrittHelper.listInsert( "Restaurants", "The Rockin' Crawfish", "211 Foothill Blvd", "510-251-1657", "4 stars", "$$", "Cajun Seafood", getString(R.string.rockinCrawfish_info));
            lakeMerrittHelper.listInsert( "Restaurants", "Haddon Hill Cafe", "504 Wesly Ave", null, "5 stars", "$", "Cafe", getString(R.string.haddonHill_info), R.drawable.haddonhillcafelogo, R.drawable.haddonhilldrinks1, R.drawable.haddondrink3);
//            lakeMerrittHelper.listInsert( "Restaurants", "Arizmendi Bakery", "3265 Lakeshore Ave", "510-268-8849", "5 stars", "$$", "Bakeries, Pizza", getString(R.string.arizmendiBakery_info));
//            lakeMerrittHelper.listInsert( "Restaurants", "Michel Bistro", "3343 Lakeshore Ave", "510-836-8737", "4 stars", "$$", "French, Bar", getString(R.string.michelBistro_info));
//            lakeMerrittHelper.listInsert( "Restaurants", "The Alley", "3325 Grand Ave", "510-444-8505", "4 stars", "$$", "Steakhouse", getString(R.string.theAlley_info));
//            lakeMerrittHelper.listInsert( "Restaurants", "Off The Grid Food Truck", "1000 Oak St", null, "4 stars", "$$", "Food Trucks", getString(R.string.offTheGrid_info));
            lakeMerrittHelper.listInsert( "Activities", "The Gardens", "666 Bellevue Ave", null, null, null, "Parks", getString(R.string.theGardenInfo), R.drawable.thegardenslogo2, R.drawable.birdwatching1, R.drawable.japanesegarden1);
            lakeMerrittHelper.listInsert( "Activities", "Japanese Garden", "666 Bellevue Ave", null, null, null, "Parks", getString(R.string.japaneseGardenInfo), R.drawable.japanesegarden1, R.drawable.japanesegarden2, R.drawable.japanesegarden3);
//            lakeMerrittHelper.listInsert( "Activities", "Palm Garden", "666 Bellevue Ave", null, null, null, "Parks", getString(R.string.palmGardenInfo));
//            lakeMerrittHelper.listInsert( "Activities", "The Mediterranean Garden", "666 Bellevue Ave", null, null, null, "Parks", getString(R.string.mediterraneanGardenInfo));
            lakeMerrittHelper.listInsert( "Activities", "Water Sports", "666 Bellevue Ave", null, null, null, "Sports", getString(R.string.waterSportsInfo), R.drawable.watersailboat, R.drawable.waterrental, R.drawable.waterrental2);
//            lakeMerrittHelper.listInsert( "Activities", "FairyLand", "699 Bellevue Ave", null, null, null, "Parks", getString(R.string.fairyLandInfo));
//            lakeMerrittHelper.listInsert( "Activities", "Exercise", "Lake Merritt", null, null, null, "Sports", getString(R.string.exerciseInfo));
//            lakeMerrittHelper.listInsert( "Activities", "Flea Market", "Grand Ave & Lake Park Ave", null, null, null, "Shops", getString(R.string.fleaMarketInfo));


        }

    }

}
