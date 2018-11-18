package loopa.main;

import java.util.ArrayList;
import java.util.logging.Level;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class WebCrawler
{
    private ArrayList<String> robotsList = new ArrayList<>();

    public WebCrawler(String startingUrl)
    {
        populateRobotsList(startingUrl);
    }

    private void populateRobotsList(String url)
    {
        try
        {
            Loopa.log("Getting robots list for " + url, Level.INFO);

            Document doc = Jsoup.connect(url + "/robots.txt").get();
            Elements bodyOf = doc.select("body");

            Loopa.log(bodyOf.text(), Level.INFO);
        }
        catch (Exception ex)
        {
            Loopa.logException("Failed to get robots for " + url, Level.WARNING, ex);
        }
    }
}
