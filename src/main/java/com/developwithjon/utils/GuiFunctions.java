package com.developwithjon.utils;

import com.developwithjon.Webscrape.WebScrape;

import java.util.ArrayList;

public class GuiFunctions {

    public GuiFunctions() {
    }

    public ArrayList webScrapeFunction(String URL){
        WebScrape scraper = new WebScrape();
        return scraper.collectData(URL);
    }
}
