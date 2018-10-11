package loopa;

import loopa.gui.LoopaInterface;
import java.awt.Desktop;
import java.net.URL;

public class Loopa
{

    public static void main(String args[])
    {
        // Start web crawler before launching the application
        WebCrawler webCrawler = new WebCrawler("http://bbc.co.uk");
        
        // Create term map 
        //InvertedBuilder.createMap(WebCrawler.listOfTraversed);
        
        // Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new LoopaInterface().setVisible(true);
            }
        });

    }//main 

    //open web page in browser for a given String (URL)
    public static void openWebpage(String urlString)
    {
        try
        {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}//class
