package com.example.billy.bill_neighborhood_guide;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class list_results extends AppCompatActivity {

    LakeMerrittSQLiteOpenHelper merrittHelper;

    ArrayList<Restaurants> restaurantLists;
    ArrayList<Activity> activityLists;

    public final static String RESULT_TITLE = "result_title";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_results);


        merrittHelper = LakeMerrittSQLiteOpenHelper.getInstance(this);

        merrittHelper.listInsert(1, "Restaurants", "Portal", "1611 2nd Ave", "510-663-7678", "4 stars", "$$", "Breakfast");



        Cursor cursor = LakeMerrittSQLiteOpenHelper.getInstance(list_results.this).getLakeMerrittLists();

        CursorAdapter cursorAdapter = new CursorAdapter(list_results.this, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.new_layout_view, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView nameTextView = (TextView) view.findViewById(R.id.title_name);
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

        ListView listView = (ListView) findViewById(R.id.list_result_view);
        listView.setAdapter(cursorAdapter);


    }




    /**
     * need to setup onItemClick to display information of place
     *
     */

   /* public void setOnItemClick(){
        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent resultListIntent = new Intent(list_results.this, description_layout.class);

                    // to grab from restaurantLists on item click and name
                //String descriptionTitleExtra = restaurantLists.get(position).getRestaurantName();

                // this is if we don't know where the name is from
                String descriptionTitleExtra = ((TextView)view.findViewById(R.id.title_name)).getText().toString();

                resultListIntent.putExtra("result_title", descriptionTitleExtra);
                startActivity(resultListIntent);


            }
        });

    }

    public void setListTitle(){
        String titleExtra = getIntent().getStringExtra("TitleName");
        titleName.setText(titleExtra);

    }*/

}
