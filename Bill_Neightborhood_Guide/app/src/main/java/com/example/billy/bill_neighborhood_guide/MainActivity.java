package com.example.billy.bill_neighborhood_guide;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button searchButton;
    EditText userSearchInput;
    TextView activity;
    TextView restaurants;
    ImageView mainImage;
    LakeMerrittSQLiteOpenHelper merrittHelper;

    public final static String TITLE_KEY = "TitleName";
    public final static String LIST_KEY = "listKey";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setView();
        floatButton();
        setOnItemClick();

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


    /**
     * item click will pull up the Lists of selected category and display the name on the Title
     */

    public void setOnItemClick(){

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultListIntent = new Intent(MainActivity.this, list_results.class);
                String title = restaurants.getText().toString();
                resultListIntent.putExtra(TITLE_KEY, title);
                startActivity(resultListIntent);


            }
        });


        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultListIntent = new Intent(MainActivity.this, list_results.class);
                String title = activity.getText().toString();
                resultListIntent.putExtra(TITLE_KEY, title);
                startActivity(resultListIntent);

            }
        });





    }



    /**
     * floatButton pull up favorite list
     *
     */
    public void floatButton(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.float_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }




    public void setView(){
        userSearchInput = (EditText) findViewById(R.id.main_edit_text);
        searchButton = (Button)findViewById(R.id.search_button);
        restaurants = (TextView)findViewById(R.id.restuarant_textview);
        activity = (TextView)findViewById(R.id.activity_textview);
        mainImage = (ImageView)findViewById(R.id.main_image);





    }



}
