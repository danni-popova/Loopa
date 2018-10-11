/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loopa;

import java.util.ArrayList;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author up782716
 */
public class InvertedBuilder
{

    public static HashMap<String, Term> mapIB = new HashMap<>();

    public static void createMap(ArrayList<String> collection)
    {

        //change to for(String url : WebCrawler.listOfTraversedURLs)
        for (String url : collection) //for each url in the url set
        {
            try
            {
                Document doc = Jsoup.connect(url).get();
                Elements body = doc.select("body");
                String textFromPage = body.text();

                TextAnalyser ta = new TextAnalyser(textFromPage);

                for (String word : ta.termsList) //for each term in the url
                {
                    if (!mapIB.containsKey(word)) //if the term isn't already in the map
                    {
                        mapIB.put(word, new Term(url, (double) 1 / ta.termsList.size())); //put the term with value url and 1
                    } else
                    {
                        if (!mapIB.get(word).termMap.containsKey(url))
                        {
                            mapIB.get(word).termMap.put(url, (double) 1 / ta.termsList.size());
                        } else
                        {
                            mapIB.get(word).changeFrequency(url, ta.termsList.size());
                        }
                    }
                }   //inner for loop

            } catch (Exception ex)    //urls.remove(url);
            {
            }
        }//outer for loop
    }//inverted File builder method

}
