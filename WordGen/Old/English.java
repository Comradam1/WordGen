
/**
 * Write a description of class Shaharan here.
 *
 * @author Adam Townsley
 * @version 10/04/2023
 */
public class English
{
    public static void main(){
        final String FILENAME = "C:\\Users\\adamt\\OneDrive\\Desktop\\BlueJ stuff\\WordGen\\words\\english.txt";
        
        //int n;
        //WordCount cnt = new WordCount(FILENAME);
        //n = cnt.count();
        
        System.out.println(generate() + " ");
        System.out.println((int) (Math.random() * 2));
    }
    
    public static String generate(){
        String letters[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","qu","r",
                            "s","t","u","v","w","x","y","z"};
        char letterType[] = {'v','c','c','c','v','c','c','c','v','c','c','c','c','c','v','c','c','c',
                             'c','c','v','c','c','c','c','c'};
        double freq[] = {8.0, 1.3, 2.2, 4.6, 12.4, 2.2, 2.0, 6.5, 6.7, 0.07, 0.7, 3.6, 2.5, 7.0, 7.6, 
                         1.6, 0.1, 6.1, 6.2, 8.9, 2.7, 0.8, 2.3, 0.1, 2.0, 0.03};
        
        String vowels[] = {"a","e","i","o","u"};
        final double V = 2.67379679;
        double vowelFreq[] = {8.0 * V, 12.4 * V, 6.7 * V, 7.6 * V, 2.7 * V};
        
        String consonants[] = {"b","c","d","f","g","h","j","k","l","m","n","p","qu","r","s","t","v",
                               "w","x","y","z"};
        final double C = 1.59744409;
        double consonantFreq[] = {1.3 * C, 2.2 * C, 4.6 * C, 2.2 * C, 2.0 * C, 6.5 * C, 0.07 * C, 
                                  0.7 * C, 3.6 * C, 2.5 * C, 7.0 * C, 1.6 * C, 0.1 * C, 6.1 * C, 6.2 * C, 
                                  8.9 * C, 0.8 * C, 2.3 * C, 0.1 * C, 2.0 * C, 0.03 * C};
                         
        int length = (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1);
        
        String word = "";
        
        char last = 'v';
        char secondLast = 'v';
        
        double n = Math.random() * 100;
        for (int j = 0; j < 26; j++){
            if (n <= freq[j]){
                word = word + letters[j];
                last = letterType[j];
                break;
            }
            else{
                n -= freq[j];
            }
        }
        
        for (int i = 1; i < length; i++){
            n = Math.random() * 100;
            if (last == 'v' && secondLast != 'v'){
                for (int j = 0; j < 26; j++){
                    if (n <= freq[j]){
                        word = word + letters[j];
                        secondLast = last;
                        last = letterType[j];
                        break;
                    }
                    else{
                        n -= freq[j];
                    }
                }
            }
            else if (last == 'c' && secondLast == 'v'){
                for (int j = 0; j < 26; j++){
                    if (n <= freq[j]){
                        word = word + letters[j];
                        secondLast = last;
                        last = letterType[j];
                        break;
                    }
                    else{
                        n -= freq[j];
                    }
                }
            }
            else if (last == 'v' && secondLast == 'v'){
                for (int j = 0; j < 21; j++){
                    if (n <= consonantFreq[j]){
                        word = word + consonants[j];
                        secondLast = last;
                        last = 'c';
                        break;
                    }
                    else{
                        n -= consonantFreq[j];
                    }
                }
            }
            else{
                for (int j = 0; j < 5; j++){
                    if (n <= vowelFreq[j]){
                        word = word + vowels[j];
                        secondLast = last;
                        last = 'v';
                        break;
                    }
                    else{
                        n -= vowelFreq[j];
                    }
                }
            }
        }
        
        return word;
    }
}
