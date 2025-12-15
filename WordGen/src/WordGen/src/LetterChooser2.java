package src.WordGen.src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;/**
 * Prompts user to choose sounds to be omitted from words,
 * order sounds to correspond to the frequency of consonants and vowel sounds in English,
 * and choose consonants that can be doubled up
 *
 * @author Adam Townsley
 * @version 7/27/2025
 */

@SuppressWarnings("serial")
public class LetterChooser2 extends JFrame {

	private static JFrame frame;

    private static LetterButton[] buttons;

    //Stores consonant sounds to be used
    private String[] C_SOUNDS;
    //Stores consonant letters to be used
    private String[] C_LETTERS;
    //Stores vowel sounds to be used
    private String[] V_SOUNDS;
    //Stores vowel letters to be used
    private String[] V_LETTERS;
    //Stores all sounds and letters
    private String[][] afterOmissions;

    /**
     * Creates a src.LetterChooser2 object with consonant and vowel sound and letter lists
     *
     * @param C_SOUNDS List of consonant sounds in order of frequency in English
     * @param C_LETTERS List of letters and letter combinations corresponding to the consonant sounds
     * @param V_SOUNDS List of vowel sounds in order of frequency in English
     * @param V_LETTERS List of letters and letter combinations corresponding to the vowel sounds
     */
    public LetterChooser2(String[] C_SOUNDS, String[] C_LETTERS, String[] V_SOUNDS, String[] V_LETTERS) {
        afterOmissions = omissionsChooserFrame(C_SOUNDS, C_LETTERS, V_SOUNDS, V_LETTERS);

        this.C_SOUNDS = afterOmissions[0];
        this.C_LETTERS = afterOmissions[1];
        this.V_SOUNDS = afterOmissions[2];
        this.V_LETTERS = afterOmissions[3];
    }

