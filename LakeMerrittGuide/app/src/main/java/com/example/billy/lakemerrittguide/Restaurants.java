package com.example.billy.lakemerrittguide;

/**
 * Created by Billy on 3/18/16.
 */
public class Restaurants {
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantRatings;
    private String restaurantPhoneNumber;
    private String restaurantType;
    private String restaurantPrice;



    public Restaurants(String restaurantName, String restaurantAddress){
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;

    }

    public Restaurants(String restaurantName, String restaurantAddress, String restaurantRatings, String restaurantPhoneNumber, String restaurantType, String restaurantPrice) {
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantRatings = restaurantRatings;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.restaurantType = restaurantType;
        this.restaurantPrice = restaurantPrice;
    }

    public String getRestaurantPhoneNumber() {
        return restaurantPhoneNumber;
    }

    public void setRestaurantPhoneNumber(String restaurantPhoneNumber) {
        this.restaurantPhoneNumber = restaurantPhoneNumber;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    public String getRestaurantPrice() {
        return restaurantPrice;
    }

    public void setRestaurantPrice(String restaurantPrice) {
        this.restaurantPrice = restaurantPrice;
    }

    public Restaurants(String restaurantName) {
        this.restaurantName = restaurantName;
    }


    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantRatings() {
        return restaurantRatings;
    }

    public void setRestaurantRatings(String restaurantRatings) {
        this.restaurantRatings = restaurantRatings;
    }


}


