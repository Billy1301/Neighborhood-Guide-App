package com.example.billy.lakemerrittguide;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Billy on 3/24/16.
 */
public class InsertListsForDB {

    public final static String RESTAURANTS_CATEGORY = "Restaurants";
    public final static String ACTIVITIES_CATEGORY = "Activities";
    public final static String ADD_FAVORITE = "iDoLikeThis";
    public final static String REMOVE_FAVORITE = "iDoNotLikeThis";


    /**
     * creating list for class objects and use OOP to insert into DB
     *
     */

    public static ArrayList<ThingsToDoClass> getRestaurantsList(Context context){

        ThingsToDoClass portalRestaurant = new ThingsToDoClass();
        portalRestaurant.setCategoryTypes(RESTAURANTS_CATEGORY);
        portalRestaurant.setPlaceName(context.getString(R.string.portalName));
        portalRestaurant.setPlaceAddress(context.getString(R.string.portal_address));
        portalRestaurant.setPlacePhoneNumber(context.getString(R.string.portal_phone));
        portalRestaurant.setPlaceRatings(context.getString(R.string.four_stars));
        portalRestaurant.setPlacePrice(context.getString(R.string.moderatePrice));
        portalRestaurant.setPlaceType(context.getString(R.string.portal_type));
        portalRestaurant.setPlaceInfo(context.getString(R.string.portal_info));
        portalRestaurant.setInfoMainImageLogo(R.drawable.portalbrunch1);
        portalRestaurant.setInfoImageOne(R.drawable.portalfood4);
        portalRestaurant.setFavoriteStatus(REMOVE_FAVORITE);

        ThingsToDoClass jongGaRestaurant = new ThingsToDoClass(RESTAURANTS_CATEGORY, context.getString(R.string.jongGaName), context.getString(R.string.jong_ga_address), context.getString(R.string.jongGaPhone), context.getString(R.string.five_stars), context.getString(R.string.moderatePrice), context.getString(R.string.korean_type), context.getString(R.string.jongGA_info), R.drawable.jonggabbq2, R.drawable.jonggafood1, REMOVE_FAVORITE);
        ThingsToDoClass grandLakeRestaurant = new ThingsToDoClass(RESTAURANTS_CATEGORY, context.getString(R.string.grandLakeName), context.getString(R.string.grand_lake_address), context.getString(R.string.grandLakePhone), context.getString(R.string.three_stars), context.getString(R.string.high_price), context.getString(R.string.grandLake_type), context.getString(R.string.grandLakeKitchen_info), R.drawable.grandlake10, R.drawable.grandlakerunch1, REMOVE_FAVORITE);
        ThingsToDoClass rockinCrawfishRestaurant = new ThingsToDoClass(RESTAURANTS_CATEGORY, context.getString(R.string.rockinCrawfishName), context.getString(R.string.rockin_crawfish_address), context.getString(R.string.rockinCrawfishPhone), context.getString(R.string.four_stars), context.getString(R.string.moderatePrice), context.getString(R.string.rockinCrawfish_type), context.getString(R.string.rockinCrawfish_info), R.drawable.rockinfoods2, R.drawable.rockinfoods1, REMOVE_FAVORITE);
        ThingsToDoClass haddonCafeRestaurant = new ThingsToDoClass(RESTAURANTS_CATEGORY, context.getString(R.string.haddonCafeName), context.getString(R.string.haddon_address), null, context.getString(R.string.five_stars), context.getString(R.string.low_price), context.getString(R.string.cafe_type_name), context.getString(R.string.haddonHill_info), R.drawable.haddonhillcafelogo, R.drawable.haddonhilldrinks1, REMOVE_FAVORITE);
        ThingsToDoClass arizmendiRestaurant = new ThingsToDoClass(RESTAURANTS_CATEGORY, context.getString(R.string.arizmendiName), context.getString(R.string.arizmendi_address), context.getString(R.string.arizmedi_phone), context.getString(R.string.five_stars), context.getString(R.string.moderatePrice),context.getString(R.string.arizmendi_type), context.getString(R.string.arizmendiBakery_info), R.drawable.arizmendibakerylogo, R.drawable.arizmendi2, REMOVE_FAVORITE);
        ThingsToDoClass michelBistroRestaurant = new ThingsToDoClass(RESTAURANTS_CATEGORY, context.getString(R.string.michelBistroName), context.getString(R.string.michel_address), context.getString(R.string.michel_phone), context.getString(R.string.four_stars), context.getString(R.string.moderatePrice), context.getString(R.string.michel_type), context.getString(R.string.michelBistro_info), R.drawable.michelfood2, R.drawable.michelfood6, REMOVE_FAVORITE);
        ThingsToDoClass theAlleyRestaurant = new ThingsToDoClass(RESTAURANTS_CATEGORY, context.getString(R.string.theAlleyName), context.getString(R.string.alley_address), context.getString(R.string.ally_phone), context.getString(R.string.four_stars), context.getString(R.string.moderatePrice), context.getString(R.string.theAlley_type), context.getString(R.string.theAlley_info), R.drawable.thealleylogo, R.drawable.thealleyfood1, REMOVE_FAVORITE);
        ThingsToDoClass offTheGridRestaurant = new ThingsToDoClass(RESTAURANTS_CATEGORY, context.getString(R.string.offTheGridName), context.getString(R.string.offthegrid_address), null, context.getString(R.string.four_stars), context.getString(R.string.moderatePrice), context.getString(R.string.offTheGrid_type), context.getString(R.string.offTheGrid_info), R.drawable.offthegridfood2, R.drawable.offthegridfoodtrucks1, REMOVE_FAVORITE);

        ArrayList<ThingsToDoClass> restaurants = new ArrayList<>();
        restaurants.add(theAlleyRestaurant);
        restaurants.add(portalRestaurant);
        restaurants.add(rockinCrawfishRestaurant);
        restaurants.add(offTheGridRestaurant);
        restaurants.add(jongGaRestaurant);
        restaurants.add(grandLakeRestaurant);
        restaurants.add(haddonCafeRestaurant);
        restaurants.add(arizmendiRestaurant);
        restaurants.add(michelBistroRestaurant);


        return restaurants;
    }


