import javax.swing.*;
import java.awt.event.*;

/**
 * Class that uses the ArabicToRomanGUI
 * @author Cameron Fischer
 */
public class ArabicToRomanMain {
    public static void main(String[] args){
        ArabicToRomanGUI launch = new ArabicToRomanGUI();
        launch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launch.setSize(400, 150);

        launch.setResizable(false);
        launch.setVisible(true);
    }
}
