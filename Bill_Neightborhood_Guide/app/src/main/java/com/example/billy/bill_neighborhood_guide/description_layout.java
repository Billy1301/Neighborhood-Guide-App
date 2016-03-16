package com.example.billy.bill_neighborhood_guide;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class description_layout extends AppCompatActivity {

    TextView descriptionTitleName;
    TextView addressText;
    ImageView titleImage;
    ArrayList<String> allListData;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setView();
        setTitle();


        /**
         *
         * setup Arraylist of all result
         *
         */

        FloatingActionButton fab = (FloatingActionButton) findViewById(com.example.billy.bill_neighborhood_guide.R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public void setView() {
        descriptionTitleName = (TextView) findViewById(R.id.description_title);
        titleImage = (ImageView) findViewById(R.id.description_image);
        addressText = (TextView) findViewById(R.id.description_addressView);

    }

    public void setTitle() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String titleExtra = extras.getString("result_title");
            descriptionTitleName.setText(titleExtra);


        }
    }


}
