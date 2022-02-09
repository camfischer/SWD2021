/**
 * Creates gametype Hockey.
 */
public class Hockey extends RunGame{
    /**
     * Has Parameters of Two Teams both with different attributes. Sets the correct type of game in this case Hockey.
     * @param t1
     * @param t2
     */
    public Hockey(Team t1, Team t2){
        super(t1, t2, 3, "Period", 20, new String[]{"Goal"});
    }
    /**
     * addScore does just that, adds the score to the appropriate team based off of the score method. Must Override as it
     * is from the superclass "RunGame."
     * @param goal
     * @param teamscored
     * @param scoremethod
     */
    @Override
    public void addScore(int goal, int teamscored, String scoremethod){
        goal = 1;
        super.addScore(goal, teamscored, scoremethod);
    }
}
