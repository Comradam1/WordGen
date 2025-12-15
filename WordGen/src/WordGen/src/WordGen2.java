package src.WordGen.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Generates names based on chosen vowel and consonant frequencies as well
 * as English syllable structure
 *<p></p>
 * Uses frequency tables listed in following study for reference
 *<p></p>
 * Gilner, Leah and Franc, Morales. (2010).
 * Functional load: transcription and analysis of the 10,000 most frequent words in spoken English.
 * 3. 10.5750/bjll.v3i0.27.
 * <a href="https://www.researchgate.net/figure/Frequency-of-occurrence-of-consonants_tbl5_277825691">...</a>
 *
 * @author Adam Townsley
 * @version 7/27/2025
 */

public class WordGen2
{    
    public static void main(String[] args){

        //Finds sounds to be omitted
        LetterChooser2 window = new LetterChooser2(ConsonantFreq.CONSONANTS, ConsonantFreq.CONSONANTS_LETTERS,
                VowelFreq.VOWELS, VowelFreq.VOWELS_LETTERS);

        //Finds which consonant sounds should be most frequent
        String[][] consonants = window.chooserFrame("C");

        //Finds which vowel sounds should be most frequent
        String[][] vowels = window.chooserFrame("V");

        //Finds which consonants can be doubled up
        String[] doubles = window.doublesChooserFrame();

        //Finds where the text files should be stored
        String filepath = window.getFilepath(getPrevPath());

        //Stores the generated word pronunciations in [0] and word spellings in [1]
        String word[];

        //Stores the File for pronunciations in [0] and spellings in [1]
        File[] files = createFile(filepath);

        //File for the previous filepath to be stored in
        File fileForPath = new File("prevFilePath.txt");

        //Declarations and initializations
        FileWriter writer = null;
        FileWriter writer2 = null;
        FileWriter writer3;
        boolean cont = true;

        //Assigns writers to their files and writes the custom filepath into prevFilePath.txt if one was entered
        try {
            writer = new FileWriter(files[0], Charset.forName("UTF-8"));
            writer2 = new FileWriter(files[1], Charset.forName("UTF-8"));
            writer3 = new FileWriter(fileForPath);

            writer3.write(filepath);
            writer3.close();
        }catch(IOException e){
            System.out.println("Error creating writers");
            cont = false;
        }

        //Generates words if writers were successfully created and writes them into the respective Files
        if(cont) {
            try {
                for (int i = 0; i < 300; i++) {
                    word = genWord(getWordComp(), consonants, vowels, doubles);
                    writer.write(word[0] + ",");
                    writer2.write(word[1] + ",");
                }
                writer.close();
                writer2.close();
            }catch(IOException e){
                System.out.println("Error writing to files");
            }
        }
    }

    /**
     * Randomly determines consonant and vowel composition of a word based on the frequencies of syllable
     * types found in the study or src.SyllableFreq.java
     *
     * @return A string composed of 'C's and 'V's to represent consonants and vowels
     */
    private static String getWordComp(){
        //Random number to be compared to frequencies of syllable counts
        double syllableNumRand = Math.random() *
                findModifier(SyllableFreq.COUNT.length, SyllableFreq.COUNT_FREQUENCIES);
        //Number of syllables in the word
        int syllableNum = 0;
        //Current frequency threshold being compared to
        double threshold = 0;

        for(int i = 0; i < SyllableFreq.COUNT.length; i++){
            //Increments the threshold by the next sound's frequency percentage
            threshold += SyllableFreq.COUNT_FREQUENCIES[i];

            if(syllableNumRand <= threshold){
                //If threshold is met, sets number of syllables to the count at the current list index
                syllableNum = SyllableFreq.COUNT[i];
                break;
            }
        }

        //Random number to be compared to frequencies of syllable types
        double syllableTypeRand;
        //Used to store final word composition
        String wordComp = "";
        
        for(int i = 0; i < syllableNum; i++){
            //Reset random value and threshold until number of syllables is met
            syllableTypeRand = Math.random() *
                    findModifier(SyllableFreq.TYPES.length, SyllableFreq.TYPE_FREQUENCIES);
            //Reset random value and threshold until number of syllables is met
            threshold = 0;

            for (int j = 0; j < SyllableFreq.TYPES.length; j++){
                //Increments threshold
                threshold += SyllableFreq.TYPE_FREQUENCIES[j];

                if(syllableTypeRand <= threshold){
                    //If threshold is met, adds syllable type to the end of the wordComp string
                    wordComp = wordComp.concat(SyllableFreq.TYPES[j]);
                    break;
                }
            }
        }
        
        return wordComp;
    }

