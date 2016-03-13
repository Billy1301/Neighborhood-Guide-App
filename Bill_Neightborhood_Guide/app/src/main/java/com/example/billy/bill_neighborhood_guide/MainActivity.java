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

    RecyclerView recyclerView;
    ListView dataListView;
    Button searchButton;
    EditText userSearchInput;
    ArrayList<String> categoryList;
    ArrayAdapter<String> listAdapter;
    TextView activity;
    TextView restaurants;
    TextView shops;
    RecycleViewAdapter customAdapter;

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
                resultListIntent.putExtra("TitleName", title);
                startActivity(resultListIntent);


            }
        });


        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultListIntent = new Intent(MainActivity.this, list_results.class);
                String title = activity.getText().toString();
                resultListIntent.putExtra("TitleName", title);
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
        FloatingActionButton fab = (FloatingActionButton) findViewById(com.example.billy.bill_neighborhood_guide.R.id.float_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

/*   // not using any ListView for Category list atm

    public void setCategoryList(){
        categoryList = new ArrayList<>(); // this will be my list of Categories

        categoryList.add("Restaurants");
        categoryList.add("Events");
        categoryList.add("Shops");
        categoryList.add("num4");
        categoryList.add("num5");
        categoryList.add("num6");
    }
*/



    public void setView(){
        // recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);  //set it off to display only a simple Listview
        userSearchInput = (EditText) findViewById(R.id.main_edit_text);
        // dataListView = (ListView)findViewById(R.id.main_list_view);
        searchButton = (Button)findViewById(R.id.search_button);
        restaurants = (TextView)findViewById(R.id.restuarant_textview);
        activity = (TextView)findViewById(R.id.activity_textview);
        shops = (TextView)findViewById(R.id.shop_textview);

    }

    /**
     * need to fix adapter to connect to right layout view.. it's currently set to wrong layout
     */

 /*   public void setAdapater(){

//        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categoryList);
//        dataListView.setAdapter(listAdapter);

        customAdapter = new RecycleViewAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this,1)); // the #1 is the grid look
        recyclerView.setAdapter(customAdapter);


    }*/
}
