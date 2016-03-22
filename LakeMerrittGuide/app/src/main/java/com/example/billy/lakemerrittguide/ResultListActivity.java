package com.example.billy.lakemerrittguide;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
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

public class ResultListActivity extends AppCompatActivity  {

    private TextView resultTitleName;
    private ListView lakeMerrittListView;
    private CursorAdapter cursorAdapter;
    private Cursor cursor;
    LakeMerrittSQLiteOpenHelper lakeMerrittHelper;
    ArrayAdapter<CharSequence> filterSpinnerAdapter;
    private ArrayAdapter<String> filterDynamicAdapter;
    private Spinner filterSpinner;
    private List<String> filters;
    private ImageView titleImageLogo;

    public static final String ID_KEY_SENDING = "_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_result);

        setView();

        setTitleAndLogos();
        setCursor();
        setCursorAdapter();
        handleIntent(getIntent());
        lakeMerrittListView.setAdapter(cursorAdapter);
        setItemClicker();


        /**
         * these are for filter spinner
         */
        filters = new ArrayList<String>();
        setSpinnerName();
        setSpinner();
        setFilterClicker();

    }


    /**
     * filter by types
     */

    public void setFilterClicker(){
            filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    Log.d("Result", "itemclicked ");

                    if (position == 0) {
                        Log.d("Result", "itemclicked " + position);
                        if(resultTitleName.getText().equals("Restaurants")){
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getRestaurantList();

                        }
                        else if (resultTitleName.getText().equals("Activities")) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getActivitiesList();
                        }
                        else if (resultTitleName.getText().equals("Favorites")) {
                            //make this to favorite list
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getLakeMerrittLists();

                        }

                        cursorAdapter.changeCursor(cursor);
                        cursorAdapter.notifyDataSetChanged();

                    }
                    if (position == 1) {
                        Log.d("Result", "itemclicked " + position);

                        if(resultTitleName.getText().equals("Restaurants")) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).testingGetKoreanList();
                        } else if(resultTitleName.getText().equals("Activities")) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).testingGetSportsList();
                        } else if (resultTitleName.getText().equals("Favorites")) {

                            /**
                             * set this to favorites only
                             */
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getLakeMerrittLists();

                        }

                        cursorAdapter.changeCursor(cursor);
                        cursorAdapter.notifyDataSetChanged();

                    }

                    if (position == 2){
                        Log.d("Result", "itemclicked " + position);

                        if(resultTitleName.getText().equals("Restaurants")) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).testingGetKoreanList();
                        }else {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getParksList();
                        }

                        cursorAdapter.changeCursor(cursor);
                        cursorAdapter.notifyDataSetChanged();

                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


    }

    /**
     * this setup the dialog text for the spinner
     */
    public void setSpinnerName(){

        if (resultTitleName.getText().equals("Restaurants")) {
            filters.add("Show All Type");
            filters.add("Korean");
            filters.add("Breakfast");
            filters.add("Drinks");

        } else {
            filters.add("Show All Places");
            filters.add("Sports");
            filters.add("Parks");
            filters.add("Exercise");
        }

    }


    /**
     * this connects the spinner to the ArrayList
     */

    public void setSpinner(){
        filterDynamicAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filters);
        filterDynamicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(filterDynamicAdapter);


       /* filterSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.filter, android.R.layout.simple_spinner_item);
        filterSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(filterSpinnerAdapter);*/

    }



    /**
     * use  spinner for filter option.. a nice way to sort stuff...
     */

    public void setItemClicker(){
        lakeMerrittListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ResultListActivity.this, DescriptionActivity.class);
                Cursor selectedItem = (Cursor) parent.getItemAtPosition(position);
                int databaseID = selectedItem.getInt(selectedItem.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_ID));
                intent.putExtra(ID_KEY_SENDING, databaseID);
                startActivity(intent);
            }
        });

    }

    /**
     * this set the cursorAdapter to work with my custom layout
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

                nameTextView.setText(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_PLACE_NAME)));
                addressTextView.setText(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_ADDRESS)));
                ratingTextView.setText(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_RATINGS)));

                // this set image according to Names
                resultListImage.setImageResource(setPlaceImageLogo(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_PLACE_NAME))));


            }
        };

    }


    /**
     * setting all the views
     */

    public void setView(){
        resultTitleName = (TextView)findViewById(R.id.resultTitleView);
        lakeMerrittListView = (ListView) findViewById(R.id.resultListView);
        lakeMerrittHelper = LakeMerrittSQLiteOpenHelper.getInstance(this);
        filterSpinner = (Spinner) findViewById(R.id.static_spinner);
        titleImageLogo = (ImageView)findViewById(R.id.resultImageIcon);


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
            filterSpinner.setEnabled(false);
            titleImageLogo.setImageResource(R.drawable.lakemerrittapplogo);

        }
    }


    /**
     * this is for the search function.. **currently not working correctly.. need to fix
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

    public void setTitleAndLogos(){
        String titleExtra = getIntent().getStringExtra("TitleName");
        resultTitleName.setText(titleExtra);

        if(resultTitleName.getText().equals("Restaurants")){
            titleImageLogo.setImageResource(R.drawable.restaurantbacktitlelogo);
        }
        if(resultTitleName.getText().equals("Activities")){
            titleImageLogo.setImageResource(R.drawable.whats_happening_logo);
        }
        if (resultTitleName.getText().equals("Favorites")){
            filterSpinner.setEnabled(false);
            titleImageLogo.setImageResource(R.drawable.hearticon);
        }



    }

    private int setPlaceImageLogo(String logoImage){
        switch(logoImage){
            case "Portal":
                return R.drawable.portalicon;
            case "Jong Ga House":
                return R.drawable.jonggalogo;
            case "Grand Lake Kitchen":
                return R.drawable.restaurantbacktitlelogo;
            case "Japanese Garden":
                return R.drawable.japanesegarden1;
            case "Haddon Hill Cafe":
                return R.drawable.haddonhillcafelogo;
            case "The Gardens":
                return R.drawable.thegardenslogo2;
            case "Water Sports":
                return R.drawable.watersailboat;

            default:
                return 0;
        }
    }

    public void setCursor(){

        switch(resultTitleName.getText().toString()) {
            case "Restaurants":
                cursor = LakeMerrittSQLiteOpenHelper.getInstance(this).getRestaurantList();
                break;
            case "Activities":
                cursor = LakeMerrittSQLiteOpenHelper.getInstance(this).getActivitiesList();
                break;
            case "Favorites":
                cursor = LakeMerrittSQLiteOpenHelper.getInstance(this).getLakeMerrittLists();
                break;

            // this is to get whole list
            // cursor = LakeMerrittSQLiteOpenHelper.getInstance(this).getLakeMerrittLists();


        }

    }


}