    /**
     * Randomly generates a word based on a passed word composition, and passed consonant and vowel
     * frequency lists
     *
     * @param wordComp Vowel and consonant composition in the form of a string (e.g. "CVCCVCC")
     * @param consonants Ordered list of consonants that corresponds to the frequency list in src.ConsonantFreq.java
     * @param vowels Ordered list of vowels that corresponds to the frequency list in src.VowelFreq.java
     * @return A randomly generated word
     */
    private static String[] genWord(String wordComp, String[][] consonants, String[][] vowels,
                                    String[] doubles){
        //Used to store final IPA pronunciation of word
        String generatedWordPronunciation = "";
        //Used to store final English spelling of word
        String generatedWord = "";
        //Used to store both generated words in one array, pronunciation in [0] and spelling in [1]
        String[] words;

        //Current frequency threshold being compared to
        int threshold;
        //Random number to be compared to frequencies of consonant sounds
        double consonantRand;
        //Random number to be compared to frequencies of vowel sounds
        double vowelRand;
        //True if an "e" should be added after the next consonant
        boolean e = false;
        //Set to false if the chosen consonant is acceptable, reset to true on the next iteration of the for loop
        boolean retry;
        //Holds the previously chosen consonant letter, used for checking doubles
        String prevLetter = "";

        for(int i = 0; i < wordComp.length(); i++){
            //Reset threshold
            threshold = 0;
            if(wordComp.charAt(i) == 'C') {
                retry = true;
                while (retry) {
                    //Set random target number
                    consonantRand = Math.random() * findModifier(consonants[0].length, ConsonantFreq.FREQUENCIES);
                    for (int j = 0; j < consonants[0].length; j++) {
                        //Increment threshold
                        threshold += ConsonantFreq.FREQUENCIES[j];

                        if (consonantRand <= threshold) {
                            if (i < 1 || checkDoubles(consonants[1][j], prevLetter, doubles)) {
                            /*
                            If threshold is met and the consonant is not a double or is an acceptable double,
                            add current consonant to pronunciation and spelling
                             */
                                generatedWordPronunciation = generatedWordPronunciation.concat(consonants[0][j]);
                                generatedWord = generatedWord.concat(consonants[1][j]);
                                prevLetter = consonants[0][j];
                                retry = false;
                                break;
                            }
                        }
                    }
                }
            }
            else{
                //Set random target number
                vowelRand = Math.random() * findModifier(vowels[0].length, VowelFreq.FREQUENCIES);
                //Reset e if there is another vowel in the word
                e = false;
                
                for(int j = 0; j < vowels[0].length; j++){
                    //Increment threshold
                    threshold += VowelFreq.FREQUENCIES[j];
                    
                    if(vowelRand <= threshold){
                        //If threshold is met, add current vowel to pronunciation and spelling
                        generatedWordPronunciation = generatedWordPronunciation.concat(vowels[0][j]);
                        generatedWord = generatedWord.concat(vowels[1][j]);

                        //Determines if a silent "e" should be added at the end of the word
                        if(vowels[0][j].equals("e\u026A") || vowels[0][j].equals("a\u026A") ||
                                vowels[0][j].equals("\u0254")){
                            e = true;
                        }
                        break;
                    }
                }
                //Resets prevLetter because it is no longer a consonant
                prevLetter = "";
            }
        }

        //Adds an "e" to the end of the word's spelling if the last vowel sound is eɪ, aɪ, or ɔ
        if(e){
            generatedWord = generatedWord + "e";
        }

        words = new String[]{generatedWordPronunciation, generatedWord};

        return words;
    }

