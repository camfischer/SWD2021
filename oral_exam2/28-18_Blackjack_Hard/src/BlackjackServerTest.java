import javax.swing.*;

public class BlackjackServerTest {
    /**
     * Executes GUI
     * @param args
     */
    public static void main(String[] args)
    {
        BlackjackServer application = new BlackjackServer();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.execute();
    }
}
