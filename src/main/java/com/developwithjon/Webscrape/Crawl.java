package com.developwithjon.Webscrape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Crawl {
    String URL;

    public ArrayList crawlSite(){
        try {
            Document doc = Jsoup.connect("https://www.realtor.com/realestateandhomes-search/Orlando_FL/type-multi-family-home").get();
            Elements links = doc.getElementsByClass("jsx-1709448077 srp-content").select("[href]");
            ArrayList<Object> linkA = new ArrayList<>();
            for (Element link : links) {
                linkA.add(link.attr("href"));
            }
            return linkA;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Crawl(String URL) {
        this.URL = URL;

    }
}
