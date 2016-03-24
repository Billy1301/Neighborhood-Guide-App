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

    private void createDBItems() {

            ArrayList<ThingsToDoClass> restaurantLists = InsertListsForDB.getRestaurantsList(this);
            ArrayList<ThingsToDoClass> activitiesLists = InsertListsForDB.getActivitiesList(this);

            for (ThingsToDoClass restaurantListing : restaurantLists) {
                lakeMerrittHelper.listInsert(restaurantListing.getCategoryTypes(), restaurantListing.getPlaceName(), restaurantListing.getPlaceAddress(), restaurantListing.getPlacePhoneNumber(), restaurantListing.getPlaceRatings(), restaurantListing.getPlacePrice(), restaurantListing.getPlaceType(), restaurantListing.getPlaceInfo(), restaurantListing.getInfoMainImageLogo(),restaurantListing.getInfoImageOne(), restaurantListing.getFavoriteStatus());
            }

            for (ThingsToDoClass activitiesListing : activitiesLists){
                lakeMerrittHelper.listInsert(activitiesListing.getCategoryTypes(), activitiesListing.getPlaceName(), activitiesListing.getPlaceAddress(), activitiesListing.getPlacePhoneNumber(), activitiesListing.getPlaceRatings(), activitiesListing.getPlacePrice(), activitiesListing.getPlaceType(), activitiesListing.getPlaceInfo(), activitiesListing.getInfoMainImageLogo(),activitiesListing.getInfoImageOne(), activitiesListing.getFavoriteStatus());
            }

/*          // this is for backup when refactoring fail
            lakeMerrittHelper.listInsert(RESTAURANTS, getString(R.string.portalName), getString(R.string.portal_address), getString(R.string.portal_phone), getString(R.string.four_stars), getString(R.string.moderatePrice), getString(R.string.portal_type), getString(R.string.portal_info), R.drawable.portalicon, R.drawable.portalbrunch1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(RESTAURANTS, getString(R.string.jongGaName), getString(R.string.jong_ga_address), getString(R.string.jongGaPhone), getString(R.string.five_stars), getString(R.string.moderatePrice), getString(R.string.korean_type), getString(R.string.jongGA_info), R.drawable.jonggalogo, R.drawable.jonggafood1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(RESTAURANTS, getString(R.string.grandLakeName), getString(R.string.grand_lake_address), getString(R.string.grandLakePhone), getString(R.string.three_stars), getString(R.string.high_price), getString(R.string.grandLake_type), getString(R.string.grandLakeKitchen_info), R.drawable.grandlakekitchenlogo, R.drawable.grandlakerunch1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(RESTAURANTS, getString(R.string.rockinCrawfishName), getString(R.string.rockin_crawfish_address), getString(R.string.rockinCrawfishPhone), getString(R.string.four_stars), getString(R.string.moderatePrice), getString(R.string.rockinCrawfish_type), getString(R.string.rockinCrawfish_info), R.drawable.rockincrawfishicon, R.drawable.rockinfoods1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(RESTAURANTS, getString(R.string.haddonCafeName), getString(R.string.haddon_address), null, getString(R.string.five_stars), getString(R.string.low_price), getString(R.string.cafe_type_name), getString(R.string.haddonHill_info), R.drawable.haddonhillcafelogo, R.drawable.haddonhilldrinks1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(RESTAURANTS, getString(R.string.arizmendiName), getString(R.string.arizmendi_address), getString(R.string.arizmedi_phone), getString(R.string.five_stars), getString(R.string.moderatePrice), getString(R.string.arizmendi_type), getString(R.string.arizmendiBakery_info), R.drawable.arizmendibakerylogo, R.drawable.arizmendi2, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(RESTAURANTS, getString(R.string.michelBistroName), getString(R.string.michel_address), getString(R.string.michel_phone), getString(R.string.four_stars), getString(R.string.moderatePrice), getString(R.string.michel_type), getString(R.string.michelBistro_info), R.drawable.michelbistrologo, R.drawable.michelfood2, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(RESTAURANTS, getString(R.string.theAlleyName), getString(R.string.alley_address), getString(R.string.ally_phone), getString(R.string.four_stars), getString(R.string.moderatePrice), getString(R.string.theAlley_type), getString(R.string.theAlley_info), R.drawable.thealleylogo, R.drawable.thealleyfood1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(RESTAURANTS, getString(R.string.offTheGridName), getString(R.string.offthegrid_address), null, getString(R.string.four_stars), getString(R.string.moderatePrice), getString(R.string.offTheGrid_type), getString(R.string.offTheGrid_info), R.drawable.offthegridlogo, R.drawable.offthegridfoodtrucks1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(ACTIVITIES, getString(R.string.theGardenName), getString(R.string.thegardens_address), null, null, null, getString(R.string.parks_type_name), getString(R.string.theGardenInfo), R.drawable.thegardenslogo2, R.drawable.japaneseimagemain, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(ACTIVITIES, getString(R.string.japaneseGardenName), getString(R.string.japanesegarden_address), null, null, null, getString(R.string.parks_type_name), getString(R.string.japaneseGardenInfo), R.drawable.japaneseimage11, R.drawable.japaneseimage22, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(ACTIVITIES, getString(R.string.palmGardenName), getString(R.string.palmgarden_address), null, null, null, getString(R.string.parks_type_name), getString(R.string.palmGardenInfo), R.drawable.palmgarden, R.drawable.palmgarden1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(ACTIVITIES, getString(R.string.mediterranName), getString(R.string.mediterran_address), null, null, null, getString(R.string.parks_type_name), getString(R.string.mediterraneanGardenInfo), R.drawable.mediterraneangarden, R.drawable.mediterrane1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(ACTIVITIES, getString(R.string.waterSportName), getString(R.string.waterSport_address), getString(R.string.waterSport_phone), null, null, getString(R.string.sports_type_name), getString(R.string.waterSportsInfo), R.drawable.watersailboat, R.drawable.waterrental, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(ACTIVITIES, getString(R.string.fairyLandName), getString(R.string.fairyland_address), null, null, null, getString(R.string.parks_type_name), getString(R.string.fairyLandInfo), R.drawable.fairylandlogo, R.drawable.fairyland2, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(ACTIVITIES, getString(R.string.exerciseName), getString(R.string.exercise_address), null, null, null, getString(R.string.sports_type_name), getString(R.string.exerciseInfo), R.drawable.exercising2, R.drawable.exercisestairs, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert(ACTIVITIES, getString(R.string.fleaMarketName), getString(R.string.fleaMarket_address), null, null, null, getString(R.string.shop_type_name), getString(R.string.fleaMarketInfo), R.drawable.fleamarketlogo, R.drawable.fleamarket, REMOVE_FAVORITE);
*/

    }

}
