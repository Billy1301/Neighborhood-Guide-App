package com.example.billy.lakemerrittguide;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Billy on 3/23/16.
 */
public class ThingsToDoTest {


    @Test
    public void testFullName(){
        ThingsToDo fullName = new ThingsToDo("Portal", "234 Foothill", "4 Stars", "510-234,5678", "Brunch", "$$");

        String expected = "Portal";
        String actual = fullName.getPlaceName();

        assertEquals(expected, actual);
    }

    @Test
    public void testAdress(){
        ThingsToDo address = new ThingsToDo("Portal", "234 Foothill", "4 Stars", "510-234,5678", "Brunch", "$$");

        String expected = "234 Foothill";
        String actual = address.getPlaceAddress();

        assertEquals(expected, actual);

    }


    @Test
    public void testRating(){
        ThingsToDo rating = new ThingsToDo("Portal", "234 Foothill", "4 Stars", "510-234,5678", "Brunch", "$$");

        String expected = "4 Stars";
        String actual = rating.getPlaceRatings();

        assertEquals(expected, actual);

    }

    @Test
    public void testPhone(){
        ThingsToDo phone = new ThingsToDo("Portal", "234 Foothill", "4 Stars", "510-234,5678", "Brunch", "$$");

        String expected = "510-234,5678";
        String actual = phone.getPlacePhoneNumber();

        assertEquals(expected, actual);

    }

    @Test
    public void testType(){
        ThingsToDo type = new ThingsToDo("Portal", "234 Foothill", "4 Stars", "510-234,5678", "Brunch", "$$");

        String expected = "Brunch";
        String actual = type.getPlaceType();

        assertEquals(expected, actual);

    }

    @Test
    public void testPrice(){
        ThingsToDo price = new ThingsToDo("Portal", "234 Foothill", "4 Stars", "510-234,5678", "Brunch", "$$");

        String expected = "$$";
        String actual = price.getPlacePrice();

        assertEquals(expected, actual);

    }
}
