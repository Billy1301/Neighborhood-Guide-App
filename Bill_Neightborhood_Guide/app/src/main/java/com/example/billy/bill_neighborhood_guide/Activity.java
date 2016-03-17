package com.example.billy.bill_neighborhood_guide;

/**
 * Created by Billy on 3/12/16.
 */
public class Activity {
    private String activityName;
    private String activityAddress;
    private String activityType;
    private String activityPhoneNumber;


    public Activity(String activityName, String activityAddress, String activityType, String activityPhoneNumber) {
        this.activityName = activityName;
        this.activityAddress = activityAddress;
        this.activityType = activityType;
        this.activityPhoneNumber = activityPhoneNumber;
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

    public Activity(String activityName) {
        this.activityName = activityName;
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
}
