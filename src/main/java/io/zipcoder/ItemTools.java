package io.zipcoder;

import java.util.*;
import java.util.stream.Collectors;

public class ItemTools {
    private static int numberOfErrors;
    private ArrayList<Item> listOfItems = new ArrayList<>();
    private Set<String> uniqueNames;
    private Map<Double, Integer> mapOfPriceAndCount;

    public ArrayList<Item> getListOfItems() {
        return listOfItems;
    }

    public Set<String> getUniqueNames() {
        return uniqueNames;
    }

    public Map<Double, Integer> getMapOfPriceAndCount() {
        return mapOfPriceAndCount;
    }

    public void createListOfItems(ArrayList<String> arrayListOfItems) {
        ItemParser aItemParser = new ItemParser();
        for (String stringItem : arrayListOfItems) {
            try {
                Item newItem = aItemParser.parseStringIntoItem(stringItem);
                listOfItems.add(newItem);
            } catch (ItemParseException e) {
                numberOfErrors++;
            }
        }
    }

    public void createUniqueListOfNames() {
        ArrayList<String> listOfNames = new ArrayList<>();
        listOfItems.forEach(item -> listOfNames.add(item.getName()));
        uniqueNames = new HashSet<>(listOfNames);
    }

    private ArrayList<Item> findGroupOfItems(String input) {
        return listOfItems.stream().
                filter(item -> item.getName().equalsIgnoreCase(input)).
                collect(Collectors.toCollection(ArrayList::new));
    }

    private void createMapOfItems(String input) {
        ArrayList<Item> groupOfSpecificFood = findGroupOfItems(input);
        mapOfPriceAndCount = new HashMap<>();

        for (Item aItem : groupOfSpecificFood) {
            if (mapOfPriceAndCount.containsKey(aItem.getPrice())){
                Integer count = mapOfPriceAndCount.get(aItem.getPrice())+1;
                mapOfPriceAndCount.put(aItem.getPrice(),count);
            }
            else {
                mapOfPriceAndCount.put(aItem.getPrice(),1);
            }
        }
    }

    private void displayItemInATable(String input) {
        System.out.println("name: "+input+"  \t\t seen: "+ findGroupOfItems(input).size()+" times");
        System.out.println("============= \t \t =============");

        printPriceAndCount();
        System.out.println("");
    }

    private void printPriceAndCount() {
        mapOfPriceAndCount.forEach((price, count) -> {
            System.out.println("Price: \t "+price+"\t\t seen: "+count+" times");
            System.out.println("-------------		 -------------");
        });
    }

    private void stringForNumberOfErrors() {
        System.out.println("Errors         \t \t seen: "+ numberOfErrors +" times");
    }

    public void displayAllItemsInATableWithErrorCount() {
        for (String foodName : uniqueNames) {
            createMapOfItems(foodName);
            displayItemInATable(foodName);
        }
        stringForNumberOfErrors();
    }
}
