import javax.swing.*;
import javax.swing.JFrame;

public class BlackjackClientTest {
    /**
     * Sets localhost as server
     * @param args
     */
    public static void main(String[] args){
        BlackjackClient application;

        if(args.length == 0) {
            application = new BlackjackClient("127.0.0.1"); // localhost
        }
      else
          application = new BlackjackClient(args[0]); // use args

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
