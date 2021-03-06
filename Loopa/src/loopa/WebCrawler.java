package loopa;

import java.util.ArrayList;
import java.util.Arrays;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * Multi-threaded Web crawler
 * that create a list of visited websites, 
 * starting from given anchor URL
 */
public class WebCrawler
{

    //one list to contain all the robots
    public ArrayList<String> robotsList = new ArrayList<>();

    //queue of websites to be crawled
    public ArrayList<String> listOfPending = new ArrayList<>();

    //collection of websites
    public ArrayList<String> listOfTraversed = new ArrayList<>();

    //websites that return errors
    public ArrayList<String> errorsList = new ArrayList<>();

    //starting URLs from the GUI
    public ArrayList<String> toBeCrawled = new ArrayList<>();

    int counter = 0;

    public WebCrawler(String startingWebsite)
    {
        
    }
    
    public void crawl(String startingURL)
    {
        getRobots(startingURL); //fill the robots list with the forbiden URLs

        listOfPending.add(startingURL);
        //checks if the website allows any robots
        if (!robotsList.contains(startingURL.concat("/")))
        {
            while (!listOfPending.isEmpty() && listOfTraversed.size() <= 100)
            {
                String url = listOfPending.remove(0);
                if (!listOfTraversed.contains(url) && !errorsList.contains(url))
                {
                    System.out.println("Crawling: " + url);
                    listOfTraversed.add(url);
                    for (String s : getSubURLs(url))
                    { //if not already in and not a robots URL, add to queue
                        if (!listOfTraversed.contains(s) && !robotsList.contains(s))
                        {
                            listOfPending.add(s);
                        }
                    }//add the sub URLs to the pending list
                }
            }//the list is full
        }
    }//crawl method

    public ArrayList<String> getSubURLs(String url)
    {
        //using the same algorithm for the method, but with the Jsoup librabry
        ArrayList<String> list = new ArrayList<>();
        try
        {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            for (Element link : links)
            {
                String l = link.attr("abs:href");
                if (l.contains("http://") || l.contains("https://"))
                //checks if it's a valid URL to avoid errors
                {
                    list.add(l);
                }
            }
        } catch (Exception ex)
        {
            errorsList.add(url);
        }
        return list;
    }

    public void getRobots(String url)
    {
        String robotsURL = url.concat("/robots.txt");

        //connect to robots.txt
        try
        {
            Document doc = Jsoup.connect(robotsURL).get();
            Elements bodyOf = doc.select("body");
            String robot = bodyOf.text();
            StringBuilder txt = new StringBuilder();
            
            for (int i = robot.indexOf("User-agent: *"); i < robot.length(); i++)
            {
                txt.append(robot.charAt(i));
            }
            
            String text = txt.toString();
            String[] split = text.split("Disallow: ");
            ArrayList<String> l = new ArrayList<>();
            l.addAll(Arrays.asList(split));
            l.remove(0);
            for (int i = 0; i < l.size(); i++)
            {
                l.set(i, url.concat(l.get(i)));
            }
            robotsList.addAll(l);
        }
        catch (Exception e)
        {
        }
    }
}
