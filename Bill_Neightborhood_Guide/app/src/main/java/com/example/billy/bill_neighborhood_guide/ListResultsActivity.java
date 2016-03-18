package com.example.billy.bill_neighborhood_guide;

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
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class ListResultsActivity extends AppCompatActivity {

    TextView listTitleName;

    LakeMerrittSQLiteOpenHelper merrittHelper;

    ArrayList<Restaurants> restaurantLists;
    ArrayList<Activity> activityLists;
    ListView merrittListView;
    public final static String RESULT_TITLE = "result_title";

    CursorAdapter cursorAdapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_results);

        listTitleName = (TextView)findViewById(R.id.result_title_name);

        setListTitle();
/*

        merrittHelper = LakeMerrittSQLiteOpenHelper.getInstance(this);

        merrittHelper.listInsert(1, "Restaurants", "Portal", "1611 2nd Ave", "510-663-7678", "4 stars", "$$", "Breakfast");
        merrittHelper.listInsert(2, "Restaurants", "Jong Ga House", "372 Grand Ave", "510-444-7678", "5 stars", "$$", "Korean");
        merrittHelper.listInsert(3, "Restaurants", "Grand Lake Kitchen", "576 Grand Ave", "510-922-9582", "3 stars", "$$", "American");
        merrittHelper.listInsert(4, "Restaurants", "The Rockin Crawfish", "211 Foothill Blvd", "510-251-1657", "4 stars", "$$", "Cajun Seafood");
        merrittHelper.listInsert(5, "Restaurants", "Haddon Hill Cafe", "504 Wesly Ave", null, "5 stars", "$", "Cafe");
        merrittHelper.listInsert(6, "Restaurants", "Arizmendi Bakery", "3265 Lakeshore Ave", "510-268-8849", "5 stars", "$$", "Bakeries");
        merrittHelper.listInsert(7, "Restaurants", "Michel Bistro", "3343 Lakeshore Ave", "510-836-8737", "4 stars", "$$", "French");
        merrittHelper.listInsert(8, "Restaurants", "The Alley", "3325 Grand Ave", "510-444-8505", "4 stars", "$$", "Steakhouse");
        merrittHelper.listInsert(9, "Restaurants", "Off The Grid Food Truck", "1000 Oak St", null, "4 stars", "$$", "Breakfast");
        merrittHelper.listInsert(10, "Activities", "The Gardens", "666 Bellevue Ave", null, null, null, "Parks");
        merrittHelper.listInsert(11, "Activities", "Japanese Garden", "666 Bellevue Ave", null, null, null, "Parks");
        merrittHelper.listInsert(12, "Activities", "Palm Garden", "666 Bellevue Ave", null, null, null, "Parks");
        merrittHelper.listInsert(13, "Activities", "The Mediterranean Garden", "666 Bellevue Ave", null, null, null, "Parks");
        merrittHelper.listInsert(14, "Activities", "Water sports", "666 Bellevue Ave", null, null, null, "Sports");
        merrittHelper.listInsert(15, "Activities", "FairyLand", "699 Bellevue Ave", null, null, null, "Theme Park");
        merrittHelper.listInsert(16, "Activities", "Exercise", "Lake Merritt", null, null, null, "Sports");
        merrittHelper.listInsert(17, "Activities", "Flea Market", "Grand Ave & Lake Park Ave", null, null, null, "Shops");
*/



        if (listTitleName.equals("Restaurants")){
            // need to setup cursor to pull the right info
            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ListResultsActivity.this).getRestaurantList();
            cursor.moveToFirst();

            //Log.i("List", " " + cursor.getString(cursor.getColumnIndex("Address")));
        }
        else {
            cursor = LakeMerrittSQLiteOpenHelper.getInstance(ListResultsActivity.this).getActivitiesList();
            cursor.moveToFirst();

        }


        //cursor = LakeMerrittSQLiteOpenHelper.getInstance(ListResultsActivity.this).getLakeMerrittLists();

        cursorAdapter = new CursorAdapter(ListResultsActivity.this, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.new_layout_view, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView nameTextView = (TextView) view.findViewById(R.id.place_title_name);
                TextView addressTextView = (TextView) view.findViewById(R.id.address);
                TextView ratingTextView = (TextView) view.findViewById(R.id.rating);
                ImageView resultListImage = (ImageView) view.findViewById(R.id.event_image);

                nameTextView.setText(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.LAKE_MERRITT_PLACE_NAME)));
                addressTextView.setText(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.LAKE_MERRITT_ADDRESS)));
                ratingTextView.setText(cursor.getString(cursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.LAKE_MERRITT_RATINGS)));


                if (nameTextView.equals("Portal")) {
                    resultListImage.setImageResource(R.drawable.portalicon);
                }

            }
        };


        merrittListView = (ListView) findViewById(R.id.list_result_view);
        merrittListView.setAdapter(cursorAdapter);

        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_main, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            cursor = merrittHelper.searchLakeMerrittList(query);
            cursorAdapter.changeCursor(cursor);
            cursorAdapter.notifyDataSetChanged();
        }
    }

    public void setListTitle(){
        String titleExtra = getIntent().getStringExtra("TitleName");
        listTitleName.setText(titleExtra);

    }

    /**
     * need to setup onItemClick to display information of place
     *
     */

   /* public void setOnItemClick(){
        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent resultListIntent = new Intent(ListResultsActivity.this, Description_layout.class);

                    // to grab from restaurantLists on item click and name
                //String descriptionTitleExtra = restaurantLists.get(position).getRestaurantName();

                // this is if we don't know where the name is from
                String descriptionTitleExtra = ((TextView)view.findViewById(R.id.title_name)).getText().toString();

                resultListIntent.putExtra("result_title", descriptionTitleExtra);
                startActivity(resultListIntent);


            }
        });

    }

   */

}
