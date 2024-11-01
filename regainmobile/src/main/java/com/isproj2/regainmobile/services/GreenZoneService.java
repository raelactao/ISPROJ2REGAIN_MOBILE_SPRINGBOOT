package com.isproj2.regainmobile.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.model.GreenZone;

@Service
public class GreenZoneService {
    
    // Make sure the method is public so it can be accessed from the controller
    public List<GreenZone> getGreenZoneNews(String url) throws IOException {
        List<GreenZone> articles = new ArrayList<>();
        
        try {
            Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .referrer("https://www.google.com")
                .header("Accept-Language", "en-US,en;q=0.9")
                .timeout(30 * 1000)
                .get();
            
                Elements articleElements = doc.select(".article-item.mb-6.md\\:flex");


            if (articleElements != null) {
                Elements newsElements = articleElements.select("div.article-item");

                for (Element element : newsElements) {
                    String title = element.select("h2 > a").text();
                    String link = element.select("h2 > a").attr("href");
                    String dateTime = element.select("p > span").text();

                    Element summaryElement = element.select("p").last();
                        String summary = "";
                        if (summaryElement != null) {
                        summary = summaryElement.ownText(); 
                        }

                    GreenZone article = new GreenZone(title, dateTime, link, summary);
                    articles.add(article);
                }
            } else {
                System.out.println("No articles found in the provided URL.");
            }

        } catch (IOException e) {
            System.err.println("Error fetching news from " + url + ": " + e.getMessage());
            throw new IOException("Unable to fetch data from the provided URL.");
        }
        
        return articles;
    }
}