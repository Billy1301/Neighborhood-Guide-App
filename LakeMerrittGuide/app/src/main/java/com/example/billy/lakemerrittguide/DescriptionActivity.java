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
    ThingsToDo clickedItem;
    //ArrayList<Activity> activitiesList;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView locationTextView;
    private TextView typeTextView;
    private ImageView logoImage;
    private ImageView infoImageOne;
    private ImageView infoImageTwo;
    int selectedId;
    //private ImageView infoImageThree;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setView();
        helper = LakeMerrittSQLiteOpenHelper.getInstance(DescriptionActivity.this);
        getAndSetIntentToView();

        setFavoriteButton();

        }

    public void getAndSetIntentToView() {

        selectedId = getIntent().getIntExtra(ResultListActivity.ID_KEY_SENDING, -1);

        if (selectedId >= 0) {

            clickedItem = helper.createObjects(selectedId);

            nameTextView.setText(clickedItem.getPlaceName());
            descriptionTextView.setText(clickedItem.getPlaceInfo());
            locationTextView.setText(clickedItem.getPlaceAddress());
            typeTextView.setText(clickedItem.getPlaceType());

            logoImage.setImageResource(clickedItem.getMainImageLogo());
            infoImageOne.setImageResource(clickedItem.getInfoImageOne());
            infoImageTwo.setImageResource(clickedItem.getInfoImageTwo());




      /*  if(selectedId >= 0) {
            Cursor selectedItemCursor = LakeMerrittSQLiteOpenHelper.getInstance(DescriptionActivity.this).getItem(selectedId);
            selectedItemCursor.moveToFirst();


            nameTextView.setText(selectedItemCursor.getString(selectedItemCursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_PLACE_NAME)));
            descriptionTextView.setText(selectedItemCursor.getString(selectedItemCursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_DESCRIPTION)));
            locationTextView.setText(selectedItemCursor.getString(selectedItemCursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_ADDRESS)));
            typeTextView.setText(selectedItemCursor.getString(selectedItemCursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_TYPE)));



            logoImage.setImageResource(setImageLogo(selectedItemCursor.getString(selectedItemCursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_PLACE_NAME))));
            infoImageOne.setImageResource(setMainImageOne(selectedItemCursor.getString(selectedItemCursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_PLACE_NAME))));
            infoImageTwo.setImageResource(setImageTwo(selectedItemCursor.getString(selectedItemCursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_PLACE_NAME))));

            //infoImageThree.setImageResource(setImageThree(selectedItemCursor.getString(selectedItemCursor.getColumnIndex(LakeMerrittSQLiteOpenHelper.COL_LAKE_MERRITT_PLACE_NAME))));

            selectedItemCursor.close();*/
        }

    }

    public void setFavoriteButton(){
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
        infoImageOne = (ImageView)findViewById(R.id.frameTwoImageOne);
        infoImageTwo = (ImageView)findViewById(R.id.frameTwoImageTwo);
//        infoImageThree = (ImageView)findViewById(R.id.frameTwoImageThree);

    }


    private int setImageLogo(String imageLogo){
        switch(imageLogo){
            case "Portal":
                return R.drawable.portalicon;
            case "Jong Ga House":
                return android.R.drawable.ic_menu_add;
            case "Grand Lake Kitchen":
                return android.R.drawable.ic_menu_upload;
            case "The Rockin Crawfish":
                return android.R.drawable.ic_media_play;
            case "Haddon Hill Cafe":
                return R.drawable.haddonhillcafelogo;
            case "The Gardens":
                return R.drawable.android_arms;
            default:
                return 0;
        }
    }


    private int setMainImageOne(String imageOne){
        switch(imageOne){
            case "Portal":
                return R.drawable.portalbrunch1;
            case "Jong Ga House":
                return android.R.drawable.ic_menu_add;
            case "Grand Lake Kitchen":
                return android.R.drawable.ic_menu_upload;
            case "The Rockin Crawfish":
                return android.R.drawable.ic_media_play;
            case "Haddon Hill Cafe":
                return R.drawable.haddonhilldrinks1;
            case "The Gardens":
                return R.drawable.android_arms;
            default:
                return 0;
        }
    }

    private int setImageTwo(String imageTwo){
        switch(imageTwo){
            case "Portal":
                return R.drawable.portalbrunch1;
            case "Jong Ga House":
                return android.R.drawable.ic_menu_add;
            case "Grand Lake Kitchen":
                return android.R.drawable.ic_menu_upload;
            case "The Rockin Crawfish":
                return android.R.drawable.ic_media_play;
            case "Haddon Hill Cafe":
                return R.drawable.haddondrink2;
            case "The Gardens":
                return R.drawable.android_arms;
            default:
                return 0;
        }
    }

    private int setImageThree(String imageThree){
        switch(imageThree){
            case "Portal":
                return R.drawable.portalbrunch1;
            case "Jong Ga House":
                return android.R.drawable.ic_menu_add;
            case "Grand Lake Kitchen":
                return android.R.drawable.ic_menu_upload;
            case "The Rockin Crawfish":
                return android.R.drawable.ic_media_play;
            case "Haddon Hill Cafe":
                return R.drawable.haddondrink3;
            case "The Gardens":
                return R.drawable.android_arms;
            default:
                return 0;
        }
    }
}
