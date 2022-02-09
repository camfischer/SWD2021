import java.util.*;

public class MorseCodeMain {
    /**
     * Main method runs the program, creating the scanner and prompting the user to choose between either morse to english
     * or english to morse.
     * @param args
     */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("1. Morse to English");
        System.out.println("2. English to Morse");
        int num = input.nextInt();
        input.nextLine();

        if(num == 1){

            System.out.println("Phrase to translate");
            String phrase = input.nextLine();
            MorseToEnglish convert = new MorseToEnglish();
            convert.translation(phrase);
        }
        else if(num ==2){
            System.out.println("Phrase to translate");
            String phrase = input.nextLine();
            EnglishToMorse convert = new EnglishToMorse();
            convert.translation(phrase);

        }
        else{
            System.out.println("Invalid Entry");
        }
    }
}
