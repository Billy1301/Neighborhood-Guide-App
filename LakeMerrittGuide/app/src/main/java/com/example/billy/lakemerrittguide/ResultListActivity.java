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

    private TextView resultTitleName;
    private ListView lakeMerrittListView;
    private CursorAdapter cursorAdapter;
    private Cursor cursor;
    LakeMerrittSQLiteOpenHelper lakeMerrittHelper;
    private ArrayAdapter<String> typeFilterAdapter, priceFilterAdapter, ratingFilterAdapter;
    private Spinner typeFilterSpinner, priceFilterSpinner, ratingFilterSpinner;
    private List<String> typeFilters, priceFilters, ratingFilters;
    private ImageView titleImageLogo;
    Intent intent;

    public static final String ID_KEY_SENDING = "_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_result);

        setView();

        setTitleAndLogosAndSpinner();
        setCursor();
        setCursorAdapter();
        handleIntent(getIntent());
        lakeMerrittListView.setAdapter(cursorAdapter);
        setItemClicker();


        /**
         * these are for the filter spinners
         */
        typeFilters = new ArrayList<String>();
        priceFilters = new ArrayList<String>();
        ratingFilters = new ArrayList<String>();
        setSpinnerName();
        setSpinner();
        setFilterClicker();



    }


    /**
     * This setup the Spinner Item Select to filter listing to the pre-set choices
     *
     */

    public void setFilterClicker(){
            typeFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    if (position == 0) {
                        if (resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getRestaurantList();
                        } else if (resultTitleName.getText().equals(MainActivity.ACTIVITIES)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getActivitiesList();
                        } else if (resultTitleName.getText().equals(MainActivity.VIEW_ALL)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getLakeMerrittLists();
                        } else if (resultTitleName.getText().equals(MainActivity.FAVORITES)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getFavoriteLists();
                        }
                        changeCursorRefreshAdapter();

                    }

                    if (position == 1) {

                        if (resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getKoreanList();

                        } else if (resultTitleName.getText().equals(MainActivity.ACTIVITIES)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getSportsList();
                        } else if (resultTitleName.getText().equals(MainActivity.VIEW_ALL)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getActivitiesList();
                        } else if (resultTitleName.getText().equals(MainActivity.FAVORITES)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getFavoriteLists();
                        }
                        changeCursorRefreshAdapter();

                    }

                    if (position == 2) {

                        if (resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getBreakfastList();

                        } else if (resultTitleName.getText().equals(MainActivity.ACTIVITIES)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getParksList();

                        } else if (resultTitleName.getText().equals(MainActivity.VIEW_ALL)) {
                        cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getRestaurantList();
                        }
                        changeCursorRefreshAdapter();

                    }

                    if (position == 3) {

                        if (resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getSteakhouseList();

                        } else if (resultTitleName.getText().equals(MainActivity.ACTIVITIES)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getShopList();
                        } else if (resultTitleName.getText().equals(MainActivity.VIEW_ALL)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getLakeMerrittLists();
                        } else if (resultTitleName.getText().equals(MainActivity.FAVORITES)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getFavoriteLists();
                        }
                        changeCursorRefreshAdapter();

                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            priceFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        if (resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getRestaurantList();
                        }
                        changeCursorRefreshAdapter();

                    }

                    if (position == 1) {

                        if (resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).get$PriceList();
                        }
                        changeCursorRefreshAdapter();

                    }

                    if (position == 2) {

                        if (resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).get$$PriceList();

                        }
                        changeCursorRefreshAdapter();

                    }

                    if (position == 3) {

                        if (resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).get$$$PriceList();
                        }
                        changeCursorRefreshAdapter();

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ratingFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        if (resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getRestaurantList();
                        }
                        changeCursorRefreshAdapter();

                    }

                    if (position == 1) {

                        if (resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getThreeStarsList();
                        }
                        changeCursorRefreshAdapter();

                    }

                    if (position == 2) {

                        if (resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getFourStarsList();

                        }
                        changeCursorRefreshAdapter();

                    }

                    if (position == 3) {

                        if (resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getFiveStarsList();
                        }
                        changeCursorRefreshAdapter();
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
    }


    public void changeCursorRefreshAdapter(){
        cursorAdapter.changeCursor(cursor);
        cursorAdapter.notifyDataSetChanged();
    }


    /**
     * this setup the dialog names for the Spinners
     */
    public void setSpinnerName() {

        if (resultTitleName.getText().equals(MainActivity.RESTAURANTS)) {
            typeFilters.add("Show All");
            typeFilters.add("Korean");
            typeFilters.add("Brunch");
            typeFilters.add("Steakhouse");

            priceFilters.add("Show All Price");
            priceFilters.add("$");
            priceFilters.add("$$");
            priceFilters.add("$$$");

            ratingFilters.add("Show All Ratings");
            ratingFilters.add("3 stars");
            ratingFilters.add("4 stars");
            ratingFilters.add("5 stars");


        } else if (resultTitleName.getText().equals(MainActivity.ACTIVITIES)) {
            typeFilters.add("Show All");
            typeFilters.add("Sports");
            typeFilters.add("Parks");
            typeFilters.add("Shops");

            priceFilters.add("Show All");
            ratingFilters.add("Show All");


        } else if (resultTitleName.getText().equals(MainActivity.VIEW_ALL)) {
            typeFilters.add("Show All");
            typeFilters.add(MainActivity.ACTIVITIES);
            typeFilters.add(MainActivity.RESTAURANTS);

            priceFilters.add("Show All");
            ratingFilters.add("Show All");


        }
    }


    /**
     * this connects the spinner to the ArrayList
     */

    public void setSpinner(){
        typeFilterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeFilters);
        priceFilterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, priceFilters);
        ratingFilterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ratingFilters);

        typeFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratingFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeFilterSpinner.setAdapter(typeFilterAdapter);
        priceFilterSpinner.setAdapter(priceFilterAdapter);
        ratingFilterSpinner.setAdapter(ratingFilterAdapter);

    }



    /**
     *clicking on any item will send you to the detail activity
     * setup a delay to start to show the each click will lights up
     */

    public void setItemClicker(){
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
                }, 800);
            }
        });

    }

    /**
     * Set the cursorAdapter to work with my custom layout
     */

    public void setCursorAdapter(){
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

                // this set image according to Names
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
     * this is for singleton
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
            typeFilterSpinner.setEnabled(false);
            titleImageLogo.setImageResource(R.drawable.lakemerrittapplogo);

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
     * Set Title Names and Spinner to off for the one that doesn't need it
     */
    public void setTitleAndLogosAndSpinner(){
        String titleExtra = getIntent().getStringExtra("TitleName");
        resultTitleName.setText(titleExtra);

        if(resultTitleName.getText().equals(MainActivity.RESTAURANTS)){
            titleImageLogo.setImageResource(R.drawable.foodicons1);
        }
        if(resultTitleName.getText().equals(MainActivity.ACTIVITIES)){
            titleImageLogo.setImageResource(R.drawable.whats_happening_logo);
            priceFilterSpinner.setEnabled(false);
            ratingFilterSpinner.setEnabled(false);
        }
        if (resultTitleName.getText().equals(MainActivity.FAVORITES)){
            typeFilterSpinner.setEnabled(false);
            priceFilterSpinner.setEnabled(false);
            ratingFilterSpinner.setEnabled(false);
            titleImageLogo.setImageResource(R.drawable.hearticon);
        }
        if (resultTitleName.getText().equals(MainActivity.VIEW_ALL)){
            titleImageLogo.setImageResource(R.drawable.whats_happening_logo);
            priceFilterSpinner.setEnabled(false);
            ratingFilterSpinner.setEnabled(false);
        }


    }

    /**
     * TO SET IMAGES ACCORDING TO THE NAME FOR THE LIST RESULTS
     * @param logoImage
     * @return
     */

    private int setPlaceImageLogo(String logoImage){
        switch(logoImage){
            case MainActivity.PORTAL:
                return R.drawable.portalicon;
            case MainActivity.JONG_GA:
                return R.drawable.jonggalogo;
            case MainActivity.GRAND_LAKE:
                return R.drawable.grandlakekitchenlogo;
            case MainActivity.THE_ROCKIN_CRAWFISH:
                return R.drawable.rockincrawfishicon;
            case MainActivity.HADDON_HILL:
                return R.drawable.haddonhillcafelogo;
            case MainActivity.ARIZMENDI_BAKERY:
                return R.drawable.arizmendibakerylogo;
            case MainActivity.MICHEL_BISTRO:
                return R.drawable.michelbistrologo;
            case MainActivity.THE_ALLEY:
                return R.drawable.thealleylogo;
            case MainActivity.OFF_THE_GRID:
                return R.drawable.offthegridlogo;
            case MainActivity.THE_GARDENS:
                return R.drawable.thegardenslogo2;
            case MainActivity.JAPANESE_GARDEN:
                return R.drawable.japanesegarden1;
            case MainActivity.PALM_GARDEN:
                return R.drawable.palmgarden;
            case MainActivity.THE_MEDITERRANEAN:
                return R.drawable.mediterraneangarden;
            case MainActivity.WATER_SPORTS:
                return R.drawable.watersailboat;
            case MainActivity.FAIRYLAND:
                return R.drawable.fairylandlogo;
            case MainActivity.EXERCISE:
                return R.drawable.exercising2;
            case MainActivity.FLEA_MARKET:
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