    public static ArrayList<ThingsToDoClass> getActivitiesList(Context context){

        ThingsToDoClass theGarden = new ThingsToDoClass(ACTIVITIES_CATEGORY, context.getString(R.string.theGardenName), context.getString(R.string.thegardens_address), null, null, null, context.getString(R.string.parks_type_name), context.getString(R.string.theGardenInfo), R.drawable.thegardenslogo2, R.drawable.japaneseimagemain, REMOVE_FAVORITE);
        ThingsToDoClass japaneseGarden = new ThingsToDoClass (ACTIVITIES_CATEGORY, context.getString(R.string.japaneseGardenName), context.getString(R.string.japanesegarden_address), null, null, null, context.getString(R.string.parks_type_name), context.getString(R.string.japaneseGardenInfo), R.drawable.japaneseimage11, R.drawable.japaneseimage22, REMOVE_FAVORITE);
        ThingsToDoClass palmGarden = new ThingsToDoClass(ACTIVITIES_CATEGORY, context.getString(R.string.palmGardenName), context.getString(R.string.palmgarden_address), null, null, null, context.getString(R.string.parks_type_name), context.getString(R.string.palmGardenInfo), R.drawable.palmgarden, R.drawable.palmgarden1, REMOVE_FAVORITE);
        ThingsToDoClass mediterranGarden = new ThingsToDoClass(ACTIVITIES_CATEGORY, context.getString(R.string.mediterranName), context.getString(R.string.mediterran_address), null, null, null, context.getString(R.string.parks_type_name), context.getString(R.string.mediterraneanGardenInfo), R.drawable.mediterraneangarden, R.drawable.mediterrane1, REMOVE_FAVORITE);
        ThingsToDoClass waterSports = new ThingsToDoClass(ACTIVITIES_CATEGORY, context.getString(R.string.waterSportName), context.getString(R.string.waterSport_address), context.getString(R.string.waterSport_phone), null, null, context.getString(R.string.sports_type_name), context.getString(R.string.waterSportsInfo), R.drawable.watersailboat, R.drawable.waterrental, REMOVE_FAVORITE);
        ThingsToDoClass fairyland = new ThingsToDoClass(ACTIVITIES_CATEGORY, context.getString(R.string.fairyLandName), context.getString(R.string.fairyland_address), null, null, null, context.getString(R.string.parks_type_name), context.getString(R.string.fairyLandInfo), R.drawable.fairylandlogo, R.drawable.fairyland2, REMOVE_FAVORITE);
        ThingsToDoClass exercise = new ThingsToDoClass(ACTIVITIES_CATEGORY, context.getString(R.string.exerciseName), context.getString(R.string.exercise_address), null, null, null, context.getString(R.string.sports_type_name), context.getString(R.string.exerciseInfo), R.drawable.exercising2, R.drawable.exercisestairs, REMOVE_FAVORITE);
        ThingsToDoClass farmerMarket = new ThingsToDoClass(ACTIVITIES_CATEGORY, context.getString(R.string.farmerMarketName), context.getString(R.string.farmerMarket_address), null, null, null, context.getString(R.string.shop_type_name), context.getString(R.string.fleaMarketInfo), R.drawable.farmermarketlogo, R.drawable.farmermarket, REMOVE_FAVORITE);

        ArrayList<ThingsToDoClass> activities = new ArrayList<>();
        activities.add(waterSports);
        activities.add(farmerMarket);
        activities.add(fairyland);
        activities.add(exercise);
        activities.add(theGarden);
        activities.add(palmGarden);
        activities.add(mediterranGarden);
        activities.add(japaneseGarden);

        return activities;

    }

}
