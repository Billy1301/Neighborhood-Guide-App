package com.example.billy.bill_neighborhood_guide;

/**
 * Created by Billy on 3/12/16.
 */
public class Activity {
    private String activityName;
    private String activiteLocation;


    public Activity(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActiviteLocation() {
        return activiteLocation;
    }

    public void setActiviteLocation(String activiteLocation) {
        this.activiteLocation = activiteLocation;
    }
}
