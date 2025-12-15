import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Counts the number of lines in a .txt document
 *
 * @author Adam Townsley
 * @version 01/03/2023
 */

public class WordCount
{
    String FILE;
    
    public WordCount(String FILENAME){
        FILE = FILENAME;
    }
    
    public int count(){
        int n = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String strCurrentLine;
            
            while ((strCurrentLine = reader.readLine()) != null){
                n++;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        
        return n;
    }
}
