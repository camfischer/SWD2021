/**
 * Creates a Game Type of Basketball
 */
public class Basketball extends RunGame{
    /**
     * Has Parameters of Two Teams both with different attributes. Sets the correct type of game in this case Basketball.
     * @param t1
     * @param t2
     */
    public Basketball(Team t1, Team t2){
        super(t1, t2, 4, "Quarter", 10, new String[]{"Layup", "2 Point Shot", "3 Point Shot", "Free Throw"});
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
        if(scoremethod.equals("Layup")) {
            points = 2;
            super.addScore(points, teamscored, scoremethod);
        }
        else if(scoremethod.equals("2 Point Shot")){
            points = 2;
            super.addScore(points, teamscored, scoremethod);
        }
        else if(scoremethod.equals("3 Point Shot")){
            points = 3;
            super.addScore(points, teamscored, scoremethod);
        }
        else if(scoremethod.equals("Free Throw")){
            points = 1;
            super.addScore(points, teamscored, scoremethod);
        }
    }
}
