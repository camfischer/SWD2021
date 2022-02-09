import java.util.Arrays;

/**
 * Tuple is a helper class that allows the roots and coefficients to be stored.
 */
public class Tuple {
    private Double a,b,c;
    private int ID;

    /**
     * Constructor that is used for the polynomials.
     * @param first
     * @param second
     * @param third
     */
    public Tuple(Double first, Double second, Double third){
        this.a = first;
        this.b = second;
        this.c =third;
    }

    /**
     * Constructor that is used for roots and the id of the thread.
     * @param root1
     * @param root2
     * @param num
     */
    public Tuple(Double root1, Double root2, int num){
        this.a= root1;
        this.b = root2;
        this.ID = num;
    }

    /**
     * Allows the tuples' values to be accessed.
     * @return
     */
    public Double[] tuples (){
        Double[] tuple = {this.a, this.b, this.c};
        return tuple;

    }

    /**
     * Simple toString method which allows the solutions to be printed.
     * @return
     */
    public String toString(){
        return "[ " + a + ", " + b + ", ]";
    }

    /**
     * GetID is important for keeping track of the threadID that solved the polynomial;
     * @return
     */
    public int getID(){
        return this.ID;
    }


}
