package com.example.billy.bill_neightborhood_guide;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billy on 3/8/16.
 */
public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.CustomHolder> {

    //Our collection actually that backs our RecyclerView. Placeholder for data that we will bring in.
    ArrayList<String> dataItems;

    // public abstract ___ (){} <--- creates an abstract method. We don't know exactly what it does, but we have one for the user to define later

    //Constructor. Initialze our actual ArrayList;
    //Uses this one if no data is inputted from MainActivity ====> CustomListAdapter
    public CustomListAdapter() {
        dataItems = new ArrayList<>();  //All this does is create an ArrayList
    }

    // Constructor if there is an input of ArrayList<String> it sets this.dataItems to the data that we inputted dataItems
    // called in mainActivity ======>>>> customAdapter = new data.add("Restaurant 1");
    public CustomListAdapter(ArrayList<String> dataItems) {
        this.dataItems = dataItems;  // Pass in an existing object, which may or may not be populated with data. so that this has access to the data
    }

    public void addItem(String data){
        dataItems.add(data);
        notifyDataSetChanged();  //Performance improvement, but updating after each add
    }

    public void addItems(List<String> dataItems){
        dataItems.addAll(dataItems);
        notifyDataSetChanged();
    }


    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        DataListView dataListView = (DataListView) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_view, parent, false);
        return new CustomHolder(dataListView);
    }

    @Override  //Connects view to our actual collection
    public void onBindViewHolder(CustomHolder holder, int position) {  //Get the item that we are currently on, when scrolling. Get corresponding data item to our collection
        holder.bindTo(dataItems.get(position));  // Get a string, and use our bindTo, and pass that data to our view
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }


    // Create our ViewHolder

    public static final class CustomHolder extends RecyclerView.ViewHolder{
        DataListView dataListView;

        public CustomHolder(DataListView itemView) {
            super(itemView);  // Assign the objects here. *****   Dependency injection   *****
            dataListView = itemView;
        }

        // Define a custom method that allows us to pass data to our view
        // Can use findviewbyId here, but we use the class to separate the adapter and view
        public void bindTo(String data){
            dataListView.bindTo(data);  // This will use the method from our class.
        }
    }

}
