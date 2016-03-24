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
    private TextView viewAll;

    public final static String RESTAURANTS = "Restaurants";
    public final static String ACTIVITIES = "Activities";
    public final static String FAVORITES = "Favorites";
    public final static String VIEW_ALL = "View All";

    public final static String TITLE_KEY = "TitleName";
    public final static String ADD_FAVORITE = "iDoLikeThis";
    public final static String REMOVE_FAVORITE = "iDoNotLikeThis";

    // should create a string for the name and use it

    public final static String PORTAL = "Portal";
    public final static String JONG_GA = "Jong Ga House";
    public final static String GRAND_LAKE = "Grand Lake Kitchen";
    public final static String THE_ROCKIN_CRAWFISH = "The Rockin Crawfish";
    public final static String HADDON_HILL = "Haddon Hill Cafe";
    public final static String ARIZMENDI_BAKERY = "Arizmendi Bakery";
    public final static String MICHEL_BISTRO = "Michel Bistro";
    public final static String THE_ALLEY = "The Alley";
    public final static String OFF_THE_GRID = "Off the Grid Food Truck";
    public final static String THE_GARDENS = "The Gardens";
    public final static String JAPANESE_GARDEN = "Japanese Garden";
    public final static String PALM_GARDEN = "Palm Garden";
    public final static String THE_MEDITERRANEAN = "The Mediterranean Garden";
    public final static String WATER_SPORTS = "Water Sports";
    public final static String FAIRYLAND = "FairyLand";
    public final static String EXERCISE = "Exercise";
    public final static String FLEA_MARKET = "Flea Market";



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




    /**
     * Set Click Listener for the Text
     */
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

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String viewAllTitle = viewAll.getText().toString();
                resultListIntent.putExtra(TITLE_KEY, viewAllTitle);
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

            lakeMerrittHelper.listInsert( RESTAURANTS, PORTAL, getString(R.string.portal_address), "510-663-7678", "4 stars", "$$", "American, Breakfast & Brunch", getString(R.string.portal_info), R.drawable.portalicon, R.drawable.portalbrunch1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, JONG_GA, getString(R.string.jong_ga_address), "510-444-7678", "5 stars", "$$", "Korean", getString(R.string.jongGA_info), R.drawable.jonggalogo, R.drawable.jonggafood1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, GRAND_LAKE, getString(R.string.grand_lake_address), "510-922-9582", "3 stars", "$$$", "American, Breakfast & Brunch, Deli", getString(R.string.grandLakeKitchen_info), R.drawable.grandlakekitchenlogo, R.drawable.grandlakerunch1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, THE_ROCKIN_CRAWFISH, getString(R.string.rockin_crawfish_address), "510-251-1657", "4 stars", "$$", "Cajun Seafood", getString(R.string.rockinCrawfish_info), R.drawable.rockincrawfishicon, R.drawable.rockinfoods1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, HADDON_HILL, getString(R.string.haddon_address), null, "5 stars", "$", "Cafe", getString(R.string.haddonHill_info), R.drawable.haddonhillcafelogo, R.drawable.haddonhilldrinks1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, ARIZMENDI_BAKERY, getString(R.string.arizmendi_address), "510-268-8849", "5 stars", "$$", "Bakeries, Pizza", getString(R.string.arizmendiBakery_info), R.drawable.arizmendibakerylogo, R.drawable.arizmendi2, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, MICHEL_BISTRO, getString(R.string.michel_address), "510-836-8737", "4 stars", "$$", "French, Bar", getString(R.string.michelBistro_info), R.drawable.michelbistrologo, R.drawable.michelfood2, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, THE_ALLEY, getString(R.string.alley_address), "510-444-8505", "4 stars", "$$", "Steakhouse, Bar", getString(R.string.theAlley_info), R.drawable.thealleylogo, R.drawable.thealleyfood1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, OFF_THE_GRID, getString(R.string.offthegrid_address), null, "4 stars", "$$", "Food Trucks", getString(R.string.offTheGrid_info), R.drawable.offthegridlogo, R.drawable.offthegridfoodtrucks1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, THE_GARDENS, getString(R.string.thegardens_address), null, null, null, "Parks", getString(R.string.theGardenInfo), R.drawable.thegardenslogo2, R.drawable.japaneseimagemain, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, JAPANESE_GARDEN, getString(R.string.japanesegarden_address), null, null, null, "Parks", getString(R.string.japaneseGardenInfo), R.drawable.japaneseimage11, R.drawable.japaneseimage22, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, PALM_GARDEN, getString(R.string.palmgarden_address), null, null, null, "Parks", getString(R.string.palmGardenInfo), R.drawable.palmgarden, R.drawable.palmgarden1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, THE_MEDITERRANEAN, getString(R.string.mediterran_address), null, null, null, "Parks", getString(R.string.mediterraneanGardenInfo), R.drawable.mediterraneangarden, R.drawable.mediterrane1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, WATER_SPORTS, getString(R.string.waterSport_address), "510-238-2196", null, null, "Sports", getString(R.string.waterSportsInfo), R.drawable.watersailboat, R.drawable.waterrental,REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, FAIRYLAND, getString(R.string.fairyland_address), null, null, null, "Parks", getString(R.string.fairyLandInfo), R.drawable.fairylandlogo, R.drawable.fairyland2, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, EXERCISE, getString(R.string.exercise_address), null, null, null, "Sports", getString(R.string.exerciseInfo), R.drawable.exercising2, R.drawable.exercisestairs, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, FLEA_MARKET, getString(R.string.fleaMarket_address), null, null, null, "Shops", getString(R.string.fleaMarketInfo), R.drawable.fleamarketlogo, R.drawable.fleamarket, REMOVE_FAVORITE);

