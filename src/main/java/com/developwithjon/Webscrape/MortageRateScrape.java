package com.developwithjon.Webscrape;

import org.json.JSONObject;

import java.time.LocalDate;
import java.net.*;
import java.io.*;

public class MortageRateScrape {
    public float mortgageRateAPIRequest() throws Exception {
    String weekAgo = LocalDate.now().minusDays(7).toString();
    String formatURL = String.format("https://api.stlouisfed.org/fred/series/observations?series_id=MORTGAGE30US&observation_start=%s&observation_end=2022-10-22&api_key=600f4c6e14418ed1524f7668f71794a9&sort_order=desc&limit=1&file_type=json", weekAgo);

    URL mortgageRatesURL = new URL(formatURL);
    URLConnection mortgageRatesAPI = mortgageRatesURL.openConnection();
    BufferedReader in = new BufferedReader(
            new InputStreamReader(
                    mortgageRatesAPI.getInputStream()));
    String inputLine;
    inputLine = in.readLine();
    if (inputLine != null) {
        JSONObject outputJson = new JSONObject(inputLine);
        in.close();

        float mortgageRate = Float.parseFloat(outputJson.getJSONArray("observations").getJSONObject(0).get("value").toString());
        return mortgageRate;
    } else {
        return 0;
    }
}
}
