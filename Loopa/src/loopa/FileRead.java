package loopa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileRead
{

    public HashMap<String, Term> fromFile = new HashMap<>();

    public FileRead(String filePath) throws FileNotFoundException, IOException
    {

        FileReader fr = new FileReader(filePath);
        StringBuilder text = new StringBuilder();

        while (fr.read() != -1)
        {

            text.append(fr.read());
        }

    }

    private void buildInvertedFileMap()
    {

    }

}
