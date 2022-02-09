
import java.util.*;
public class Computus {
    /**
     * Creating instances of the ComputusAlgorithm class and ComputusList to display
     * @author Cameron Fischer
     * @param args
     */
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);

        System.out.print("Please type in a year: ");
        int year = input.nextInt();

        ComputusAlgorithim easter = new ComputusAlgorithim();
        easter.getEasterDate(year);

        System.out.println("Easter: " + easter.getDay() +" " + easter.GetEasterMonth() + " " + year);

        ComputusList EasterFrequency = new ComputusList();
        EasterFrequency.Eastercount();
        EasterFrequency.print();

    }
}