    /**
     * Checks if the two consonants are the same and, if so, checks if that consonant is allowed to be doubled
     *
     * @param consonant String holding the current consonant
     * @param prevConsonant String holding the previous consonant
     * @param doubles String array holding letters that can be doubled
     * @return True if letters can be doubled or if they are not the same, false otherwise
     */
    private static boolean checkDoubles(String consonant, String prevConsonant, String[]doubles){
        if(!consonant.equals(prevConsonant)){
            return true;
        }
        for(String letter : doubles){
            if(consonant.equals(letter)){
                return true;
            }
        }
        return false;
    }

    /**
     * Finds what modifier should be used for the Math.random() call by adding together the frequencies
     * of the letters or syllables being used
     *
     * @param n number of items in the chosen list
     * @param freq frequencies of all items of the type being used
     * @return Sum of all the frequencies being used
     */
    private static double findModifier(int n, double[] freq){
        double total = 0;

        for(int i = 0; i < n; i++){
            total += freq[i];
        }

        return total;
    }

    /**
     * Creates two files, one to store the generated words, and one to store their pronunciations
     *
     * @param path Filepath, defaults to WordGen\words\ if empty
     * @return Array of 2 Files, one for words and one for pronunciations
     */
    private static File[] createFile(String path){
        //Used to increment File names
        int i = -1;

        try{
            //File names and default path
            String fileName = "\\namesbase";
            String fileName2 = "\\namesbasePronunciations";
            String defaultPath = "words";

            //Array to store the two Files once created
            File files[] = new File[2];

            //Used to reiterate do-while loops
            boolean check;

            //Checks if there is a custom filepath
            if(path.equals("")) {
                //Increments the number in the spellings File name if the current name is in use
                do{
                    i++;
                    File checkFile = new File(defaultPath + fileName + i + ".txt");
                    check = checkFile.isFile();
                } while(check);

                //Increments the number in the pronunciations File name if the current name is in use
                do{
                    i++;
                    File checkFile = new File(defaultPath + fileName2 + i + ".txt");
                    check = checkFile.isFile();
                } while(check);

                //Initializes Files with proper names
                files[0] = new File(defaultPath + fileName + i + ".txt");
                files[1] = new File(defaultPath + fileName2 + i + ".txt");
            }
            else{
                //Increments the number in the spellings File name if the current name is in use
                do{
                    i++;
                    File checkFile = new File(path + fileName + i + ".txt");
                    check = checkFile.isFile();
                } while(check);

                //Increments the number in the pronunciations File name if the current name is in use
                do{
                    i++;
                    File checkFile = new File(path + fileName2 + i + ".txt");
                    check = checkFile.isFile();
                } while(check);

                //Initializes Files with proper names
                files[1] = new File(path + fileName + i + ".txt");
                files[0] = new File(path + fileName2 + i + ".txt");
            }

            //Prints to console if File creation was successful
            if(files[0].createNewFile() && files[1].createNewFile()){
                System.out.println(fileName + i + " created!");
            }
            else{
                System.out.println("Error creating files");
            }
            return files;

        }catch(IOException e){
            System.out.println("Error with path name");
            return null;
        }
    }

    /**
     * Reads the prevFilePath.txt File to determine if a custom filepath was used when the program was run last
     *
     * @return Contents of the prevFilePath.txt File
     */
    private static String getPrevPath(){
        try {
            //Initializations
            File file = new File("prevFilePath.txt");
            Scanner myReader = new Scanner(file);

            //Returns previous filepath if the File is not empty
            if(myReader.hasNextLine()) {
                String filepath = myReader.nextLine();
                myReader.close();
                return filepath;
            }
            myReader.close();
        } catch (FileNotFoundException f) {
            System.out.println("No previous filepath found");
        }
        //Returns empty String if the File is empty or if no File was found
        return "";
    }
}
