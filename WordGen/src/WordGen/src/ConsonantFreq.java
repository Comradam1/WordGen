package src.WordGen.src;

/**
 * List of frequencies of consonant sounds in the English language
 *
 * @author Adam Townsley
 * @version 7/26/2025
 */
public class ConsonantFreq
{
    /*
    Consonant sounds not found in the study but are present in the English language (or are present
    in other languages that I wanted) are given a frequency equal to half of the last frequency in the study

    While this increases the total past 100%, the findModifier() method in src.WordGen2 will account for the new
    total.
     */
    public static double[] FREQUENCIES = {
        12.89,
        10.62,
        10.35,
        9.86,
        7.84,
        7.2,
        6.67,
        4.9,
        4.87,
        4.45,
        2.74,
        2.54,
        2.53,
        2.17,
        2.1,
        1.6,
        1.5,
        1.4,
        0.99,
        0.96,
        0.92,
        0.57,
        0.17,
        0.15,
        0.075,
        0.075
    };
    
    public static String[] CONSONANTS = {
        "r",
        "t",
        "n",
        "s",
        "l",
        "k",
        "d",
        "z",
        "p",
        "m",
        "b",
        "\u014B",
        "f",
        "v",
        "\u0283",
        "g",
        "w",
        "\u02A4",
        "h",
        "t\u0283",
        "j",
        "\u0275",
        "\u00F0",
        "\u0292",
        "x",
        "\u0263"
    };

    public static String[] CONSONANTS_LETTERS = {
        "r",
        "t",
        "n",
        "s",
        "l",
        "k",
        "d",
        "z",
        "p",
        "m",
        "b",
        "ng",
        "f",
        "v",
        "sh",
        "g",
        "w",
        "j",
        "h",
        "ch",
        "y",
        "th",
        "th",
        "zh",
        "kh",
        "gh"
    };
}
