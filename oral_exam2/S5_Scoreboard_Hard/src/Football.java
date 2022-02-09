/**
 * Creates game type Football
 */
public class Football extends RunGame{
    /**
     * Has Parameters of Two Teams both with different attributes. Sets the correct type of game in this case Football.
     * @param t1
     * @param t2
     */
    public Football(Team t1, Team t2){
        super(t1, t2, 4, "Quarter", 15, new String[]{"Touchdown", "Field Goal", "Safety", "Touchdown Conversion", "Touchdown Field Goal"});
    }
    /**
     * addScore does just that, adds the score to the appropriate team based off of the score method. Must Override as it
     * is from the superclass "RunGame."
     * @param points
     * @param teamscored
     * @param scoremethod
     */
    @Override
    public void addScore(int points, int teamscored, String scoremethod){
        if(scoremethod.equals("Touchdown")) {
            points = 6;
            super.addScore(points, teamscored, scoremethod);
        }
        else if(scoremethod.equals("Field Goal")){
            points = 3;
            super.addScore(points, teamscored, scoremethod);
        }
        else if(scoremethod.equals("Safety")){
            points = 2;
            super.addScore(points, teamscored, scoremethod);
        }
        else if(scoremethod.equals("Touchdown Conversion")){
            points = 2;
            super.addScore(points, teamscored, scoremethod);
        }
        else{
            points = 1;
            super.addScore(points, teamscored, scoremethod);
        }
    }
}
