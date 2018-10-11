
package javaapplication21;

import java.util.ArrayList;
import java.util.Arrays;

public class TextAnalyser {
    //stores the terms extracted from the string
   public ArrayList<String> termsList = new ArrayList<>();
   
   public static boolean useStopWords = true;
   public static boolean useNumbers = true;
   
   public String textFromPage; 
   
   public static String [] allStopwords  = {"a" , "about" , "above","after","again","againstall","am","anand","any","are","aren't","as","at","be","because","been","before","being","below","between","both","but","bycan't","cannot",
   "could","couldn't","did","didn't","do","does","doesn't","doing","don't","down","during","each","few","for","from","further","had","hadn't","has","hasn't","have","haven't","having","he","he'd","he'll","he's","her","here","here's",
   "hers","herself","him","himself","his","how","how's","i","i'd","i'll","i'm","i've","if","in","into","is","isn't","it","it's","its","itself","let's","me","more","most","mustn't","my","myself","no","nor","not","of",
   "off","on","once","only","or","other","ought","our","ours","ourselves","out","over","own","same","shan't","she","she'd","she'll","she's","should","shouldn't","so","some","such","than","that","that's","the","their",
   "theirs","them","themselves","then","there","there's","these","they","they'd","they'll","they're","they've","this","those","through","to","too","under","until","up","very","was","wasn't","we","we'd","we'll","we're","we've","were",
   "weren't","what","what's","when","when's","where","where's","which","while","who","who's","whom","why","why's","with","won't","would","wouldn't","you","you'd","you'll","you're","you've","your",
   "yours","yourself","yourselves"};
   
   public static String [] fewStopwords = { "a" , "and" , "are" , "be" , "if" , "in" , "is" , "it" , "of" , "on" , "or" , "so" , "the" , "they" , "there" , "this"};
   
     public TextAnalyser(String text) 
        {
            textFromPage = text;
            //System.out.println("Text from page received" + text);
            analyse(textFromPage);
        }

    private void analyse (String text)
    {
    
    char [] delimiter = {',' , '.' , '?' , '<' , '!' , '>', '-', ')' , '(', '\t', '\n', ':' , '[', ']', };
    
    
    ArrayList<String> stopWords = new ArrayList<>();
     
    if(useStopWords){
    stopWords.addAll(Arrays.asList(fewStopwords));
    }
    else 
    stopWords.addAll(Arrays.asList(allStopwords));
//replace all deimiters with white spaces
    
    for (int i = 0; i<delimiter.length; i++)
    {
     text = text.replace(delimiter[i], ' ');
    }
    
   // System.out.println("Delimiters replaced: " + text);

    
    //exclude numbers if the option is selected
    if(!useNumbers)
    {
    
        text = text.replaceAll("\\d"," ");
    
    }
    
//trim all the whitespaces
    
    text = text.trim().replaceAll("\\s+", " ");
    
    
    
   // System.out.println("White spaces replaced: " + text);

//make the text lower case    
    
    text = text.toLowerCase();
    
    //System.out.println("Lower case-d: " + text);
//split into seperate words    

    String[] wordsList = text.split(" ");

   // System.out.println("Words split: " + wordsList);
    
//add all words to one list

    termsList.addAll(Arrays.asList(wordsList));
    
   // System.out.println("Added to terms List: " + termsList);
//remove the stop words from the list
    
   
    
    termsList.removeAll(stopWords);
    
   // System.out.println("Removed stopwords " + termsList);
    
//Porter's algorithm, stemm words
    
    for (int i = 0; i<termsList.size(); i++)
    {
    Porter stem = new Porter(termsList.get(i));
    termsList.set(i, stem.getStem());
    }
    
    //System.out.println("Stemmed: " + termsList);
    
    //check if the url hasn't returned any terms
    
    if(termsList.isEmpty())
    termsList.add("none");
    
    
    }



}