package com.example.billy.lakemerrittguide;

import java.util.ArrayList;

/**
 * Created by Billy on 3/18/16.
 */
public class ThingsToDo {
    private String placeName;
    private String placeAddress;
    private String placeRatings;
    private String placePhoneNumber;
    private String placeType;
    private String placePrice;
    private String placeInfo;
    private String favoriteStatus;
    private int mainImageLogo;
    private int infoImageOne;
    private int infoImageTwo;




    public ThingsToDo(String placeName, String placeAddress, String placeRatings, String placePhoneNumber, String placeType, String placePrice, String placeInfo) {
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.placeRatings = placeRatings;
        this.placePhoneNumber = placePhoneNumber;
        this.placeType = placeType;
        this.placePrice = placePrice;
        this.placeInfo = placeInfo;
    }


    public ThingsToDo(String placeName, String placeAddress, String placeRatings, String placePhoneNumber, String placeType, String placePrice, String placeInfo, int mainImageLogo, int infoImageOne, String favoriteStatus) {
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.placeRatings = placeRatings;
        this.placePhoneNumber = placePhoneNumber;
        this.placeType = placeType;
        this.placePrice = placePrice;
        this.placeInfo = placeInfo;
        this.mainImageLogo = mainImageLogo;
        this.infoImageOne = infoImageOne;
        //this.infoImageTwo = infoImageTwo;
        this.favoriteStatus = favoriteStatus;
    }

    public String getFavoriteStatus() {
        return favoriteStatus;
    }

    public void setFavoriteStatus(String favoriteStatus) {
        this.favoriteStatus = favoriteStatus;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public String getPlaceRatings() {
        return placeRatings;
    }

    public void setPlaceRatings(String placeRatings) {
        this.placeRatings = placeRatings;
    }

    public String getPlacePhoneNumber() {
        return placePhoneNumber;
    }

    public void setPlacePhoneNumber(String placePhoneNumber) {
        this.placePhoneNumber = placePhoneNumber;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getPlacePrice() {
        return placePrice;
    }

    public void setPlacePrice(String placePrice) {
        this.placePrice = placePrice;
    }

    public String getPlaceInfo() {
        return placeInfo;
    }

    public void setPlaceInfo(String placeInfo) {
        this.placeInfo = placeInfo;
    }

    public int getMainImageLogo() {
        return mainImageLogo;
    }

    public void setMainImageLogo(int mainImageLogo) {
        this.mainImageLogo = mainImageLogo;
    }

    public int getInfoImageOne() {
        return infoImageOne;
    }

    public void setInfoImageOne(int infoImageOne) {
        this.infoImageOne = infoImageOne;
    }

    public int getInfoImageTwo() {
        return infoImageTwo;
    }

    public void setInfoImageTwo(int infoImageTwo) {
        this.infoImageTwo = infoImageTwo;
    }


}


