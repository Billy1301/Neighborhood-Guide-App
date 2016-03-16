package com.example.billy.bill_neighborhood_guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.billy.bill_neighborhood_guide.Adapters.ActivityAdapter;
import com.example.billy.bill_neighborhood_guide.Adapters.RestaurantAdapter;

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

    public final static String RESULT_TITLE = "result_title";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_results);

        setView();
        setListTitle();

        setRestaurantLists();
        setActivityLists();
        setShopLists();

        setOnItemClick();
        setAdapter();


    }


    /**
     * need to setup onItemClick to display information of place
     *
     */

    public void setOnItemClick(){
        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent resultListIntent = new Intent(list_results.this, description_layout.class);

                    // to grab from restaurantLists on item click and name
                //String descriptionTitleExtra = restaurantLists.get(position).getRestaurantName();

                // this is if we don't know where the name is from
                String descriptionTitleExtra = ((TextView)view.findViewById(R.id.title_name)).getText().toString();

                resultListIntent.putExtra("result_title", descriptionTitleExtra);
                startActivity(resultListIntent);


            }
        });

    }

    public void setRestaurantLists(){

        Restaurants restaurantsOne = new Restaurants("Rockin Crawfish");
        Restaurants restaurantsTwo = new Restaurants("Portal");
        restaurantsOne.setLocation("123 International");
        restaurantsTwo.setLocation("987 Foothill");

        restaurantLists = new ArrayList<>();
        restaurantLists.add(restaurantsOne);
        restaurantLists.add(restaurantsTwo);


    }

    public void setActivityLists(){

        Activity activityOne = new Activity("Exercising");
        Activity activityTwo = new Activity("Parks");

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


    }
}
