package src.WordGen.src;

import javax.swing.*;

/**
 * A LetterButton holds a JButton object as well as a string for a consonant/vowel sound and the
 * corresponding letters
 */
public class LetterButton {
    private JButton button;
    private String sound;
    private String letter;

    /**
     * Creates a src.LetterButton object from a sound string and a letter string
     *
     * @param sound Consonant or vowel sound
     * @param letter Letter or letters that correspond to the sound
     */
    public LetterButton(String sound, String letter) {
        this.sound = sound;
        this.letter = letter;
        button = new JButton(sound);
    }

    /**
     * Gets the JButton
     *
     * @return The src.LetterButton's JButton object
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Gets the vowel/consonant sound
     *
     * @return The src.LetterButton's sound
     */
    public String getSound() {
        return sound;
    }

    /**
     * Gets the letter/letters
     *
     * @return The src.LetterButton's letter or letters
     */
    public String getLetter() {
        return letter;
    }
}
