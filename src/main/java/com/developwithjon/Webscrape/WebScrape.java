package com.developwithjon.Webscrape;

import com.developwithjon.utils.Calculations;
import com.developwithjon.utils.RegexParser;
import com.developwithjon.utils.StringParser;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.json.JSONObject;

public class WebScrape {

    public static ArrayList collectData(String URL){
        Document document=null;
        ArrayList<Object> dataArray;

        try{
            System.out.printf("collecting data from %s\n", URL);
            document = Jsoup.connect(URL).get();

            parseFeatures(document);
            parseFundamentals(document);
            parseTables(document);
            dataArray =  new ArrayList<Object>(Arrays.asList(
                    parseFeatures(document),
                    parseFundamentals(document),
                    parseTables(document),
                    parsePropertyFeatures(document)
            ));
            return dataArray;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static HashMap parseFundamentals(Document document){
        double offerPrice;
        String salesStatus;
        String address;
        boolean isHot;

        HashMap<String, Object> fundamentalMap = new HashMap<>();
        NumberFormat format = NumberFormat.getCurrencyInstance();

        try {
            salesStatus = document.select("span.jsx-3853574337.statusText.ldpPage").text();
            offerPrice = Double.parseDouble(format.parse(document.getElementsByClass("Price__Component-rui__x3geed-0 gipzbd").text()).toString());
            address = document.getElementsByClass("Text__StyledText-rui__sc-19ei9fn-0 dEYYQ TypeBody__StyledBody-rui__sc-163o7f1-0 gVxVge").get(0).text();
            StringParser stringParser = new StringParser();
            HashMap<String, String> addressMap = stringParser.parseAddress(address);
            if (document.getElementsByClass("jsx-1072481585 hot-market-container").size() > 0){
                isHot = true;
            } else {
                isHot = false;
            }
            fundamentalMap.put("salesStatus", salesStatus);
            fundamentalMap.put("offerPrice", offerPrice);
            fundamentalMap.put("address", address);
            fundamentalMap.put("isHot", isHot);
            return fundamentalMap;
        } catch (Exception e){
            e.printStackTrace();
            return fundamentalMap;
        }
    }
    public static HashMap parseFeatures(Document document){
        HashMap<String, Object> featuresHashMap = new HashMap<>();
        String rawText = "";

        try {
            Elements elements = document.getElementsByClass("jsx-2231148777");
            for ( Element ele : elements) {
                String featureText = ele.text();
                if (featureText.length() < 50) {
                    rawText+=featureText + "\n";
                }
            }
            RegexParser regexParser = new RegexParser();
            return regexParser.parseWebscrape(rawText);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static HashMap parsePropertyFeatures(Document document){

        String yearBuilt;
        String HOAFee;
        String propertyType;
        String averageRental;
        String totalUnitRents;
        HashMap<String, Object> propertyFeatureMap = new HashMap<>();

        try {
            Thread.sleep(10);
            int propertyFeaturesSize = document.select("div:contains(Property Features)").size();
            if (propertyFeaturesSize > 0) {
                System.out.println("searching for property features...");
                Element propertyFeatures = document.select("div:contains(Property Features)").get(propertyFeaturesSize - 1);
                // bedrooms
                int bedroomSize = propertyFeatures.select("ul:contains(Bedrooms)").size();
                String bedrooms = propertyFeatures.select("ul:contains(Bedrooms)").get(bedroomSize-1).text();
                // Building & Construction

                int yearBuiltSize = propertyFeatures.select("ul:contains(Year Built:)").size();
                yearBuilt = propertyFeatures.select("ul:contains(Year Built:)").get(yearBuiltSize-1).text();
                RegexParser parser = new RegexParser();

                int HOAFeeSize = propertyFeatures.select("ul:contains(Association Fee:)").size();
                int propertyTypeSize = propertyFeatures.select("ul:contains(Property Subtype:)").size();
                Elements propertyEle = propertyFeatures.select("ul:contains(Property Subtype:)");
                propertyType = propertyEle.select("li:contains(Property Subtype:)").text();

                String rent = propertyFeatures.select("ul:contains(Rent:)").toString();

                ArrayList<String> rentArray = parser.parseWebscrapeArray(rent, "Rent");
                Calculations calc = new Calculations();

                String totalRent = Double.toString(calc.calculateSum(rentArray));

                System.out.println(totalRent);
                // Add to map
                propertyFeatureMap.put("yearBuilt", parser.parseWebscrape(yearBuilt, "Year Built"));
                propertyFeatureMap.put("propertyType", parser.parseWebscrape(propertyType, "Property Subtype"));
                propertyFeatureMap.put("totalRent", totalRent);

            } else{
                System.out.printf("no property features found...\n%s\n", document.select("div:contains(Property Features)").text());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return propertyFeatureMap;
    }
    public static ArrayList parseTables(Document document) {
        try {
            final Elements tables = document.select("table");
            ArrayList<JSONObject> tableData = new ArrayList();
            for (Element table : document.select("table")) {

                JSONObject tableMap = new JSONObject();
                String tableClassName = table.parent().select("div").first().className();
                if (tableClassName == "" || tableClassName == null){
                    tableClassName = "schoolTable";
                }
                tableMap.put("tableName", tableClassName);
                JSONArray rowDataArray = new JSONArray();

                ArrayList<String> headerNames = new ArrayList<>();
                for (Element row : table.select("tr")) {
                    JSONObject rowMap = new JSONObject();
                    int i = 0;
                    for (Element header : row.select("th")) {
                        headerNames.add(header.text());
                    }
                    for (Element point : row.select("td")) {
                        rowMap.put(headerNames.get(i), point.text());
                        i++;
                    }
                    if (rowMap.length() > 0) {
                        rowDataArray.put(rowMap);
                        tableMap.put("data", rowDataArray);
                    }

                }
                tableData.add(tableMap);
            }
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
