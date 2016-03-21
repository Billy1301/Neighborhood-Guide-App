package com.example.billy.lakemerrittguide;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DescriptionActivity extends AppCompatActivity {

    LakeMerrittSQLiteOpenHelper helper;
    ArrayList<Restaurants> restaurantsList;
    ArrayList<Activity> activitiesList;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView locationTextView;
    private TextView typeTextView;
    private ImageView logoImage;
    private ImageView imageOne;
    private ImageView imageTwo;
    private ImageView imageThree;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setView();


        helper = LakeMerrittSQLiteOpenHelper.getInstance(DescriptionActivity.this);


        restaurantsList = new ArrayList<>();

        int selectedId = getIntent().getIntExtra(ResultListActivity.ID_KEY_SENDING,-1);


        if(selectedId >= 0) {
            Cursor selectedItemCursor = LakeMerrittSQLiteOpenHelper.getInstance(DescriptionActivity.this).getItem(selectedId);
            selectedItemCursor.moveToFirst();


            nameTextView.setText(selectedItemCursor.getString(selectedItemCursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_PLACE_NAME)));
            descriptionTextView.setText(selectedItemCursor.getString(selectedItemCursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_DESCRIPTION)));
            locationTextView.setText(selectedItemCursor.getString(selectedItemCursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_ADDRESS)));
            typeTextView.setText(selectedItemCursor.getString(selectedItemCursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_TYPE)));
            logoImage.setImageResource(getDrawableValue(selectedItemCursor.getString(selectedItemCursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_PLACE_NAME))));
            imageOne.setImageResource(getMoreImage(selectedItemCursor.getString(selectedItemCursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_PLACE_NAME))));

            selectedItemCursor.close();


            if (nameTextView.getText().equals("Portal")) {

                //imageOne.setImageResource(R.drawable.portalbrunch1);
                imageTwo.setImageResource(R.drawable.star);
            }


        }




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Snackbar.make(view, "set favorite function here", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void setView(){
        nameTextView = (TextView)findViewById(R.id.descriptionPlaceTextView);
        descriptionTextView = (TextView)findViewById(R.id.descriptionInfoDetailText);
        locationTextView = (TextView)findViewById(R.id.descriptionLocationText);
        typeTextView = (TextView)findViewById(R.id.descriptionTypeTextView);
        logoImage = (ImageView)findViewById(R.id.descriptionImageLogo);
        imageOne = (ImageView)findViewById(R.id.frameTwoImageOne);
        imageTwo = (ImageView)findViewById(R.id.frameTwoImageTwo);
        imageThree = (ImageView)findViewById(R.id.frameTwoImageThree);

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


    private int getMoreImage(String image){
        switch(image){
            case "Portal":
                return R.drawable.portalbrunch1;
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
}
