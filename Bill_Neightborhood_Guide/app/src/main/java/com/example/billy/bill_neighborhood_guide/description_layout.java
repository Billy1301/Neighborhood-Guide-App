package com.example.billy.bill_neighborhood_guide;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class description_layout extends AppCompatActivity {

    TextView titleName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.billy.bill_neighborhood_guide.R.layout.activity_description_layout);
        Toolbar toolbar = (Toolbar) findViewById(com.example.billy.bill_neighborhood_guide.R.id.toolbar);

        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(com.example.billy.bill_neighborhood_guide.R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
