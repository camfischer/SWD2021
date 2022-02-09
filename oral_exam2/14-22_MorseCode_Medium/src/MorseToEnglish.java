import java.util.*;

public class MorseToEnglish {
    private Map<String, Character> morseEnglishDict = new HashMap<>();

    /**
     * Translation is simply a void method where it takes in a string phrase, in this case a morse code phrase and splits
     * the string into an array which will the match the correct key,value from the hashmap and is then printed to screen.
     * @param phrase
     */
    public void translation(String phrase){
        setMorseEnglishDict();
        String[] split = phrase.split(" ");
        for(int i =0; i < split.length; i++){
            System.out.print(morseEnglishDict.get(split[i]));
        }
    }

    /**
     * Populates the hashmap, same as EnglishToMore but reverse as String, char -> Key, Value.
     */
    public void setMorseEnglishDict(){
        morseEnglishDict.put(".-", 'A');
        morseEnglishDict.put("-...", 'B');
        morseEnglishDict.put("-.-.", 'C');
        morseEnglishDict.put("-..", 'D');
        morseEnglishDict.put(".",'E');
        morseEnglishDict.put("..-.", 'F');
        morseEnglishDict.put("--.", 'G');
        morseEnglishDict.put("....", 'H');
        morseEnglishDict.put("..", 'I');
        morseEnglishDict.put(".---", 'J');
        morseEnglishDict.put("-.-", 'K');
        morseEnglishDict.put(".-..", 'L');
        morseEnglishDict.put("--", 'M');
        morseEnglishDict.put("-.", 'N');
        morseEnglishDict.put("---", 'O');
        morseEnglishDict.put(".--.", 'P');
        morseEnglishDict.put("--.-", 'W');
        morseEnglishDict.put(".-.", 'R');
        morseEnglishDict.put("...", 'S');
        morseEnglishDict.put("-", 'T');
        morseEnglishDict.put("..-", 'U');
        morseEnglishDict.put("...-", 'V');
        morseEnglishDict.put(".--", 'W');
        morseEnglishDict.put("-..-", 'X');
        morseEnglishDict.put("-.--", 'Y');
        morseEnglishDict.put("--..", 'Z');
        morseEnglishDict.put("-----", '0');
        morseEnglishDict.put(".----", '1');
        morseEnglishDict.put("..---", '2');
        morseEnglishDict.put("...--", '3');
        morseEnglishDict.put("....-", '4');
        morseEnglishDict.put(".....", '5');
        morseEnglishDict.put(" -....", '6');
        morseEnglishDict.put("--...", '7');
        morseEnglishDict.put("---..", '8');
        morseEnglishDict.put("----.", '9');


    }
}
