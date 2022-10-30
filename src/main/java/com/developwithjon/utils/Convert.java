package com.developwithjon.utils;

import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class Convert {
    public String convertAbbrev(String abbrev) {
        try {
            JSONParser parser = new JSONParser();
            String localDir = System.getProperty("user.dir");
            org.json.simple.JSONObject abbrevMap = (org.json.simple.JSONObject) parser.parse(new FileReader(localDir + "/src/main/java/com/developwithjon/utils/stateAbbrev.json"));
            String result = (String) abbrevMap.get(abbrev);
            return result;
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}

