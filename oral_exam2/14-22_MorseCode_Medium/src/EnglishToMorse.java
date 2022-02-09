import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EnglishToMorse {

    private Map<Character, String> morseEnglishDict = new HashMap<>();

    /**
     * Translation is simply a void method where it takes in a string phrase, in this case an English phrase and
     * prints to the screen the translated english to morse code phrase using the hashmap.
     * @param phrase
     */
    public void translation(String phrase){
        setMorseEnglishDict();
        String[] split = phrase.split(" ");
        for(int i =0; i < split.length; i++){
            for(int j = 0; j < split[i].length(); j ++){
                System.out.print(morseEnglishDict.get(split[i].toUpperCase().charAt(j)) + " ");
            }
        }
    }

    /**
     * No inputs, setMorseEnglishDict puts all hashmap key,values inside the map. With Char, String being this type of
     * map.
     */
    public void setMorseEnglishDict(){
        morseEnglishDict.put('A', ".-");
        morseEnglishDict.put('B', "-...");
        morseEnglishDict.put('C', "-.-.");
        morseEnglishDict.put('D', "-..");
        morseEnglishDict.put('E',".");
        morseEnglishDict.put('F',"..-.");
        morseEnglishDict.put('G',"--.");
        morseEnglishDict.put('H',"....");
        morseEnglishDict.put('I',"..");
        morseEnglishDict.put('J',".---");
        morseEnglishDict.put('K',"-.-");
        morseEnglishDict.put('L',".-..");
        morseEnglishDict.put('M',"--");
        morseEnglishDict.put('N',"-.");
        morseEnglishDict.put('O',"---");
        morseEnglishDict.put('Q',".--.");
        morseEnglishDict.put('P',".--.");
        morseEnglishDict.put('R',".-.");
        morseEnglishDict.put('S',"...");
        morseEnglishDict.put('T',"-");
        morseEnglishDict.put('U',"..-");
        morseEnglishDict.put('V',"...-");
        morseEnglishDict.put('W',".--");
        morseEnglishDict.put('X',"-..-");
        morseEnglishDict.put('Y',"-.--");
        morseEnglishDict.put('Z',"--..");
        morseEnglishDict.put('0',"-----");
        morseEnglishDict.put('1',".----");
        morseEnglishDict.put('2',"..---");
        morseEnglishDict.put('3',"...--");
        morseEnglishDict.put('4',"....-");
        morseEnglishDict.put('5',".....");
        morseEnglishDict.put('6',"-....");
        morseEnglishDict.put('7',"--...");
        morseEnglishDict.put('8',"---..");
        morseEnglishDict.put('9',"----.");


    }
}