    /**
     * Creates a JFrame to prompt user to order consonants or vowels depending on the passed String
     *
     * @param C_OR_V Denotes whether the frame will display consonants or vowels ("C" or "V")
     * @return Re-ordered sound and letter lists together in one 2D array
     */
    public String[][] chooserFrame(String C_OR_V) {
        //Used to store re-ordered sound list in position 0 and re-ordered letter list in position 1
        String[][] finalList;

        //Used to show "Consonant" or "Vowel" on the frame
        String frameString;

        //Pre-ordered list of consonant or vowel sounds
        String[] sounds;

        //Pre-ordered list of consonant or vowel letters
        String[] letters;

        AtomicBoolean confirmed = new AtomicBoolean(false);

        //Sets the frame to either consonants or vowels
        if(C_OR_V.equals("C")){
            frameString = "Consonant";
            sounds = C_SOUNDS;
            letters = C_LETTERS;
        }
        else{
            frameString = "Vowel";
            sounds = V_SOUNDS;
            letters = V_LETTERS;
        }

        //Initializations and panel formatting
        frame = new JFrame(frameString + " Picker");

        buttons = new LetterButton[sounds.length];

        JButton defaultButton = new JButton("Default");
        JButton confirmButton = new JButton("Confirm?");

        finalList = new String[2][sounds.length];

        JLabel label = new JLabel("Chosen " + frameString + "s :");

        JPanel mainPanel = new JPanel();
        JPanel topButtons = new JPanel();
        JPanel centerLabel = new JPanel();
        JPanel bottomButtons = new JPanel();
        JPanel footer = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        ArrayList<LetterButton> buttonsList = new ArrayList<>();

        /*
        Function for the "Default" button:
        Adds the non-chosen sounds to the re-ordered list in their current order
         */
        defaultButton.addActionListener(e -> {
            for (int i = 0; i < sounds.length; i++) {
                boolean inList = false;
                for (LetterButton button : buttonsList) {
                    inList = button.equals(buttons[i]);
                }
                if (!inList) {
                    buttonsList.add(buttons[i]);
                    bottomButtons.add(buttons[i].getButton());
                    SwingUtilities.updateComponentTreeUI(frame);
                }

            }
        });

        /*
        Function for the "Confirm?" button:
        Same as "Default" button, but with an added line to set the confirmed boolean to true
         */
        confirmButton.addActionListener(e -> {
            for (int i = 0; i < sounds.length; i++) {
                boolean inList = false;
                for (LetterButton button : buttonsList) {
                    inList = button.equals(buttons[i]);
                }
                if (!inList) {
                    buttonsList.add(buttons[i]);
                    bottomButtons.add(buttons[i].getButton());
                    SwingUtilities.updateComponentTreeUI(frame);
                }
            }
            frame.setVisible(false);

            frame.dispose();

            confirmed.set(true);
        });

        topButtons.add(defaultButton);
        footer.add(confirmButton);

        /*
        Function for the sound buttons:
        Adds the chosen sound to the end of the re-ordered list
         */
        for (int i = 0; i < sounds.length; i++) {
            buttons[i] = new LetterButton(sounds[i], letters[i]);
            LetterButton temp = buttons[i];
            buttons[i].getButton().addActionListener(e -> {
                buttonsList.add(temp);
                for (LetterButton button : buttonsList) {
                    bottomButtons.add(button.getButton());
                    SwingUtilities.updateComponentTreeUI(frame);
                }
            });
        }

        for (LetterButton button : buttons) {
            topButtons.add(button.getButton());
        }

        centerLabel.add(label);

        //Formats and shows frame
        mainPanel.add(topButtons);
        mainPanel.add(centerLabel);
        mainPanel.add(bottomButtons);
        mainPanel.add(footer);
        
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        frame.add(mainPanel);

        frame.setSize(500, 500);

        frame.setVisible(true);

        //Waits for all sounds to be chosen
        while (true) {
            if(confirmed.get()){
                //Stores the sounds and letters from the re-ordered list of buttons into the finalList array
                for (int i = 0; i < sounds.length; i++) {
                    finalList[0][i] = buttonsList.get(i).getSound();
                    finalList[1][i] = buttonsList.get(i).getLetter();
                }

                System.out.println();

                return finalList;
            }
        }
    }

    /**
     * Creates a JFrame to prompt user to choose consonants that can be doubled up in the generated word
     *
     * @return Array of consonants that can be doubled
     */
    public String[] doublesChooserFrame(){

        //Initializations and panel formatting
        frame = new JFrame("Doubles Picker");
        JPanel mainPanel = new JPanel();
        JPanel topButtons = new JPanel();
        JPanel centerLabel = new JPanel();
        JPanel bottomButtons = new JPanel();
        JPanel footer = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        buttons = new LetterButton[C_SOUNDS.length];

        JButton confirm = new JButton("Confirm?");

        AtomicBoolean confirmed = new AtomicBoolean(false);

        String[] doubles;

        ArrayList<LetterButton> buttonsList = new ArrayList<>();

        /*
        Function for the letter buttons:
        Adds letters to the top list or bottom list
         */
        for(int i = 0; i < C_LETTERS.length; i++){
            buttons[i] = new LetterButton(C_SOUNDS[i], C_LETTERS[i]);
            //Distinguishes between the two "th" sounds (ɵ and ð)
            if(buttons[i].getSound().equals("\u0275")){
                buttons[i].getButton().setText(buttons[i].getLetter() + " (\u0275)");
            }
            else if(buttons[i].getSound().equals("\u00F0")){
                buttons[i].getButton().setText(buttons[i].getLetter() + " (\u00F0)");
            }
            else{
                buttons[i].getButton().setText(buttons[i].getLetter());
            }
            LetterButton temp = buttons[i];
            if(buttons[i].getButton().getActionListeners().length != 0){
                buttons[i].getButton().removeActionListener(buttons[i].getButton().getActionListeners()[0]);
            }

            buttons[i].getButton().addActionListener(e -> {
                buttonsList.add(temp);
                for(LetterButton button : buttonsList){
                    bottomButtons.add(button.getButton());
                    SwingUtilities.updateComponentTreeUI(frame);
                    button.getButton().removeActionListener(button.getButton().getActionListeners()[0]);

                    button.getButton().addActionListener(e1 -> {
                        buttonsList.remove(button);
                        topButtons.add(button.getButton());
                        SwingUtilities.updateComponentTreeUI(frame);
                    });
                }
            });

            topButtons.add(buttons[i].getButton());
        }

        /*
        Function for the "Confirm?" button
        Closes the frame.
         */
        confirm.addActionListener(e -> {
            frame.setVisible(false);

            frame.dispose();

            confirmed.set(true);
        });

        footer.add(confirm);

        centerLabel.add(new JLabel("Chosen Doubles"));

        //Adds all panels to the main panel
        mainPanel.add(topButtons);
        mainPanel.add(centerLabel);
        mainPanel.add(bottomButtons);
        mainPanel.add(footer);
        
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.add(mainPanel);

        frame.setSize(500, 500);

        frame.setVisible(true);

        while(true){
            if(confirmed.get()){
                doubles = new String[buttonsList.size()];

                for(int i = 0; i < doubles.length; i++){
                    doubles[i] = buttonsList.get(i).getLetter();
                }

                return doubles;
            }
        }
    }

