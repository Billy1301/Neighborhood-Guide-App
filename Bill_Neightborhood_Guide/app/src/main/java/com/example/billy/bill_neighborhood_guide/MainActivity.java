package com.example.billy.bill_neighborhood_guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    RecyclerView recyclerView;
//    ListView dataListView;
    Button searchButton;
    EditText userSearchInput;
//    ArrayList<String> categoryList;
//    ArrayAdapter<String> listAdapter;
    TextView activity;
    TextView restaurants;
    TextView shops;
   // RecycleViewAdapter customAdapter;
    public final static String TITLE_KEY = "TitleName";
    public final static String LIST_KEY = "listKey";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setView();
        // setCategoryList();
        //setAdapater();
        floatButton();

        setOnItemClick();
        setSearchButton();


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

    public void setSearchButton(){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userText = userSearchInput.getText().toString();

                if (userText.isEmpty()) {

                    Snackbar.make(v, "Invalid search entry", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {


                    // set this to display search results
                }



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
        shops = (TextView)findViewById(R.id.shop_textview);

    }



}
