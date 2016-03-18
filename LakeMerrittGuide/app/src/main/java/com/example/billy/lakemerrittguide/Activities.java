package com.example.billy.lakemerrittguide;

/**
 * Created by Billy on 3/18/16.
 */
public class Activities {

    private String activityName;
    private String activityAddress;
    private String activityType;
    private String activityPhoneNumber;


    public Activities(String activityName, String activityAddress, String activityType, String activityPhoneNumber) {
        this.activityName = activityName;
        this.activityAddress = activityAddress;
        this.activityType = activityType;
        this.activityPhoneNumber = activityPhoneNumber;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityPhoneNumber() {
        return activityPhoneNumber;
    }

    public void setActivityPhoneNumber(String activityPhoneNumber) {
        this.activityPhoneNumber = activityPhoneNumber;
    }
}
