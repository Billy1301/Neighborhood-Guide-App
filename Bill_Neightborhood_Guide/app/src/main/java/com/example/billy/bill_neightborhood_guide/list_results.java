package com.example.billy.bill_neightborhood_guide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class list_results extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomListAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setView();

        ArrayList<String> data = new ArrayList<>();

        customAdapter = new CustomListAdapter(data);
        setAdapater();


        /**
         * need to set an intent to display the area name for the Title bar.. use toolbar.setTitle(); on the description layout.
         *
         */

    }


    public void setView(){
        recyclerView = (RecyclerView) findViewById(R.id.result_recyclerview);


    }
    public void setAdapater(){
        // Allows us to change how we layout our items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this,1)); // the #1 is the grid look
        recyclerView.setAdapter(customAdapter);


    }
}
