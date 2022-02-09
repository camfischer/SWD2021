import java.awt.BorderLayout;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class BlackjackServer extends JFrame{
    private JTextArea outputArea; // for outputting moves
    private Player[] players; // array of Players
    private ServerSocket server; // server socket to connect with clients
    private int currentPlayer; // keeps track of player with current move
    private final static String[] NumPlayer = {"0","1"}; // number assigned to each player
    private final static int PLAYER_X = 0;
    private final static int PLAYER_2 = 1;
    private ExecutorService runGame; // will run players
    private Lock gameLock; // to lock game for synchronization
    private Condition otherPlayerConnected; // to wait for other player
    private Condition otherPlayerTurn; // to wait for other player's turn
    private Deck deck = new Deck();
    private Card card;
    private int dealerscore = 0;
    private boolean isGameOver = false;

    /**
     * Constructer that sets thread pool as well as preparing the game and lock conditions.
     */
    public BlackjackServer(){
        super("Blackjack");

        // create ExecutorService with a thread for each player
        runGame = Executors.newFixedThreadPool(2);

        gameLock = new ReentrantLock(); // create lock for game

        // condition variable for both players being connected
        otherPlayerConnected = gameLock.newCondition();

        // condition variable for the other player's turn
        otherPlayerTurn = gameLock.newCondition();

        //players = new Player[2]; // create array of players
        currentPlayer = PLAYER_X;
        // set current player to first player

        players = new Player[2];

        try
        {
            server = new ServerSocket(23718, 2); // set up ServerSocket
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
            System.exit(1);
        }

        outputArea = new JTextArea(); // create JTextArea for output
        add(outputArea, BorderLayout.CENTER);
        outputArea.setText("Server awaiting connections\n");

        setSize(300, 300); // set size of window
        setVisible(true); // show window
    }

    /**
     * Manages how thread is executed.
     */
    public void execute()
    {
        // wait for each client to connect
        for (int i = 0; i < players.length; i++)
        {
            try // wait for connection, create Player, start runnable
            {
               players[i] = new Player(server.accept(), i);
               runGame.execute(players[i]); // execute player runnable
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
                System.exit(1);
            }
        }
        //Setting dealer hand
        dealerscore = 0;
        card = deck.hit();
        dealerscore += card.getValue();

        gameLock.lock(); // lock game to signal player X's thread

        try
        {
            players[PLAYER_X].setSuspended(false); // resume player X
            otherPlayerConnected.signal(); // wake up player X's thread
            deck.shuffle();
        }
        finally
        {
            gameLock.unlock(); // unlock game after signalling player X
        }
    }

    /**
     * Displays message to screen and updates outputArea
     * @param messageToDisplay
     */
    private void displayMessage(final String messageToDisplay)
    {
        // display message from event-dispatch thread of execution
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run() // updates outputArea
                    {
                        outputArea.append(messageToDisplay); // add message
                    }
                }
        );
    }

    /**
     * Player class that determines which number the player is as well as assigning values to them.
     */
    private class Player implements Runnable
    {
        private Socket connection; // connection to client
        private Scanner input; // input from client
        private Formatter output; // output to client
        private int playerNumber; // tracks which player this is
        private String mark; // mark for this player
        private boolean suspended = true; // whether thread is suspended

        // set up Player thread
        public Player(Socket socket, int number)
        {
            playerNumber = number; // store this player's number
            mark = NumPlayer[playerNumber]; //assigning player's mark
            connection = socket; // store socket for client

            try // obtain streams from Socket
            {
                input = new Scanner(connection.getInputStream());
                output = new Formatter(connection.getOutputStream());
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
                System.exit(1);
            }
        }



        // control thread's execution
        public void run()
        {
            // send client its mark (X or O), process messages from client
            try
            {
                displayMessage("Player " + mark + " connected\n");
                output.format("%s\n", mark); // send player's mark
                output.flush(); // flush output


                // if player X, wait for another player to arrive
                if (playerNumber == PLAYER_X)
                {
                    output.format("%s\n%s", "Player X connected",
                            "Waiting for another player\n");
                    output.flush(); // flush output

                    gameLock.lock(); // lock game to  wait for second player

                    try
                    {
                        while(suspended)
                        {
                            otherPlayerConnected.await(); // wait for player O
                        }
                    }
                    catch (InterruptedException exception)
                    {
                        exception.printStackTrace();
                    }
                    finally
                    {
                        gameLock.unlock(); // unlock game after second player
                    }

                    // send message that other player connected
                    output.format("Other player connected. Your move.\n");
                    output.flush(); // flush output
                    //Give dealer card to start
                    output.format("output: Dealer got " + card.toString() + " \n");
                    output.flush();
                }
                else
                {
                    output.format("Player 2 connected, please wait\n");
                    output.flush(); // flush output
                    //Give dealer card to start

                    output.format("output: Dealer got " + card.toString() + " \n");
                    output.flush();
                }

                int player1Score = 0;
                String in;
                ///Game logic
                while(!isGameOver){
                    in = input.nextLine();

                    if(in.equalsIgnoreCase("Deal")){
                        player1Score = 0;

                        card = deck.hit();
                        player1Score += card.getValue();
                        output.format("output: You got a " + card.toString()+ "\n");
                        output.flush();

                        card = deck.hit();
                        player1Score += card.getValue();
                        output.format("hit: You got a " + card.toString()+ "\n");
                        output.flush();

                        card = deck.hit();
                        dealerscore += card.getValue();
                    }
                    if(in.equalsIgnoreCase("hit")){
                        card= deck.hit();
                        player1Score += card.getValue();
                        output.format("hit: You got a " + card.toString() + "\n");
                        output.flush();
                        if(player1Score > 21){
                            output.format("end: Bust!");
                            isGameOver = true;
                        }
                    }
                    else if(in.equalsIgnoreCase("stay")){
                        while (dealerscore <= 16){
                            card = deck.hit();
                            dealerscore += card.getValue();
                            //output.format("output: Dealer got " + card.toString() + " \n");
                            //output.flush();
                        }
                        if(player1Score == dealerscore){
                            output.format("end: Even score, no bet lost");
                            output.flush();
                            isGameOver = true;
                        }
                        else if(player1Score > dealerscore && player1Score <= 21){
                            output.format("end: You win!");
                            output.flush();
                            isGameOver = true;
                        }
                        else if(player1Score < dealerscore && dealerscore <= 21){
                            output.format("end: House wins!" );
                            output.flush();
                            isGameOver = true;
                        }
                        else if(!(player1Score > 21 ) && dealerscore > 21){
                            output.format("end: The house busted! You win!");
                            output.flush();
                            isGameOver = true;
                        }
                        else{
                            output.format("end: Somehow you won!");
                            output.flush();
                            isGameOver = true;
                        }
                    }
                }

            }
            finally
            {
                try
                {
                    connection.close(); // close connection to client
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                    System.exit(1);
                }
            }
        }

        // set whether or not thread is suspended
        public void setSuspended(boolean status)
        {
            suspended = status; // set value of suspended
        }
    }

}
