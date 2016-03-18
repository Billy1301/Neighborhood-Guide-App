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
    ListView lakeMerrittListView;
    CursorAdapter cursorAdapter;
    Cursor cursor;
    LakeMerrittSQLiteOpenHelper lakeMerrittHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        favoriteButton = (FloatingActionButton) findViewById(R.id.fab);

        setFavoriteButton();

        lakeMerrittHelper = LakeMerrittSQLiteOpenHelper.getInstance(this);
        setLakeMerrittDatabase();

        cursor = LakeMerrittSQLiteOpenHelper.getInstance(this).getLakeMerrittLists();

        cursorAdapter = new CursorAdapter(this, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.custom_layout, parent, false);
//                return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
//                TextView textView = (TextView) view.findViewById(android.R.id.text1);
//                textView.setText("Mee");
                TextView nameTextView = (TextView) view.findViewById(R.id.place_title_name);
                TextView addressTextView = (TextView) view.findViewById(R.id.address);
                TextView ratingTextView = (TextView) view.findViewById(R.id.rating);
                ImageView resultListImage = (ImageView) view.findViewById(R.id.list_event_image);
//
                nameTextView.setText(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_PLACE_NAME)));
                addressTextView.setText(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_ADDRESS)));
                ratingTextView.setText(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_RATINGS)));


                if (nameTextView.equals("Portal")) {
                   resultListImage.setImageResource(R.drawable.star);
                }

            }
        };

        lakeMerrittListView = (ListView) findViewById(R.id.main_listView);
        lakeMerrittListView.setAdapter(cursorAdapter);

        handleIntent(getIntent());


    }


    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            cursor = lakeMerrittHelper.searchLakeMerrittList(query);
            cursorAdapter.changeCursor(cursor);
            cursorAdapter.notifyDataSetChanged();
        }
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
     * this is for the search function
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
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

/*
        lakeMerrittHelper.listInsert(1, "Restaurants", "Portal", "1611 2nd Ave", "510.663.7678", "4 stars", "$$", "Breakfast");
        lakeMerrittHelper.listInsert(2, "Restaurants", "Jong Ga House", "372 Grand Ave", "510-444-7678", "5 stars", "$$", "Korean");
        lakeMerrittHelper.listInsert(3, "Restaurants", "Grand Lake Kitchen", "576 Grand Ave", "510-922-9582", "3 stars", "$$", "American");
        lakeMerrittHelper.listInsert(4, "Restaurants", "The Rockin Crawfish", "211 Foothill Blvd", "510-251-1657", "4 stars", "$$", "Cajun Seafood");
        lakeMerrittHelper.listInsert(5, "Restaurants", "Haddon Hill Cafe", "504 Wesly Ave", null, "5 stars", "$", "Cafe");
        lakeMerrittHelper.listInsert(6, "Restaurants", "Arizmendi Bakery", "3265 Lakeshore Ave", "510-268-8849", "5 stars", "$$", "Bakeries");
        lakeMerrittHelper.listInsert(7, "Restaurants", "Michel Bistro", "3343 Lakeshore Ave", "510-836-8737", "4 stars", "$$", "French");
        lakeMerrittHelper.listInsert(8, "Restaurants", "The Alley", "3325 Grand Ave", "510-444-8505", "4 stars", "$$", "Steakhouse");
        lakeMerrittHelper.listInsert(9, "Restaurants", "Off The Grid Food Truck", "1000 Oak St", null, "4 stars", "$$", "Breakfast");
        lakeMerrittHelper.listInsert(10, "Activities", "The Gardens", "666 Bellevue Ave", null, null, null, "Parks");
        lakeMerrittHelper.listInsert(11, "Activities", "Japanese Garden", "666 Bellevue Ave", null, null, null, "Parks");
        lakeMerrittHelper.listInsert(12, "Activities", "Palm Garden", "666 Bellevue Ave", null, null, null, "Parks");
        lakeMerrittHelper.listInsert(13, "Activities", "The Mediterranean Garden", "666 Bellevue Ave", null, null, null, "Parks");
        lakeMerrittHelper.listInsert(14, "Activities", "Water sports", "666 Bellevue Ave", null, null, null, "Sports");
        lakeMerrittHelper.listInsert(15, "Activities", "FairyLand", "699 Bellevue Ave", null, null, null, "Theme Park");
        lakeMerrittHelper.listInsert(16, "Activities", "Exercise", "Lake Merritt", null, null, null, "Sports");
        lakeMerrittHelper.listInsert(17, "Activities", "Flea Market", "Grand Ave & Lake Park Ave", null, null, null, "Shops");
*/
        }

    }

}
