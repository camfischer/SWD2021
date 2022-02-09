import java.util.*;

/**
 * Main method contained in ScoreboardMain runs the game and is filled with all of the logic that is required to do so.
 */
public class ScoreboardMain {
    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in); //Declaring input
        System.out.println("Select the type of game:");
        System.out.println("1. Football");
        System.out.println("2. Basketball");
        System.out.println("3. Soccer");
        System.out.println("4. Hockey");
        System.out.print("Enter choice:"); //Scoreboard Menu
        int entry = input.nextInt();
        input.nextLine();
        while(entry < 1 || entry > 4){
            System.out.println("Invalid Entry");
            System.out.print("Enter choice:");
            entry = input.nextInt();
        }
        //String hometeam, awayteam;

        //Creating game variable and assigning home and away teams.
        RunGame game;
        Team teamHome, teamAway;
        System.out.print("Enter Home Team:");
        String hometeam = input.nextLine();

        System.out.print("Enter Away Team:");
        String awayteam = input.nextLine();
        //Prompt user to choose which sport to play and create teams and start appropriate game.
        if(entry == 1){
            teamHome = new Team(hometeam);
            teamAway = new Team(awayteam);
            game = new Football(teamHome, teamAway);
        }
        else if(entry == 2){
            teamHome = new Team(hometeam);
            teamAway = new Team(awayteam);
            game = new Basketball(teamHome, teamAway);
        }
        else if(entry == 3){
            teamHome = new Team(hometeam);
            teamAway = new Team(awayteam);
            game = new Soccer(teamHome, teamAway);
        }
        else{
            teamHome = new Team(hometeam);
            teamAway = new Team(awayteam);
            game = new Football(teamHome, teamAway);
        }
        //Starting game, sets current play period to 1
        game.gameStart();

        while(!game.gameOver()){
            System.out.println(teamHome.getName() + " - " + teamHome.getScore() + ", "+ teamAway.getName() +" - " + teamAway.getScore());
            System.out.println("Current Period" + game.getCurrent());
            System.out.println();
            System.out.println("Menu: ");
            for(int i = 0; i < game.getScoreMethods().length; i++){ //Printing home team scoring methods
                int j = i;
                System.out.println(j+1 +": "+ teamHome.getName() + " " + game.getScoreMethods()[i]);
            }
            for(int i = 0; i < game.getScoreMethods().length; i++){ //printing away team scoring methods
                int j= game.getScoreMethods().length-1;
                System.out.println(j+i+2 +": "+ teamAway.getName() +" "+ game.getScoreMethods()[i]);
            }
            System.out.println(game.getScoreMethods().length*2+1 + ": End " + game.getPeriodType());
            System.out.print("Enter choice: ");
            entry = input.nextInt();

            if(entry == game.getScoreMethods().length*2+1) { //End period
                game.endPeriod();
            }
            else if(entry < game.getScoreMethods().length){
                game.addScore(0,1, game.getScoreMethods()[entry-1]);
            }
            else if(entry >= (game.getScoreMethods().length) && entry <(game.getScoreMethods().length*2)){
                game.addScore(0,2,game.getScoreMethods()[entry-1]);
            }
            System.out.println();

            //Printing game over screen and seeing which team won.
            if(game.gameOver()){
                System.out.println("Game is over");
                System.out.println(teamHome.getName() + " - " + teamHome.getScore() + ", "+ teamAway.getName() +" - " + teamAway.getScore());
                System.out.println("Current Period: Final ");
                if(teamAway.getScore() == teamHome.getScore()){
                    System.out.println("Game is Tied");
                }
                else if(teamAway.getScore() > teamHome.getScore()){
                    System.out.println("Winner Winner chicken dinner!: "+teamAway.getName());
                }
                else{
                    System.out.println("Winner Winner chicken dinner!: "+teamHome.getName());
                }
                System.out.println();
            }
        }
    }
}
