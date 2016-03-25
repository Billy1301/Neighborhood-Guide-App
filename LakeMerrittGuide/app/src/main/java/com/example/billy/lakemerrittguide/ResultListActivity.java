package com.example.billy.lakemerrittguide;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ResultListActivity extends AppCompatActivity  {


    //region Private Variables
    private TextView resultTitleName;
    private ListView lakeMerrittListView;
    private CursorAdapter cursorAdapter;
    private Cursor cursor;
    private LakeMerrittSQLiteOpenHelper lakeMerrittHelper;
    private ArrayAdapter<String> typeFilterAdapter, priceFilterAdapter, ratingFilterAdapter;
    private Spinner typeFilterSpinner, priceFilterSpinner, ratingFilterSpinner;
    private List<String> typeFilterLists, priceFilterLists, ratingFilterLists;
    private ImageView titleImageLogo;
    private Intent intent;

    private final static String PORTAL = "Portal";
    private final static String JONG_GA = "Jong Ga House";
    private final static String GRAND_LAKE = "Grand Lake Kitchen";
    private final static String THE_ROCKIN_CRAWFISH = "The Rockin Crawfish";
    private final static String HADDON_HILL = "Haddon Hill Cafe";
    private final static String ARIZMENDI_BAKERY = "Arizmendi Bakery";
    private final static String MICHEL_BISTRO = "Michel Bistro";
    private final static String THE_ALLEY = "The Alley";
    private final static String OFF_THE_GRID = "Off the Grid Food Truck";
    private final static String THE_GARDENS = "The Gardens";
    private final static String JAPANESE_GARDEN = "Japanese Garden";
    private final static String PALM_GARDEN = "Palm Garden";
    private final static String THE_MEDITERRANEAN = "The Mediterranean Garden";
    private final static String WATER_SPORTS = "Water Sports";
    private final static String FAIRYLAND = "FairyLand";
    private final static String EXERCISE = "Exercise";
    private final static String FLEA_MARKET = "Flea Market";
    //endregion Private Variables

    public static final String ID_KEY_SENDING = "_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_result);

        setView();
        setTitleAndLogosAndSpinner();
        setCursor();
        setCustomCursorAdapter();
        handleIntent(getIntent());
        lakeMerrittListView.setAdapter(cursorAdapter);

        setListViewItemClicker();


        /**
         * these are for the filter spinners
         */
        typeFilterLists = new ArrayList<String>();
        priceFilterLists = new ArrayList<String>();
        ratingFilterLists = new ArrayList<String>();

        setFilterNames();
        setSpinner();

        setFilterClicker();


    }

    /**
     * This setup the Spinner Item to filter out listing to the pre-set choices
     * There are two ways to do below. typeFilter example does seem like a lot of codes
     * Price and rating examples looks more cleaner, try to use it as often as possible
     */

    public void setFilterClicker(){
        typeFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
                    if (position == 0) {
                        cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getRestaurantList();
                    }
                    if (position == 1) {
                        cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getKoreanList();
                    }
                    if (position == 2) {
                        cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getBreakfastList();
                    }
                    if (position == 3) {
                        cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getSteakhouseList();
                    }

                } else if (resultTitleName.getText().equals(MainActivity.ACTIVITIES)) {
                    if (position == 0) {
                        cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getActivitiesList();
                    }
                    if (position == 1) {
                        cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getSportsList();
                    }
                    if (position == 2) {
                        cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getParksList();
                    }
                    if (position == 3) {
                        cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getShopList();
                    }
                } else if (resultTitleName.getText().equals(MainActivity.VIEW_ALL)) {
                    if (position == 0) {
                        cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getLakeMerrittLists();
                    }
                    if (position == 1) {
                        cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getActivitiesList();
                    }
                    if (position == 2) {
                        cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getRestaurantList();
                    }
                }
                changeCursorRefreshAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        priceFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
                    return;
                    }
                    if (position == 0) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getRestaurantList();
                    } else {
                        //this part call the method below and set it accordingly
                        String price = getPriceStringFromSpinnerPosition(position);
                        cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getPriceList(price);
                    }
                    changeCursorRefreshAdapter();
            }

            @Override
            public void onNothingSelected (AdapterView < ? > parent){

                }
            }

        );

        /**
         * This is more a clean version to look at. First create a method with position argument with switch/case statements
         * Then you can use it below with the SQLHelper class
         */
        ratingFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected (AdapterView < ? > parent, View view,int position,
            long id){
                if (!resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
                return;
                }
                if (position == 0) {
                    cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getRestaurantList();
                } else {
                    String rating = getRatingStringFromSpinnerPosition(position);
                    cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getRatingLists(rating);
                }

                changeCursorRefreshAdapter();
            }

            @Override
            public void onNothingSelected (AdapterView < ? > parent){

                }
            }
        );
    }

    /**
     * This method assign the spinner position to the price spinner.
     * int pos will be placed into the spinner dialog box. 1 will set to $, 2 will set to $$, 3 = $$$
     * @param pos
     * @return
     */
    private String getPriceStringFromSpinnerPosition(int pos){
        String price;
        switch (pos){
            case 1:
                price = "$";
                break;
            case 2:
                price = "$$";
                break;
            case 3:
                price = "$$$";
                break;
            default:
                price = "$";
        }
        return price;
    }

    /**
     * same logic as the price
     * @param pos
     * @return
     */

    private String getRatingStringFromSpinnerPosition(int pos){
        String rating;
        switch (pos) {
            case 1:
                rating = "3 stars";
                break;
            case 2:
                rating = "4 stars";
                break;
            case 3:
                rating = "5 stars";
                break;
            default:
                rating = "3 stars";

        }
            return rating;
    }


    public void changeCursorRefreshAdapter(){
        cursorAdapter.changeCursor(cursor);
        cursorAdapter.notifyDataSetChanged();
    }


    /**
     * this setup the dialog names for the Spinners
     */
    public void setFilterNames() {

        if (resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
            typeFilterLists.add(getString(R.string.show_all_filter));
            typeFilterLists.add("Korean");
            typeFilterLists.add("Brunch");
            typeFilterLists.add("Steakhouse");

            priceFilterLists.add("Price - Show All");
            priceFilterLists.add("$");
            priceFilterLists.add("$$");
            priceFilterLists.add("$$$");

            ratingFilterLists.add("Ratings - Show All");
            ratingFilterLists.add(getString(R.string.three_stars));
            ratingFilterLists.add(getString(R.string.four_stars));
            ratingFilterLists.add(getString(R.string.five_stars));


        } else if (resultTitleName.getText().equals(MainActivity.ACTIVITIES)) {
            typeFilterLists.add(getString(R.string.show_all_filter));
            typeFilterLists.add(getString(R.string.sports_type_name));
            typeFilterLists.add(getString(R.string.parks_type_name));
            typeFilterLists.add(getString(R.string.shop_type_name));

            priceFilterLists.add(getString(R.string.show_all_filter));
            ratingFilterLists.add(getString(R.string.show_all_filter));


        } else if (resultTitleName.getText().equals(MainActivity.VIEW_ALL)) {
            typeFilterLists.add(getString(R.string.show_all_filter));
            typeFilterLists.add(MainActivity.ACTIVITIES);
            typeFilterLists.add(MainActivity.RESTAURANTS);

            priceFilterLists.add(getString(R.string.show_all_filter));
            ratingFilterLists.add(getString(R.string.show_all_filter));


        }
    }


    /**
     * this connects the spinner to the ArrayList
     */
    public void setSpinner(){
        typeFilterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeFilterLists);
        priceFilterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, priceFilterLists);
        ratingFilterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ratingFilterLists);

        typeFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratingFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeFilterSpinner.setAdapter(typeFilterAdapter);
        priceFilterSpinner.setAdapter(priceFilterAdapter);
        ratingFilterSpinner.setAdapter(ratingFilterAdapter);

    }

    /**
     *clicking on any item will send you to the detail activity
     *setup a delay to start to show each click will lights up
     */

    public void setListViewItemClicker(){
        lakeMerrittListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor selectedItem = (Cursor) parent.getItemAtPosition(position);
                int databaseID = selectedItem.getInt(selectedItem.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_ID));
                intent.putExtra(ID_KEY_SENDING, databaseID);

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // this code will be executed after some delay
                        startActivity(intent);
                    }
                }, 500);
            }
        });

    }

    /**
     * Set the cursorAdapter to work with my custom layout
     */

    public void setCustomCursorAdapter(){
        cursorAdapter = new CursorAdapter(this, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.custom_layout, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView nameTextView = (TextView) view.findViewById(R.id.place_title_name);
                TextView addressTextView = (TextView) view.findViewById(R.id.address);
                TextView ratingTextView = (TextView) view.findViewById(R.id.rating);
                ImageView resultListImage = (ImageView) view.findViewById(R.id.list_event_image);
                TextView priceTextView = (TextView)view.findViewById(R.id.price_customView);

                nameTextView.setText(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_PLACE_NAME)));
                addressTextView.setText(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_ADDRESS)));
                ratingTextView.setText(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_RATINGS)));
                priceTextView.setText(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_PRICE)));
                resultListImage.setImageResource(setPlaceImageLogo(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_PLACE_NAME))));
            }
        };
    }

    /**
     * Setting all the views
     */
    public void setView(){
        resultTitleName = (TextView)findViewById(R.id.resultTitleView);
        lakeMerrittListView = (ListView) findViewById(R.id.resultListView);
        lakeMerrittHelper = LakeMerrittSQLiteOpenHelper.getInstance(this);
        typeFilterSpinner = (Spinner) findViewById(R.id.type_static_spinner);
        priceFilterSpinner = (Spinner) findViewById(R.id.price_static_spinner);
        ratingFilterSpinner = (Spinner) findViewById(R.id.rating_static_spinner);
        titleImageLogo = (ImageView)findViewById(R.id.resultImageIcon);
        intent = new Intent(ResultListActivity.this, DescriptionActivity.class);
    }

    /**
     * this is for singleton and search function
     * @param intent
     */

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
            disableSpinners();
            typeFilterSpinner.setEnabled(false);
            resultTitleName.setText(R.string.searchResult);

        }
    }


    /**
     * this is for the search function. search by place name, types and ratings
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }


    /**
     * Set Title Names and logos
     * set Spinner to off for the one that doesn't need it
     */
    public void setTitleAndLogosAndSpinner(){
        String titleExtra = getIntent().getStringExtra("TitleName");
        resultTitleName.setText(titleExtra);

        if(resultTitleName.getText().equals(MainActivity.RESTAURANTS)){
            titleImageLogo.setImageResource(R.drawable.foodicons1);
        }
        if(resultTitleName.getText().equals(MainActivity.ACTIVITIES)){
            titleImageLogo.setImageResource(R.drawable.whats_happening_logo);
            disableSpinners();
        }
        if (resultTitleName.getText().equals(MainActivity.FAVORITES)){
            titleImageLogo.setImageResource(R.drawable.hearticon);
            disableSpinners();
            typeFilterSpinner.setEnabled(false);
        }
        if (resultTitleName.getText().equals(MainActivity.VIEW_ALL)){
            titleImageLogo.setImageResource(R.drawable.whats_happening_logo);
            disableSpinners();
        }
    }

    /**
     * this make the spinner that is not in use off
     */
    public void disableSpinners(){
        priceFilterSpinner.setEnabled(false);
        ratingFilterSpinner.setEnabled(false);
    }


    /**
     * TO SET IMAGES ACCORDING TO THE NAME FOR THE LIST RESULTS
     * @return
     */
    private int setPlaceImageLogo(String logoImage){
        switch(logoImage){
            case PORTAL:
                return R.drawable.portalicon;
            case JONG_GA:
                return R.drawable.jonggalogo;
            case GRAND_LAKE:
                return R.drawable.grandlakekitchenlogo;
            case THE_ROCKIN_CRAWFISH:
                return R.drawable.rockincrawfishicon;
            case HADDON_HILL:
                return R.drawable.haddonhillcafelogo;
            case ARIZMENDI_BAKERY:
                return R.drawable.arizmendibakerylogo;
            case MICHEL_BISTRO:
                return R.drawable.michelbistrologo;
            case THE_ALLEY:
                return R.drawable.thealleylogo;
            case OFF_THE_GRID:
                return R.drawable.offthegridlogo;
            case THE_GARDENS:
                return R.drawable.thegardenslogo2;
            case JAPANESE_GARDEN:
                return R.drawable.japanesegarden1;
            case PALM_GARDEN:
                return R.drawable.palmgarden;
            case THE_MEDITERRANEAN:
                return R.drawable.mediterraneangarden;
            case WATER_SPORTS:
                return R.drawable.watersailboat;
            case FAIRYLAND:
                return R.drawable.fairylandlogo;
            case EXERCISE:
                return R.drawable.exercising2;
            case FLEA_MARKET:
                return R.drawable.fleamarketlogo;
            default:
                return 0;
        }
    }

    public void setCursor(){

        switch(resultTitleName.getText().toString()) {
            case MainActivity.RESTAURANTS:
                cursor = LakeMerrittSQLiteOpenHelper.getInstance(this).getRestaurantList();
                break;
            case MainActivity.ACTIVITIES:
                cursor = LakeMerrittSQLiteOpenHelper.getInstance(this).getActivitiesList();
                break;
            case MainActivity.VIEW_ALL:
                cursor = LakeMerrittSQLiteOpenHelper.getInstance(this).getLakeMerrittLists();
                break;
            case MainActivity.FAVORITES:
                cursor = LakeMerrittSQLiteOpenHelper.getInstance(this).getFavoriteLists();
                break;

        }

    }


}
