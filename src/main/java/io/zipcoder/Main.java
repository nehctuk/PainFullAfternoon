package io.zipcoder;

import org.apache.commons.io.IOUtils;

import java.util.*;


public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        ItemParser itemParser = new ItemParser();
        ArrayList<String> arrayListOfItems = itemParser.parseRawDataIntoStringArray(output);
        ItemTools aItemTools = new ItemTools();
        aItemTools.createListOfItems(arrayListOfItems);
        aItemTools.createUniqueListOfNames();
        aItemTools.displayAllItemsInATableWithErrorCount();

        // TODO: parse the data in output into items, and display to console.
    }
}
