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

    TextView resultTitleName;
    ListView lakeMerrittListView;
    CursorAdapter cursorAdapter;
    Cursor cursor;
    LakeMerrittSQLiteOpenHelper lakeMerrittHelper;
    ArrayAdapter<CharSequence> filterSpinnerAdapter;
    ArrayAdapter<String> filterDynamicAdapter;
    Spinner filterSpinner;
    List<String> filters;
    //SpinnerItemClicker listener;


    public static final String ID_KEY_SENDING = "_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_result);

        setView();

        setListTitle();
        setCursor();
        setCursorAdapter();
                //Log.d("Result", " before adapter");
        lakeMerrittListView.setAdapter(cursorAdapter);
        handleIntent(getIntent());
        setItemClicker();


        /**
         * these are for spinner
         */
        filters = new ArrayList<String>();
        setSpinnerName();



        setSpinner();


        //listener = new SpinnerItemClicker();

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

                        } else {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).getActivitiesList();
                        }

                        cursorAdapter.changeCursor(cursor);
                        cursorAdapter.notifyDataSetChanged();

                    }
                    if (position == 1) {
                        Log.d("Result", "itemclicked " + position);

                        if(resultTitleName.getText().equals("Restaurants")) {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).testingGetKoreanList();
                        }else {
                            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ResultListActivity.this).testingGetSportsList();
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
                resultListImage.setImageResource(getDrawableValue(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_PLACE_NAME))));


            }
        };

    }

    public void setView(){
        resultTitleName = (TextView)findViewById(R.id.resultTitleView);
        lakeMerrittListView = (ListView) findViewById(R.id.resultListView);
        lakeMerrittHelper = LakeMerrittSQLiteOpenHelper.getInstance(this);
        filterSpinner = (Spinner) findViewById(R.id.static_spinner);


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

    public void setListTitle(){
        String titleExtra = getIntent().getStringExtra("TitleName");
        resultTitleName.setText(titleExtra);

    }

    private int getDrawableValue(String icon){
        switch(icon){
            case "Portal":
                return R.drawable.portalicon;
            case "Jong Ga House":
                return android.R.drawable.ic_menu_add;
            case "Grand Lake Kitchen":
                return android.R.drawable.ic_menu_upload;
            case "The Rockin Crawfish":
                return android.R.drawable.ic_media_play;
            case "The Gardens":
                return R.drawable.android_arms;
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
                //cursor = LakeMerrittSQLiteOpenHelper.getInstance(this).
                break;
            // this is to get whole list
            // cursor = LakeMerrittSQLiteOpenHelper.getInstance(this).getLakeMerrittLists();


        }

    }


}
