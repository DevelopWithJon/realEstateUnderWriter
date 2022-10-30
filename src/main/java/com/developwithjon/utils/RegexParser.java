package com.developwithjon.utils;

import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexParser {


    public String regexMatch(String inputText, String regexPattern) {

        Pattern pattern = Pattern.compile(regexPattern); // floating number regex pattern
        Matcher m = pattern.matcher(inputText);
        if (m.find()) {
            return m.group();
        } else {
            return null;
        }
    }
    public HashMap parseWebscrape(String inputText) {

        try {
            HashMap<String, String> regexMatchedMap = new HashMap<>();
            JSONParser parser = new JSONParser();
            String localDir = System.getProperty("user.dir");
            org.json.simple.JSONObject regexMap = (org.json.simple.JSONObject) parser.parse(new FileReader(localDir + "/src/main/java/com/developwithjon/utils/realtorRegex.json"));

            for (Object keyStr : regexMap.keySet()) {
                String regexPattern = regexMap.get(keyStr).toString();
                String matchedStr = regexMatch(inputText, regexPattern);
                if (matchedStr != null) {
                    regexMatchedMap.put(keyStr.toString(), matchedStr);
                }
            }
            return regexMatchedMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getDomain(String URL, String patternString){

        Pattern pattern = Pattern.compile(patternString);
        Matcher m = pattern.matcher(URL);
        if (m.find()) {
            return m.group().toString();
        } else {
            return null;
        }
    }
    public RegexParser() {
    }
}
