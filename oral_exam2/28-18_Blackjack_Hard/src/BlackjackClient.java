import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import javax.swing.*;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
public class BlackjackClient extends JFrame implements Runnable {

    private JTextField idField; // textfield to display player's mark
    private String player1 = "1";
    private String player2 = "2";
    private JTextArea displayArea; // JTextArea to display output
    private JButton hit;
    private JButton stay;
    private JPanel buttons;
    private JPanel holdButtons;
    private Socket connection; // connection to server
    private Scanner input; // input from server
    private Formatter output; // output to server
    private final String blackjackHost; // host name for server
    private String myNum; // this client's mark
    private boolean myTurn; // determines which client's turn it is
    private Socket client;

    /**
     * Takes in the host of the server to connect to then sets up the GUI.
     * @param host
     */
    public BlackjackClient(String host){
        super("Client");

        blackjackHost = host;

        Color colorBackground = new Color(39, 119, 20);
        Color colorButton =  new Color(204, 204, 0);

        buttons = new JPanel();
        hit = new JButton("Hit");
        stay = new JButton("Stay");
        holdButtons = new JPanel();
        displayArea = new JTextArea(23, 50);
        displayArea.setEditable(false);
        displayArea.setForeground(colorButton);
        displayArea.setBackground(colorBackground);
        buttons.setLayout(new BorderLayout());

        hit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    event("hit");
            }
        });
        stay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    event("stay");
            }
        });
        holdButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttons.add(displayArea, BorderLayout.NORTH);
        holdButtons.add(hit);
        holdButtons.add(stay);
        buttons.add(holdButtons, BorderLayout.SOUTH);
        add(buttons);
        setSize(500,500);
        setVisible(true);

        startClient();

    }

    /**
     * Starts the client and connects to the server as well as setting up the thread pool.
     */
    public void startClient() {
        try // connect to server and get streams
        {
            // make connection to server
            connection = new Socket(
                    InetAddress.getByName(blackjackHost), 23718);

            // get streams for input and output
            input = new Scanner(connection.getInputStream());
            output = new Formatter(connection.getOutputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        // create and start worker thread for this client
        ExecutorService worker = Executors.newFixedThreadPool(1);
        worker.execute(this); // execute client
    }

    private void connectToServer() throws IOException {
        displayMessage("Attempting connection\n");

        // create Socket to make connection to server
        client = new Socket(InetAddress.getByName(blackjackHost), 23718);

        // display connection information
        displayMessage("Connected to: " +
                client.getInetAddress().getHostName());
    }

    /**
     * Execute thread
     */
    public void run()
    {
        myNum = input.nextLine(); // get player's mark (X or O)

        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run()
                    {
                        // display player's mark
                        idField.setText("You are player \"" + myNum + "\"");
                    }
                }
        );

        myTurn = (myNum.equals(player1)); // determine if client's turn
        output.format("Deal\n");
        output.flush();

        // receive messages sent to client and output them
        while (true)
        {
            if (input.hasNextLine())
                processMessage(input.nextLine());
        }
    }

    /**
     * Handles event from button press.
     * @param intent
     */
    private void event(String intent){
        if(myTurn) {
            output.format(intent + "\n");
            output.flush();
            if (intent.equals("stay\n")) {
                myTurn = false;
            }
        }
    }

    /**
     * Displays message
     * @param messageToDisplay
     */
    private void displayMessage(final String messageToDisplay) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        displayArea.append(messageToDisplay); // updates output
                    }
                }
        );
    }

    /**
     * Takes in a message and outputs based on what event happened from the given message.
     * @param message
     */
    private void processMessage(String message) {
        // valid move occurred
        if (message.contains("hit:")) {
            message = message.replace("hit: ", "");
            displayMessage("\nYou were dealt: " + message + "\nStay or hit?");

        } if (message.contains("output:")) {
            message = message.replace("output:", "");
            displayMessage("\n" + message);

        } if (message.contains("end:")) {
            message = message.replace("end:: ", "");
            displayMessage("\n" + message);

        } else
            displayMessage(message + "\n"); // display the message
    }

}