    /**
     * Used in constructor, creates a JFrame to prompt user to choose consonant and vowel sounds to omit
     *
     * @param C_SOUNDS List of consonant sounds in English
     * @param C_LETTERS List of letters and letter combinations corresponding to the consonant sounds
     * @param V_SOUNDS List of vowel sounds in English
     * @param V_LETTERS List of letters and letter combinations corresponding to the vowel sounds
     * @return 2D array holding 4 arrays in order of consonant sounds, consonant letters, vowel sounds, and
     * vowel letters without the sounds chosen to be omitted
     */
    private String[][] omissionsChooserFrame(String[] C_SOUNDS, String[] C_LETTERS,
                                                    String[] V_SOUNDS, String[] V_LETTERS){
        //Initializations and panel formatting
        frame = new JFrame("Omissions Picker");
        JPanel mainPanel = new JPanel();
        JPanel topButtons = new JPanel();
        JPanel centerLabel = new JPanel();
        JPanel bottomButtons = new JPanel();
        JPanel footer = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        buttons = new LetterButton[C_SOUNDS.length + V_SOUNDS.length];

        JButton confirm = new JButton("Confirm?");

        AtomicBoolean confirmed = new AtomicBoolean(false);

        String[][] finalLists = new String[4][];

        ArrayList<LetterButton> buttonsList = new ArrayList<>();

        for(int i = 0; i < C_SOUNDS.length; i++){
            buttons[i] = new LetterButton(C_SOUNDS[i], C_LETTERS[i]);
        }
        for(int i = 0; i < V_SOUNDS.length; i++){
            buttons[C_SOUNDS.length + i] = new LetterButton(V_SOUNDS[i], V_LETTERS[i]);
        }
        /*
        Function for the sound buttons:
        Adds sounds to the top list or bottom list
         */
        for(LetterButton button : buttons){
            LetterButton temp = button;

            if(button.getButton().getActionListeners().length != 0){
                button.getButton().removeActionListener(button.getButton().getActionListeners()[0]);
            }
            button.getButton().addActionListener(e -> {
                buttonsList.add(temp);
                for(LetterButton button1 : buttonsList){
                    bottomButtons.add(button.getButton());
                    SwingUtilities.updateComponentTreeUI(frame);
                    button1.getButton().removeActionListener(button1.getButton().getActionListeners()[0]);

                    button1.getButton().addActionListener(e1 -> {
                        buttonsList.remove(button1);
                        topButtons.add(button1.getButton());
                        SwingUtilities.updateComponentTreeUI(frame);
                    });
                }
            });

            topButtons.add(button.getButton());
        }

        /*
        Function for the "Confirm?" button
        Closes the frame.
         */
        confirm.addActionListener(e -> {
            frame.setVisible(false);

            frame.dispose();

            confirmed.set(true);
        });

        centerLabel.add(new Label("Chosen Omissions"));
        footer.add(confirm);

        //Adds all panels to the main panel
        mainPanel.add(topButtons);
        mainPanel.add(centerLabel);
        mainPanel.add(bottomButtons);
        mainPanel.add(footer);
        
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.add(mainPanel);

        frame.setSize(500, 700);

        frame.setVisible(true);

        while(true){
            if(confirmed.get()){
                String[] cSoundsTemp;
                String[] cLettersTemp;
                String[] vSoundsTemp;
                String[] vLettersTemp;

                if(buttonsList.size() == 0) {
                    finalLists[0] = C_SOUNDS;
                    finalLists[1] = C_LETTERS;
                    finalLists[2] = V_SOUNDS;
                    finalLists[3] = V_LETTERS;
                }
                else{
                    ArrayList<LetterButton> cList = new ArrayList<>(Arrays.asList(buttons).subList(
                            0, C_SOUNDS.length));
                    ArrayList<LetterButton> vList = new ArrayList<>(Arrays.asList(buttons).subList(
                            C_SOUNDS.length, V_SOUNDS.length + C_SOUNDS.length));
                    for (LetterButton letterButton : buttonsList) {
                        for (int j = 0; j < C_SOUNDS.length; j++) {
                            if (letterButton.equals(buttons[j])) {
                                cList.remove(buttons[j]);
                            }
                        }
                        for (int j = 0; j < V_SOUNDS.length; j++) {
                            if (letterButton.equals(buttons[C_SOUNDS.length + j])) {
                                vList.remove(buttons[C_SOUNDS.length + j]);
                            }
                        }
                    }
                    cSoundsTemp = new String[cList.size()];
                    cLettersTemp = new String[cList.size()];

                    for(int i = 0; i < cList.size(); i++){
                        cSoundsTemp[i] = cList.get(i).getSound();
                        cLettersTemp[i] = cList.get(i).getLetter();
                    }

                    vSoundsTemp = new String[vList.size()];
                    vLettersTemp = new String[vList.size()];

                    for(int i = 0; i < vList.size(); i++){
                        vSoundsTemp[i] = vList.get(i).getSound();
                        vLettersTemp[i] = vList.get(i).getLetter();
                    }

                    finalLists[0] = cSoundsTemp;
                    finalLists[1] = cLettersTemp;
                    finalLists[2] = vSoundsTemp;
                    finalLists[3] = vLettersTemp;
                }

                return finalLists;
            }
        }
    }

    /**
     * Prompts user to enter a custom filepath for the text document that the words will be printed to
     *
     * @param prevFilepath The last custom filepath that was used.
     * @return The entered filepath if anything was entered, an empty String otherwise
     */
    public String getFilepath(String prevFilepath){
        frame = new JFrame("Please enter a filepath for the namesbase");

        JTextField textField = new JTextField(prevFilepath, 30);

        JPanel mainPanel = new JPanel();
        JPanel topText = new JPanel();
        JPanel footer = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        topText.add(textField);

        JButton confirm = new JButton("Confirm?");

        AtomicBoolean confirmed = new AtomicBoolean(false);

        confirm.addActionListener(e -> {
            frame.setVisible(false);

            frame.dispose();

            confirmed.set(true);
        });

        footer.add(confirm);

        mainPanel.add(topText);
        mainPanel.add(footer);
        
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.add(mainPanel);

        frame.setSize(500, 200);

        frame.setVisible(true);

        while(true){
            if(confirmed.get()){
                try {
                    return textField.getText();
                }catch(NullPointerException n){
                    return "";
                }
            }
        }
    }
}