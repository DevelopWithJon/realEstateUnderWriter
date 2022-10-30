package com.developwithjon.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class StringParser {

    public HashMap parseAddress(String fullAddress){
        HashMap<String, String> addressMap = new HashMap<>();
        String[] addressArray = fullAddress.split(",");
        String[] stateArray = addressArray[2].trim().split(" ");

        addressMap.put("streetAddress", addressArray[0]);
        addressMap.put("city", addressArray[1]);
        addressMap.put("state", stateArray[0]);
        addressMap.put("zip", stateArray[1]);
        return addressMap;
    }

    public String removeFix(String str, String fix) {
        String outputStr = str.replace(fix, "");
        return outputStr;
    }
    public StringParser() {
    }
}
