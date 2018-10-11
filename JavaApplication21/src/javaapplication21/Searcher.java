
package javaapplication21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.lang.Double;
import java.util.Collections;

public class Searcher {
    
    public static String source = "all";
    public String result1;
    public String result2;
    public String result3;
    public String result4;
    public String result5;
   
    public  ArrayList <String> results = new ArrayList<>();
    public HashMap<String, Double> resultsMap = new HashMap<>();
    
    
    public Searcher (String userQuery, HashMap<String, Term> i)
    {
        
        getResults(userQuery, i);
        
     
    }
    
    private void getResults (String userQuery, HashMap<String, Term> map)
    {
    
        TextAnalyser q = new TextAnalyser(userQuery);
        
        System.out.println("Terms from query: " + q.termsList);
        
        ArrayList<String> urls = new ArrayList<>();
        
        for (String term : q.termsList)
        {if(map.containsKey(term)){
            
            if(map.get(term).termMap.keySet().size()>0){
                
            urls.addAll(map.get(term).termMap.keySet());
            
            for (String u : urls)
            {
                
            
                if(resultsMap.containsKey(u))
                {
                    double weight = resultsMap.get(u);
                resultsMap.put(u, map.get(term).termMap.get(u) + weight);
                }
                else 
                {
                resultsMap.put(u, map.get(term).termMap.get(u));
                }    
            }
            }
        
        }
        }
        System.out.println("The results are contained in the following urls: " + urls);
        if(urls.size()>5){
        ArrayList<Double> values = new ArrayList<>();
        String [] value ;
        values.addAll(resultsMap.values());
        
        //find the best match
        Collections.sort(values);
        Collections.reverse(values);
        System.out.println(values);
        
        
        //find the keys for the highest 5 values: 
        
        for (String key: resultsMap.keySet())
        {        
        if(values.get(0).equals(resultsMap.get(key)))
            {
            result1 = key; 
            }
        }

    for (String key: resultsMap.keySet())
        {
        
        if(values.get(1).equals(resultsMap.get(key)))
            {
            result2 = key; 
            }
        }
    
    for (String key: resultsMap.keySet())
        {
        
        if(values.get(2).equals(resultsMap.get(key)))
            {
            result3 = key; 
            }
            
        }
        
       for (String key: resultsMap.keySet())
        {
        
        if(values.get(3).equals(resultsMap.get(key)))
            {
            result4 = key; 
            }
            
        }
       
       for (String key: resultsMap.keySet())
        {
        
        if(values.get(4).equals(resultsMap.get(key)))
            {
            result5 = key; 
            }
            
        }
        
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
    
        }
        else
        {
        
            result1 = "no results";
            result2 = "no results";
            result3 = "no results";
            result4 = "no results";
            result5 = "no results";
            
        }
    }
}
        
        
        
        
                                    
     



