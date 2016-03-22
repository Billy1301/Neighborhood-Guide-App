package com.example.billy.lakemerrittguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    LakeMerrittSQLiteOpenHelper helper;
    private ThingsToDo thingsToDoClassClickedItem;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView locationTextView;
    private TextView typeTextView;
    private ImageView logoImage;
    private ImageView infoImageOne;
    private ImageView infoImageTwo;
    private int selectedId;
    private Button favoriteButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setView();

        helper = LakeMerrittSQLiteOpenHelper.getInstance(DescriptionActivity.this);
        getAndSetIntentToView();
        setFavoriteButtonImage();
        setFavoriteButton();

        setImageClickToReturnHome();

        }



    public void getAndSetIntentToView() {

        selectedId = getIntent().getIntExtra(ResultListActivity.ID_KEY_SENDING, -1);

        if (selectedId >= 0) {

            thingsToDoClassClickedItem = helper.createObjects(selectedId);

            nameTextView.setText(thingsToDoClassClickedItem.getPlaceName());
            descriptionTextView.setText(thingsToDoClassClickedItem.getPlaceInfo());
            locationTextView.setText(thingsToDoClassClickedItem.getPlaceAddress());
            typeTextView.setText(thingsToDoClassClickedItem.getPlaceType());

            logoImage.setImageResource(thingsToDoClassClickedItem.getMainImageLogo());
            infoImageOne.setImageResource(thingsToDoClassClickedItem.getInfoImageOne());
            infoImageTwo.setImageResource(thingsToDoClassClickedItem.getInfoImageTwo());


        }

    }

    private void setFavoriteButtonImage(){
        if(thingsToDoClassClickedItem.getFavoriteStatus().equals(MainActivity.NO_FAVORITE_ICON)){
            favoriteButton.setBackgroundResource(R.drawable.emptyfav);
        } else if (thingsToDoClassClickedItem.getFavoriteStatus().equals(MainActivity.FAVORITE_ICON)){
            favoriteButton.setBackgroundResource(R.drawable.filledfav);
        }
    }


    public void setFavoriteButton(){

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (thingsToDoClassClickedItem.getFavoriteStatus()) {
                    case MainActivity.NO_FAVORITE_ICON:
                        favoriteButton.setBackgroundResource(R.drawable.filledfav);
                        helper.updateFavoriteStatus(selectedId, MainActivity.FAVORITE_ICON);
                        thingsToDoClassClickedItem.setFavoriteStatus(MainActivity.FAVORITE_ICON);
                        break;
                    case MainActivity.FAVORITE_ICON:
                        favoriteButton.setBackgroundResource(R.drawable.emptyfav);
                        helper.updateFavoriteStatus(selectedId, MainActivity.NO_FAVORITE_ICON);
                        thingsToDoClassClickedItem.setFavoriteStatus(MainActivity.NO_FAVORITE_ICON);
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
        infoImageTwo = (ImageView)findViewById(R.id.frameTwoImageTwo);
        favoriteButton = (Button)findViewById(R.id.favoriteButton);

    }

    public void setImageClickToReturnHome(){

        logoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DescriptionActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

}
