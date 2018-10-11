/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loopa;

import java.io.File;
import static java.lang.Math.log;
import java.util.ArrayList;

public class FileWrite {
    
    

    public FileWrite(InvertedBuilder i) //constructor
    {
        
        writeFile(createString(i));
        
    }
    
    private String createString(InvertedBuilder t)
    {
       
        String stringToWrite;    // example:" cat:wiki*4,port*5);dog:wiki(1),cnn(3); "
        
        StringBuilder txt = new StringBuilder(); //to contain our temporary string
        
        ArrayList<String> terms = new ArrayList<>();
        
        terms.addAll(t.mapIB.keySet()); //add all the terms from the map
        
        for (String term : terms)
        {
        
        txt.append(term);
        txt.append(":");
        
        ArrayList<String> urls = new ArrayList<>();
        urls.addAll(t.mapIB.get(term).termMap.keySet());
        
            for (String url : urls )
            {
            txt.append(url);
            txt.append("*");
            txt.append(calculateWeight(urls.size()));
            txt.append(",");
            }
        txt.append(";");
        }
        stringToWrite = txt.toString();
        return stringToWrite;
        
    }
    
    private double calculateWeight(int collectionLength)
    {
    
    double weight;
    
    weight = idf(collectionLength,4);
        
    return weight;
        
    }
    
    private double idf(double cl, double tcl)
    {                //collection length and term collection length
    
        double idfScore = log(cl/tcl);
    
        return idfScore;
    }
    
    
    private void writeFile(String s)
    {
        
    }

}
