package com.example.billy.lakemerrittguide;

/**
 * Created by Billy on 3/18/16.
 */
public class ThingsToDoClass {
    private String placeName;
    private String placeAddress;
    private String placeRatings;
    private String placePhoneNumber;
    private String placeType;
    private String placePrice;
    private String placeInfo;
    private String favoriteStatus;
    private String CategoryTypes;
    private int infoMainImageLogo;
    private int infoImageOne;


    public ThingsToDoClass(){

    }

    public ThingsToDoClass(String placeName, String placeAddress, String placeRatings, String placePhoneNumber, String placeType, String placePrice) {
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.placeRatings = placeRatings;
        this.placePhoneNumber = placePhoneNumber;
        this.placeType = placeType;
        this.placePrice = placePrice;
    }

    public ThingsToDoClass(String categoryTypes, String placeName, String placeAddress, String placePhoneNumber, String placeRatings, String placePrice, String placeType,  String placeInfo, int infoMainImageLogo, int infoImageOne, String favoriteStatus) {
        this.CategoryTypes = categoryTypes;
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.placeRatings = placeRatings;
        this.placePhoneNumber = placePhoneNumber;
        this.placeType = placeType;
        this.placePrice = placePrice;
        this.placeInfo = placeInfo;
        this.infoMainImageLogo = infoMainImageLogo;
        this.infoImageOne = infoImageOne;
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

    public int getInfoMainImageLogo() {
        return infoMainImageLogo;
    }

    public void setInfoMainImageLogo(int infoMainImageLogo) {
        this.infoMainImageLogo = infoMainImageLogo;
    }

    public int getInfoImageOne() {
        return infoImageOne;
    }

    public void setInfoImageOne(int infoImageOne) {
        this.infoImageOne = infoImageOne;
    }

    public String getCategoryTypes() {
        return CategoryTypes;
    }

    public void setCategoryTypes(String categoryTypes) {
        CategoryTypes = categoryTypes;
    }
}


