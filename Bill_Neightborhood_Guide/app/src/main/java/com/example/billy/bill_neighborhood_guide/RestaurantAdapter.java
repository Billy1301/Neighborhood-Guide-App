package com.example.billy.bill_neighborhood_guide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billy on 3/12/16.
 */
public class RestaurantAdapter extends ArrayAdapter<Restaurants> {

    List<Restaurants> data; //



    public RestaurantAdapter(Context context, ArrayList<Restaurants> objects) {
        super(context, -1, objects);
        this.data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_layout_view, parent, false);

        TextView firstTextView = (TextView) rowItem.findViewById(R.id.address_textView);
        TextView secondTextView = (TextView) rowItem.findViewById(R.id.area_description_text);
        ImageView images = (ImageView) rowItem.findViewById(R.id.result_list_image);

        Restaurants restaurants = data.get(position);
        firstTextView.setText(restaurants.getRestaurantName());
        secondTextView.setText(restaurants.getLocation());



        //use if/else statement to set images
        if (restaurants.getRestaurantName().equals("Rockin Crawfish") ) {
            images.setImageResource(R.drawable.foodmenu);
        }

        if (restaurants.getRestaurantName().equals("Portal")){
            images.setImageResource(R.drawable.background);
        }



        return rowItem;
    }


}
