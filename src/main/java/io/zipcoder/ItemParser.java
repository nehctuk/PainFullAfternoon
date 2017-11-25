package io.zipcoder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ItemParser {

    public ArrayList<String> parseRawDataIntoStringArray(String rawData){
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawData);
        return response;
    }

    public String parseName(String rawItem) throws ItemParseException {
        String reg = "(?<=name:)\\w+";
        Pattern namePattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher nameMatcher = namePattern.matcher(rawItem);
        String foundName = null;
        while (nameMatcher.find()) {
             foundName = nameMatcher.group().toLowerCase().replace('0','o');
        }
        if (foundName==null) {
            throw new ItemParseException();
        }
        return foundName;
    }

    public Double parsePrice(String rawItem) throws ItemParseException {
        String reg = "(?<=price:)\\d+[.]\\d+";
        Pattern pricePattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher priceMatcher = pricePattern.matcher(rawItem);
        Double foundPrice = null;
        while (priceMatcher.find()) {
            foundPrice = Double.valueOf(priceMatcher.group());
        }
        if (foundPrice==null) {
            throw new ItemParseException();
        }
        return foundPrice;
    }

    public String parseType(String rawItem) throws ItemParseException {
        String reg = "(?<=type:)\\w+";
        Pattern pricePattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher priceMatcher = pricePattern.matcher(rawItem);
        String foundType = null;
        while (priceMatcher.find()) {
            foundType = priceMatcher.group().toLowerCase();
        }

        if (foundType==null) {
            throw new ItemParseException();
        }
        return foundType;
    }

    public String parseExpirationDate(String rawItem) throws ItemParseException {
        String reg = "(?<=expiration:)\\d+/\\d+/\\d+";
        Pattern pricePattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher priceMatcher = pricePattern.matcher(rawItem);
        String foundExpiration = null;
        while (priceMatcher.find()) {
            foundExpiration = priceMatcher.group();
        }

        if (foundExpiration==null) {
            throw new ItemParseException();
        }

        return foundExpiration;
    }

    public Item parseStringIntoItem(String rawItem) throws ItemParseException{
        String parsedName = parseName(rawItem);
        Double parsePrice = parsePrice(rawItem);
        String parseType = parseType(rawItem);
        String parseExpiration = parseExpirationDate(rawItem);

        Item newItem = new Item(parsedName,parsePrice, parseType, parseExpiration);
        return newItem;
    }

    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem){
        String stringPattern = "[;|^]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawItem);
        return response;
    }

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString){
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }
}
