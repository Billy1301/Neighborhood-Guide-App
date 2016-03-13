package com.example.billy.bill_neighborhood_guide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class list_results extends AppCompatActivity {

//    RecyclerView recyclerView;
//    RecycleViewAdapter customAdapter;
    TextView titleName;
    ListView resultListView;

    ArrayList<Restaurants> restaurantLists;
    RestaurantAdapter restaurantListAdapter;
    ArrayList<Activity> activityLists;
    ActivityAdapter activityListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_results);

        setView();
        setListTitle();

        setRestaurantLists();
        setActivityLists();
        setShopLists();

        setAdapter();


        /**
         * need to set an intent to display the area name for the Title bar.. use toolbar.setTitle(); on the description layout.
         *
         */

    }

    public void setRestaurantLists(){

        Restaurants restaurantsOne = new Restaurants("Rockin Crawfish");
        Restaurants restaurantsTwo = new Restaurants("Portal");
        restaurantsOne.setLocation("1234 Foot");
        restaurantsTwo.setLocation("567 livermoore");

        restaurantLists = new ArrayList<>();
        restaurantLists.add(restaurantsOne);
        restaurantLists.add(restaurantsTwo);


    }

    public void setActivityLists(){

        Activity activityOne = new Activity("Exercising");
        activityOne.setActiviteLocation("Lake Merritt");

        Activity activityTwo = new Activity("Parks");
        activityTwo.setActiviteLocation("Lakeshore Park");

        activityLists = new ArrayList<>();

        activityLists.add(activityOne);
        activityLists.add(activityTwo);


    }

    public void setShopLists(){


    }


    public void setView(){
        titleName = (TextView)findViewById(R.id.result_textView);
        resultListView = (ListView)findViewById(R.id.list_result_view);

        //recyclerView = (RecyclerView) findViewById(R.id.result.recyclerview);



    }
    public void setListTitle(){
        String titleExtra = getIntent().getStringExtra("TitleName");
        titleName.setText(titleExtra);

    }


    public void setAdapter() {

        restaurantListAdapter = new RestaurantAdapter(this, restaurantLists);
        activityListAdapter = new ActivityAdapter(this, activityLists);

        if (titleName.getText().equals("Restaurants")) {
            resultListView.setAdapter(restaurantListAdapter);
        }
         if(titleName.getText().equals("Activities")) {
             resultListView.setAdapter(activityListAdapter);
         }

    /*    customAdapter = new RecycleViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this,1)); // the #1 is the grid look

        recyclerView.setAdapter(customAdapter);*/

    }
}
