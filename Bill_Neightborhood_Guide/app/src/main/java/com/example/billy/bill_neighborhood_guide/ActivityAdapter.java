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
 * Created by Billy on 3/13/16.
 */
public class ActivityAdapter extends ArrayAdapter<Activity> {

    List<Activity> activityData; //



    public ActivityAdapter(Context context, ArrayList<Activity> objects) {
        super(context, -1, objects);
        this.activityData = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_layout_view, parent, false);

        TextView firstTextView = (TextView) rowItem.findViewById(R.id.title_name);
        TextView secondTextView = (TextView)rowItem.findViewById(R.id.address);
        ImageView images = (ImageView) rowItem.findViewById(R.id.event_image);

        Activity activity = activityData.get(position);
        firstTextView.setText(activity.getActivityName());
        secondTextView.setText(activity.getActivityLocation());



        //use if/else statement to set images

        String activityName = activity.getActivityName();
        if (activityName.equals("Exercising") ) {
            images.setImageResource(R.drawable.exercising2);
        }

        if (activityName.equals("Parks")){
            images.setImageResource(R.drawable.japanesegarden1);
        }



        return rowItem;
    }

}
