package Old; /**
 * Creates random names based on English letter frequencies
 *
 * @author Adam Townsley
 * @version 7/26/2025
 */
/*
import javax.swing.*;
import java.util.ArrayList;

public class LetterChooser extends JFrame
{
    static JFrame frame;
   
    static src.LetterButton[] buttons;
   
    static JLabel label;
    
    String[][] finalConsonantList;
    
    String[][] finalVowelList;

    public LetterChooser(){}
   
    public void consonantsFrame(){
        frame = new JFrame("Consonant Picker");
         
        buttons = new src.LetterButton[24];

        JButton defaultButton = new JButton("Default");
      
        finalConsonantList = new String[2][24];
         
        label = new JLabel("Chosen consonants:");
      
        JPanel panel = new JPanel();
      
        ArrayList<src.LetterButton> buttonsList = new ArrayList<>();

        defaultButton.addActionListener(e -> {
            for(int i = 0; i < 24; i++){
                boolean inList = false;
                for(src.LetterButton button : buttonsList){
                    inList = button.equals(buttons[i]);
                }
                if(!inList){
                    buttonsList.add(buttons[i]);
                }
            }
        });

        panel.add(defaultButton);

        for (int i = 0; i < 24; i++){
            buttons[i] = new src.LetterButton(src.ConsonantFreq.CONSONANTS[i], src.ConsonantFreq.CONSONANTS_LETTERS[i]);
            src.LetterButton temp = buttons[i];
            buttons[i].getButton().addActionListener(e -> {
                buttonsList.add(temp);
                for(src.LetterButton button : buttonsList){
                    panel.add(button.getButton());
                    SwingUtilities.updateComponentTreeUI(frame);
                }
            });
        }
         
        for (src.LetterButton button : buttons){
            panel.add(button.getButton());
        }
      
        panel.add(label);
      
        frame.add(panel);
      
        frame.setSize(300, 300);
      
        frame.setVisible(true);
      
        while(buttonsList.size() < 24){
            System.out.print(".");
        }
      
        for(int i = 0; i < 24; i++){
            finalConsonantList[0][i] = buttonsList.get(i).getSound();
            finalConsonantList[1][i] = buttonsList.get(i).getLetter();
        }
        
        frame.setVisible(false);
        
        frame.dispose();

        System.out.println();
    }
    
    public void vowelsFrame(){
        frame = new JFrame("Vowel Picker");
         
        buttons = new src.LetterButton[15];
      
        finalVowelList = new String[2][15];
         
        label = new JLabel("Chosen vowels:");
      
        JPanel panel = new JPanel();
      
        ArrayList<src.LetterButton> buttonsList = new ArrayList<>();
       
        for (int i = 0; i < 15; i++){
            buttons[i] = new src.LetterButton(src.VowelFreq.VOWELS[i], src.VowelFreq.VOWELS_LETTERS[i]);
            src.LetterButton temp = buttons[i];
            buttons[i].getButton().addActionListener(e ->{
                buttonsList.add(temp);
                for(src.LetterButton button : buttonsList){
                    panel.add(button.getButton());
                    SwingUtilities.updateComponentTreeUI(frame);
                }
            });
        }
         
        for (src.LetterButton button : buttons){
            panel.add(button.getButton());
        }
      
        panel.add(label);
      
        frame.add(panel);
      
        frame.setSize(300, 300);
      
        frame.setVisible(true);
      
        while(buttonsList.size() < 15){
            System.out.print(".");
        }
      
        for(int i = 0; i < 15; i++){
            finalVowelList[0][i] = buttonsList.get(i).getSound();
            finalVowelList[1][i] = buttonsList.get(i).getLetter();
        }
        
        frame.setVisible(false);
        
        frame.dispose();

        System.out.println();
    }
    
    public String[][] getConsonantList(){
        return finalConsonantList;
    }
    
    public String[][] getVowelList(){
        return finalVowelList;
    }
}
*/