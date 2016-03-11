package com.example.billy.bill_neightborhood_guide;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomListAdapter customAdapter;
    Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setView();

        ArrayList<String> data = new ArrayList<>(); // this will be my list of Catergories
        data.add("Restaurants");
        data.add("Events");
        data.add("Shops");

        customAdapter = new CustomListAdapter(data);
        setAdapater();
        floatButton();

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
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        searchButton = (Button)findViewById(R.id.search_button);

    }


    public void setAdapater(){
        // Allows us to change how we layout our items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this,1)); // the #1 is the grid look
        recyclerView.setAdapter(customAdapter);


    }
}
