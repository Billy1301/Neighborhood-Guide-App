package com.example.billy.bill_neighborhood_guide;

/**
 * Created by Billy on 3/12/16.
 */
public class Activity {
    private String activityName;
    private String activityLocation;


    public Activity(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityLocation() {
        return activityLocation;
    }

    public void setActivityLocation(String activityLocation) {
        this.activityLocation = activityLocation;
    }
}