/*
            // this is for backup.. need to switch out one image
            lakeMerrittHelper.listInsert( RESTAURANTS, PORTAL, getString(R.string.portal_address), "510-663-7678", "4 stars", "$$", "American, Breakfast & Brunch", getString(R.string.portal_info), R.drawable.portalicon, R.drawable.portalbrunch1, R.drawable.portalfood4, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, JONG_GA, getString(R.string.jong_ga_address), "510-444-7678", "5 stars", "$$", "Korean", getString(R.string.jongGA_info), R.drawable.jonggalogo, R.drawable.jonggafood1, R.drawable.jonggabbq2, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, GRAND_LAKE, getString(R.string.grand_lake_address), "510-922-9582", "3 stars", "$$$", "American, Breakfast & Brunch, Deli", getString(R.string.grandLakeKitchen_info), R.drawable.grandlakekitchenlogo, R.drawable.grandlakerunch1, R.drawable.grandlakebrunch4, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, THE_ROCKIN_CRAWFISH, getString(R.string.rockin_crawfish_address), "510-251-1657", "4 stars", "$$", "Cajun Seafood", getString(R.string.rockinCrawfish_info), R.drawable.rockincrawfishicon, R.drawable.rockinfoods1, R.drawable.rockinfoods2, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, HADDON_HILL, getString(R.string.haddon_address), null, "5 stars", "$", "Cafe", getString(R.string.haddonHill_info), R.drawable.haddonhillcafelogo, R.drawable.haddonhilldrinks1, R.drawable.haddondrink3, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, ARIZMENDI_BAKERY, getString(R.string.arizmendi_address), "510-268-8849", "5 stars", "$$", "Bakeries, Pizza", getString(R.string.arizmendiBakery_info), R.drawable.arizmendibakerylogo, R.drawable.arizmendi2, R.drawable.arizmendi3, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, MICHEL_BISTRO, getString(R.string.michel_address), "510-836-8737", "4 stars", "$$", "French, Bar", getString(R.string.michelBistro_info), R.drawable.michelbistrologo, R.drawable.michelfood2, R.drawable.michelfood6, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, THE_ALLEY, getString(R.string.alley_address), "510-444-8505", "4 stars", "$$", "Steakhouse", getString(R.string.theAlley_info), R.drawable.thealleylogo, R.drawable.thealleyfood1, R.drawable.thealleyfood, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( RESTAURANTS, OFF_THE_GRID, getString(R.string.offthegrid_address), null, "4 stars", "$$", "Food Trucks", getString(R.string.offTheGrid_info), R.drawable.offthegridlogo, R.drawable.offthegridfoodtrucks1, R.drawable.offthegridfood2, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, THE_GARDENS, getString(R.string.thegardens_address), null, null, null, "Parks", getString(R.string.theGardenInfo), R.drawable.thegardenslogo2, R.drawable.birdwatching1, R.drawable.japanesegarden1, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, JAPANESE_GARDEN, getString(R.string.japanesegarden_address), null, null, null, "Parks", getString(R.string.japaneseGardenInfo), R.drawable.japaneseimage11, R.drawable.japaneseimagemain, R.drawable.japaneseimage22, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, PALM_GARDEN, getString(R.string.palmgarden_address), null, null, null, "Parks", getString(R.string.palmGardenInfo), R.drawable.palmgarden, R.drawable.palmgarden1, R.drawable.palmgarden2, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, THE_MEDITERRANEAN, getString(R.string.mediterran_address), null, null, null, "Parks", getString(R.string.mediterraneanGardenInfo), R.drawable.mediterraneangarden, R.drawable.mediterrane1, R.drawable.meditterdaisybutterfly, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, WATER_SPORTS, getString(R.string.waterSport_address), "510-238-2196", null, null, "Sports", getString(R.string.waterSportsInfo), R.drawable.watersailboat, R.drawable.waterrental, R.drawable.waterrental2, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, FAIRYLAND, getString(R.string.fairyland_address), null, null, null, "Parks", getString(R.string.fairyLandInfo), R.drawable.fairylandlogo, R.drawable.fairyland2, R.drawable.fairyland3, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, EXERCISE, getString(R.string.exercise_address), null, null, null, "Sports", getString(R.string.exerciseInfo), R.drawable.exercising2, R.drawable.exercisestairs, R.drawable.exercising4, REMOVE_FAVORITE);
            lakeMerrittHelper.listInsert( ACTIVITIES, FLEA_MARKET, getString(R.string.fleaMarket_address), null, null, null, "Shops", getString(R.string.fleaMarketInfo), R.drawable.fleamarketlogo, R.drawable.fleamarket, R.drawable.fleamarket2, REMOVE_FAVORITE);
*/


        }

    }

}
