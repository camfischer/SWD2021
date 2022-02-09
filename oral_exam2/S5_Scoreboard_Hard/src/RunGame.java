/**
 * Super class that does what it says in the name, allows the game to be run. Contains Getter and Setter methods for
 * each of the private variables.
 */

public abstract class RunGame {
    private Team t1,t2;
    private int period, periodLength, current;
    private String periodType;
    private String[] scoreMethods;

    /**
     * Constructor that initializes values for the game here it is team1, team2, period, name of period, length of the period,
     * and the method in which the team acquires points.
     * @param team1
     * @param team2
     * @param period
     * @param nameOfPeriod
     * @param lengthOfPeriod
     * @param scoreMethods
     */
    public RunGame(Team team1, Team team2, int period, String nameOfPeriod, int lengthOfPeriod, String[] scoreMethods){
        this.t1 = team1;
        this.t2=team2;
        this.period =period;
        this.periodType = nameOfPeriod;
        this.periodLength =lengthOfPeriod;
        this.scoreMethods = scoreMethods;
    }
    public String getPeriodType(){
        return this.periodType;
    }
    public Team getT1(){
        return this.t1;
    }
    public Team getT2(){
        return this.t2;
    }
    public String[] getScoreMethods(){
        return this.scoreMethods;
    }

    /**
     * Where points are set to each team determined points, which team scored, and the method of scoring.
     * @param points
     * @param teamscored
     * @param scoremethod
     */
    public void addScore(int points, int teamscored, String scoremethod){
        if(teamscored == 1)
            t1.addScore(points);
        else
            t2.addScore(points);
    }

    /**
     * Sets current period to 1 i.e starts the game.
     */
    public void gameStart(){
        this.current =1 ;
    }
    public boolean gameOver(){
        return this.current > this.period;
    }
    public int getCurrent(){
        return this.current;
    }

    public int getPeriodLength(){
        return this.periodLength;
    }

    /**
     * When endPeriod is called adds +1 to current period.
     */
    public void endPeriod(){
        this.current+=1;
    }


}
