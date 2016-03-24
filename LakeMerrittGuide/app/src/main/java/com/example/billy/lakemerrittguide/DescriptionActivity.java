package com.example.billy.lakemerrittguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    LakeMerrittSQLiteOpenHelper helper;
    private ThingsToDoClass thingsToDoClassClassClickedItem;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView locationTextView;
    private TextView typeTextView;
    private ImageView logoImage;
    private ImageView infoImageOne;
    private int selectedId;
    private Button favoriteButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setView();
        getAndSetIntentToView();
        setFavoriteButtonImage();
        setFavoriteButton();


        }



    public void getAndSetIntentToView() {

        selectedId = getIntent().getIntExtra(ResultListActivity.ID_KEY_SENDING, -1);

        if (selectedId >= 0) {

            thingsToDoClassClassClickedItem = helper.createObjects(selectedId);
            nameTextView.setText(thingsToDoClassClassClickedItem.getPlaceName());
            descriptionTextView.setText(thingsToDoClassClassClickedItem.getPlaceInfo());
            locationTextView.setText(thingsToDoClassClassClickedItem.getPlaceAddress());
            typeTextView.setText(thingsToDoClassClassClickedItem.getPlaceType());
            logoImage.setImageResource(thingsToDoClassClassClickedItem.getInfoMainImageLogo());
            infoImageOne.setImageResource(thingsToDoClassClassClickedItem.getInfoImageOne());

        }

    }

    private void setFavoriteButtonImage(){
        if(thingsToDoClassClassClickedItem.getFavoriteStatus().equals(MainActivity.REMOVE_FAVORITE)){
            favoriteButton.setBackgroundResource(R.drawable.emptyfav);
        } else if (thingsToDoClassClassClickedItem.getFavoriteStatus().equals(MainActivity.ADD_FAVORITE)){
            favoriteButton.setBackgroundResource(R.drawable.filledfav);
        }
    }


    /**
     * This will let the user click on the heart icon to favorite it or remove favorite it
     */

    public void setFavoriteButton(){

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (thingsToDoClassClassClickedItem.getFavoriteStatus()) {
                    case MainActivity.REMOVE_FAVORITE:
                        favoriteButton.setBackgroundResource(R.drawable.filledfav);
                        helper.updateFavoriteStatus(selectedId, MainActivity.ADD_FAVORITE);
                        thingsToDoClassClassClickedItem.setFavoriteStatus(MainActivity.ADD_FAVORITE);
                        Snackbar.make(v, "Added to Favorites", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        break;
                    case MainActivity.ADD_FAVORITE:
                        favoriteButton.setBackgroundResource(R.drawable.emptyfav);
                        helper.updateFavoriteStatus(selectedId, MainActivity.REMOVE_FAVORITE);
                        thingsToDoClassClassClickedItem.setFavoriteStatus(MainActivity.REMOVE_FAVORITE);
                        Snackbar.make(v, "Removed from Favorites", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        break;
                }
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
        favoriteButton = (Button)findViewById(R.id.favoriteButton);
        helper = LakeMerrittSQLiteOpenHelper.getInstance(DescriptionActivity.this);


    }

}
