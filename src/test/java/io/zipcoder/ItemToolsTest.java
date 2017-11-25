package io.zipcoder;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ItemToolsTest {
    ItemTools aItemTools = new ItemTools();
    ItemParser aItemParser = new ItemParser();

    private String rawMultipleItems =
            "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##"
            +"naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##"
            +"NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##";

    ArrayList<String> arrayListOfItems = aItemParser.parseRawDataIntoStringArray(rawMultipleItems);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createListOfItemsTest() throws Exception {
        String expectedOne = "name:milk price:3.23 type:food expiration:1/25/2016";
        String expectedTwo = "name:bread price:1.23 type:food expiration:1/02/2016";
        String expectedThree = "name:bread price:1.23 type:food expiration:2/25/2016";

        aItemTools.createListOfItems(arrayListOfItems);
        ArrayList<Item> listOfItems = aItemTools.getListOfItems();
        String actualOne = listOfItems.get(0).toString();
        String actualTwo = listOfItems.get(1).toString();
        String actualThree = listOfItems.get(2).toString();

        Assert.assertEquals(expectedOne,actualOne);
        Assert.assertEquals(expectedTwo,actualTwo);
        Assert.assertEquals(expectedThree,actualThree);

    }

    @Test
    public void createUniqueListOfNamesTest() throws Exception {
        aItemTools.createListOfItems(arrayListOfItems);
        aItemTools.createUniqueListOfNames();
        boolean actual = aItemTools.getUniqueNames().contains("milk");
        Assert.assertTrue(actual);
    }
}