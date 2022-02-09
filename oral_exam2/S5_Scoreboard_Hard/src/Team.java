/**
 * Class that creates the attributes of each team. Getter and Setter methods required.
 */
public class Team {
    private String name;
    private int score;
    Team(){
        this.name ="";
        this.score = 0;
    }
    Team(String name){
        this.name = name;
        this.score = 0;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setScore(int score){
        this.score = score;
    }
    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }

    public void addScore(int scoretoadd){
        score +=scoretoadd;
    }
}
