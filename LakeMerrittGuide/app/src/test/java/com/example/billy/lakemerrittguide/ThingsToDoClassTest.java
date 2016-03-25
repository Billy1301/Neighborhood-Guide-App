package com.example.billy.lakemerrittguide;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;


/**
 * Created by Billy on 3/23/16.  --- this one is not connecting. did one on the default ExampleUnitTest class..
 *
 */
public class ThingsToDoClassTest {


    @Test
    public void testFullName(){
        ThingsToDoClass fullName = new ThingsToDoClass("Portal", "234 Foothill", "4 Stars", "510-234,5678", "Brunch", "$$");

        String expected = "Portal";
        String actual = fullName.getPlaceName();

        assertEquals(expected, actual);
    }

    @Test
    public void testAdress(){
        ThingsToDoClass address = new ThingsToDoClass("Portal", "234 Foothill", "4 Stars", "510-234,5678", "Brunch", "$$");

        String expected = "234 Foothill";
        String actual = address.getPlaceAddress();

        assertEquals(expected, actual);

    }


    @Test
    public void testRating(){
        ThingsToDoClass rating = new ThingsToDoClass("Portal", "234 Foothill", "4 Stars", "510-234,5678", "Brunch", "$$");

        String expected = "4 Stars";
        String actual = rating.getPlaceRatings();

        assertEquals(expected, actual);

    }

    @Test
    public void testPhone(){
        ThingsToDoClass phone = new ThingsToDoClass("Portal", "234 Foothill", "4 Stars", "510-234,5678", "Brunch", "$$");

        String expected = "510-234,5678";
        String actual = phone.getPlacePhoneNumber();

        assertEquals(expected, actual);

    }

    @Test
    public void testType(){
        ThingsToDoClass type = new ThingsToDoClass("Portal", "234 Foothill", "4 Stars", "510-234,5678", "Brunch", "$$");

        String expected = "Brunch";
        String actual = type.getPlaceType();

        assertEquals(expected, actual);

    }

    @Test
    public void testPrice(){
        ThingsToDoClass price = new ThingsToDoClass("Portal", "234 Foothill", "4 Stars", "510-234,5678", "Brunch", "$$");

        String expected = "$$";
        String actual = price.getPlacePrice();

        assertEquals(expected, actual);

    }
}
