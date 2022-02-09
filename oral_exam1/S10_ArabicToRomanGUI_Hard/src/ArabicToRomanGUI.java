import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * extends JFrame allowing GUI use and implementation.
 */
public class ArabicToRomanGUI extends JFrame {
    private final JTextField arabic, roman;
    public static final int[] arabmagnitude = {1000, 900, 500, 400, 100, 90, 50, 40,  10, 9, 5, 4, 1};
    public static final String[] romanmagnitude = {"M", "CM", "D","CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public static final LinkedHashMap<Character, Integer> dict = new LinkedHashMap<Character, Integer>();


    public ArabicToRomanGUI(){
        super("Convert to and from Arabic to Roman");
        setLayout(new FlowLayout());

        JLabel explained1 = new JLabel("Type Arabic numeral from 1-3999");
        add(explained1);

        arabic = new JTextField(20);
        add(arabic);

        JLabel explained2 = new JLabel("Type Roman Numerals from I - MMMCMXCIX");
        add(explained2);

        roman = new JTextField(20);
        add(roman);

        TextHandler handler = new TextHandler();
        arabic.addKeyListener(handler);
        roman.addKeyListener(handler);

    }

    /**
     * Class implements Keylistener to use key based actions such as type, press, and release.
     * Only need KeyReleased otherwise empty string is taken as input.
     */
    private class TextHandler implements KeyListener{

        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            eventChecker(keyEvent); //When key is released check is the string input is too long or invalid

        }
    }

    /**
     * Verify the key pressed after release is valid and if it is perform the convertion operations
     * @param k is a keyEvent which in this case would be the key released
     */
    private void eventChecker(KeyEvent k){
        if(arabic.getText().length() > 4){
            JOptionPane.showMessageDialog(null, "Input too long");
            arabic.setText("");
            roman.setText("");
        }
        for(int i = 0; i < arabic.getText().length(); i++){
            if(!arabic.getText().matches("([0-9])+")){
                JOptionPane.showMessageDialog(null, "Not a valid arabic numeral");
                arabic.setText("");
                roman.setText("");
            }
        }

        if(roman.getText().length() > 9){
            JOptionPane.showMessageDialog(null, "Input too long");
            arabic.setText("");
            roman.setText("");
        }
        for(int i = 0; i< 27; i++){
            if(!roman.getText().matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")){
                JOptionPane.showMessageDialog(null, "Not a valid Roman Numeral Input");
                arabic.setText("");
                roman.setText("");
            }
        }
        if(k.getSource()== arabic){
            roman.setText(fromArabic(Integer.parseInt(arabic.getText())));
        }
        if(k.getSource() == roman){
            arabic.setText(Integer.toString(fromRoman(roman.getText())));

        }
    }

    /**
     * fromArabic converts arabic numerals to Roman numerals
     * @param num number from arabic textfield that is to be converted into Roman numerals
     * @return roman String of numerals that are equivalent to their arabic numerals
     */
    public static String fromArabic(int num){
            int i= 0;
            String romannumeral = "";
            while (num >0){
                if((num % arabmagnitude[i]) != num){
                    romannumeral += romanmagnitude[i];//If match is found concatenate the string
                    num -= arabmagnitude[i]; //move down in magnitude
                }
                else //if it cannot repeat move on to ext value
                    i++;

            }
            return romannumeral;
        }

    /**
     * fromRoman coverts a string of valid Roman numerals to Arabic numerals
     * @param r is a String of valid Roman Numerals
     * @return the arabic numeral equivalent of the Roman numerals
     */
    public int fromRoman(String r){
            setHashMap();
            int arab = 0;
            for (int i = 0; i < r.length()-1; i++) { //go through roman string
                if (dict.get(r.charAt(i)) <dict.get(r.charAt(i+1))) { //if the roman numeral value arabic equivalent is less than the one in front
                    arab -=dict.get(r.charAt(i));                     // reduce the value by that amount
                    }
                else
                    arab += dict.get(r.charAt(i)); //else add it to arab

                }
            arab += dict.get(r.charAt(r.length()-1));
            return arab;
            }

    /**
     * sets the Hashmap values
     */
    public void setHashMap() {
        dict.put('M', 1000);
        dict.put('D', 500);
        dict.put('C', 100);
        dict.put('L', 50);
        dict.put('X', 10);
        dict.put('V', 5);
        dict.put('I', 1);
    }

}

