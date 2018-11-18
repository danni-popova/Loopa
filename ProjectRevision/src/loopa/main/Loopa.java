package loopa.main;

import java.util.logging.*;

public class Loopa
{
    private static Logger logger = Logger.getLogger("loopa.main.loopa");

    public static void main(String[] args)
    {
        try
        {
            setLogger();

            WebCrawler webCrawler = new WebCrawler("http://bbc.co.uk");

        } catch (Exception ex)
        {
            System.out.println("Failed to log info due to exception: " + ex.toString());
        }
    }

    private static void setLogger()
    {
        try
        {
            // Set logger output to log.txt
            FileHandler fileHandler = new FileHandler("log.txt");
            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(fileHandler);

            // Set log level to All
            logger.setLevel(Level.ALL);

        } catch (Exception ex)
        {
            System.out.println("Exception occurred when trying to set logger: " + ex.toString());
        }

    }

    public static void log(String logString, Level level)
    {
        try
        {
            logger.log(level, logString);
        }
        catch (Exception ex)
        {
            System.out.println("Failed to log event due to exception: " + ex.toString());
        }
    }

    public static void logException(String logString, Level level, Exception e)
    {
        try
        {
            logger.log(level, logString, e);
        }
        catch (Exception ex)
        {
            System.out.println("Failed to log event due to exception: " + ex.toString());
        }
    }
}
