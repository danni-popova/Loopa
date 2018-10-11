package loopa;

import java.util.HashMap;

public class Term
{

    public HashMap<String, Double> termMap = new HashMap<>();

    public Term(String url, double frequency)
    {
        termMap.put(url, frequency);
    }

    public boolean checkURL(String url)
    {

        if (termMap.containsKey(url))
        {
            return true;
        } else
        {
            return false;
        }

    }

    public void changeFrequency(String url, int l)
    {

        double freq = termMap.get(url);
        freq = (freq + 1) / l;
        termMap.put(url, freq);
    }

}
