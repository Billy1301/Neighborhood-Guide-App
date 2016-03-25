package com.example.billy.lakemerrittguide;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //region Private Variables
    private TextView activity;
    private TextView restaurants;
    private Cursor cursor;
    private LakeMerrittSQLiteOpenHelper lakeMerrittHelper;
    private Intent resultListIntent;
    private TextView favorites;
    private TextView viewAll;
    //endregion Private Variables

    //region Public Variables
    public final static String RESTAURANTS = "Restaurants";
    public final static String ACTIVITIES = "Activities";
    public final static String FAVORITES = "Favorites";
    public final static String VIEW_ALL = "View All";

    public final static String TITLE_KEY = "TitleName";
    public final static String ADD_FAVORITE = "iDoLikeThis";
    public final static String REMOVE_FAVORITE = "iDoNotLikeThis";
    //endregion Public Variables


    /**
     * this is the main page
     *
     */

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

    }



    /**
     * Setting the Click Listener for the next activity Title
     */

    public void setOnItemClick(){
        setItemClicker(restaurants, RESTAURANTS);
        setItemClicker(activity, ACTIVITIES);
        setItemClicker(viewAll, VIEW_ALL);
        setItemClicker(favorites, FAVORITES);
    }

    public void setItemClicker(TextView textView, final String title){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultListIntent.putExtra(TITLE_KEY, title);
                startActivity(resultListIntent);
            }
        });


    }

    /**
     * setup all the views
     */
    public void setView(){
        restaurants = (TextView)findViewById(R.id.restaurantsTextView);
        activity = (TextView)findViewById(R.id.activitiesTextView);
        resultListIntent = new Intent(MainActivity.this, ResultListActivity.class);
        favorites = (TextView)findViewById(R.id.favoriteTextView);
        viewAll = (TextView)findViewById(R.id.vielAllTextView);
    }


    /**
     * inserting my list into the database
     */
    public void setLakeMerrittDatabase() {
        SQLiteDatabase db = lakeMerrittHelper.getWritableDatabase();
        String count = "SELECT count(*) FROM " + lakeMerrittHelper.COL_LAKE_MERRITT_TABLE_NAME;
        cursor = db.rawQuery(count, null);
        cursor.moveToFirst();
        int checkCount = cursor.getInt(0);

        if (checkCount <= 0) {

            createDBItems();

        }
    }



    /**
     * assigning classes to DB
     */

    private void createDBItems() {

            ArrayList<ThingsToDoClass> restaurantLists = InsertListsForDB.getRestaurantsList(this);
            ArrayList<ThingsToDoClass> activitiesLists = InsertListsForDB.getActivitiesList(this);

            for (ThingsToDoClass restaurantListing : restaurantLists) {
                lakeMerrittHelper.listInsert(restaurantListing.getCategoryTypes(), restaurantListing.getPlaceName(), restaurantListing.getPlaceAddress(), restaurantListing.getPlacePhoneNumber(), restaurantListing.getPlaceRatings(), restaurantListing.getPlacePrice(), restaurantListing.getPlaceType(), restaurantListing.getPlaceInfo(), restaurantListing.getInfoMainImageLogo(),restaurantListing.getInfoImageOne(), restaurantListing.getFavoriteStatus());
            }

            for (ThingsToDoClass activitiesListing : activitiesLists){
                lakeMerrittHelper.listInsert(activitiesListing.getCategoryTypes(), activitiesListing.getPlaceName(), activitiesListing.getPlaceAddress(), activitiesListing.getPlacePhoneNumber(), activitiesListing.getPlaceRatings(), activitiesListing.getPlacePrice(), activitiesListing.getPlaceType(), activitiesListing.getPlaceInfo(), activitiesListing.getInfoMainImageLogo(),activitiesListing.getInfoImageOne(), activitiesListing.getFavoriteStatus());
            }


    }

}
