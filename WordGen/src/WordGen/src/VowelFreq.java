package src.WordGen.src;

/**
 * List of frequencies of vowel sounds in the English language
 *
 * @author Adam Townsley
 * @version 7/27/2025
 */
public class VowelFreq
{
    /*
    Vowel sounds not found in the study but are present in the English language are given a frequency equal to
    half of the last frequency in the study

    While this increases the total past 100%, the findModifier() method in src.WordGen2 will account for the new
    total.
     */
    public static double[] FREQUENCIES = {
        21.47,
        21,
        10.18,
        10.05,
        6.23,
        5.47,
        5.11,
        4.49,
        4.29,
        3.44,
        3.39,
        3.08,
        0.94,
        0.47,
        0.39,
        0.195,
        0.195,
        0.195,
        0.195,
        0.195
    };
    
    public static String[] VOWELS = {
        "\u0259",
        "\u026A",
        "\u025B",
        "i",
        "\u00E6",
        "e\u026A",  //aCe
        "\u028C",
        "\u0251",
        "a\u026A",  //iCe
        "\u0254",   //oCe
        "o\u028A",
        "u",
        "a\u028A",
        "\u0254\u026A",
        "\u028A",
        "e",            //
        "\u0252",       //
        "\u026A\u0259", //
        "e\u0259",      //
        "\u028A\u0259"  //
    };
    
    public static String[] VOWELS_LETTERS = {
        "a",
        "i",
        "e",
        "i",
        "a",
        "a",
        "u",
        "aa",
        "i",
        "o",
        "o",
        "oo",
        "ao",
        "oi",
        "u",
        "e",
        "a",
        "ea",
        "eh",
        "ou"
    };
}
