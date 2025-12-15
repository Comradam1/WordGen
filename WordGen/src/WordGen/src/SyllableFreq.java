package src.WordGen.src;

/**
 * Frequencies of syllable types in the English language as well as frequencies of syllable counts in
 * English words
 *
 * @author Adam Townsley
 * @version 7/27/2025
 */
public class SyllableFreq
{
    public static String[] TYPES = {
        "CVC",
        "VC",
        "CV",
        "CVCC",
        "CCVC",
        "V",
        "VCC",
        "CCV",
        "CCVCC",
        "CVCCC",
        "CCCVC",
        "VCCC",
        "CCVCCC",
        "CCCVCC",
        "CCCV",
        "CVCCCC",
        "CCCVCCC"
    };
    
    public static double[] TYPE_FREQUENCIES = {
        37.43,
        23.02,
        10.2,
        7.96,
        5.7,
        5.51,
        3.5,
        2.46,
        1.57,
        1.22,
        0.48,
        0.42,
        0.18,
        0.17,
        0.13,
        0.01,
        0.01
    };
    
    public static int[] COUNT = {
        2,
        1,
        3,
        4,
        5,
        6,
        7,
        8
    };
    
    public static double[] COUNT_FREQUENCIES = {
        41,
        28.24,
        20.59,
        7.82,
        2.09,
        0.24,
        0.01,
        0.01
    };
}
