package com.developwithjon.Webscrape;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PropertyTax {

    public static JSONObject parseTables(Document document) {
        NumberFormat format = NumberFormat.getCurrencyInstance();

        try {
            Elements tableclass = document.getElementsByClass("table-border-top tab-ctr wide75 table-columns-left-center table-columns-tight hide-tabs-in-mobile");
            Elements table = tableclass.select("table");
            JSONObject tableMap = new JSONObject();
            System.out.println(table.select("tr").size());
            for (Element row : table.select("tr")) {
                JSONObject rowMap = new JSONObject();
                int i = 0;
                System.out.println(row);
                for (Element point : row.select("td")) {
                    System.out.println(point);
                    if (i == 0) {
                        System.out.println(point.text());
                        tableMap.put(point.text(), rowMap);
                    } else if (i == 1) {
                        JSONObject median_home_value = rowMap.put("Median Home Value", Double.parseDouble(format.parse(point.text()).toString()));
                    } else if (i == 2) {
                        rowMap.put("Median Annual Property Tax Payment", Double.parseDouble(format.parse(point.text()).toString()));
                    } else {
                        Pattern pattern = Pattern.compile("[+-]?([0-9]*[.])?[0-9]+"); // floating number regex pattern
                        Matcher m = pattern.matcher(point.text());
                        m.find();
                        rowMap.put("Average Effective Property Tax Rate", Float.parseFloat(m.group()));
                    }
                    i++;
                }
            }
            System.out.println(tableMap.toString(4));
            return tableMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String state;
        Document document=null;

        state = "new york";
        String formatURL = String.format("https://smartasset.com/taxes/%s-property-tax-calculator", state);
        try{
            document = Jsoup.connect(formatURL).get();
            parseTables(document);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
