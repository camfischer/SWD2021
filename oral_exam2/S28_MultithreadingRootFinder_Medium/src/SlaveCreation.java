import java.math.*;
import java.util.ArrayList;

public class SlaveCreation implements Runnable {
    double a,b,c;
    private int threadID;
    private final Buffer shared;
    private final Buffer sharedRoots;
    private Tuple abcs;

    /**
     * Constructor that takes two buffers, sharedLocation for the coefficients of the polynomial, roots for the solutions
     * and id for the id of the thread.
     * @param sharedlocation
     * @param roots
     * @param id
     * @throws InterruptedException
     */
    public SlaveCreation(Buffer sharedlocation, Buffer roots, int id) throws InterruptedException {
        this.sharedRoots = roots;
        this.threadID = id;
        //System.out.println(threadID +" "+ id);
        this.shared = sharedlocation;
    }

    /**
     * Run allows multithreading capability from Runnables, in this case it solves the polynomial and stores the roots in
     * the sharedRoots buffer when finished. Without while(true) this program would not finish.
     */
    @Override
    public void run() {
        while (true) {
            try {
                this.abcs = shared.blockingGet();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Access tuple values
            a = abcs.tuples()[0];
            b = abcs.tuples()[1];
            c = abcs.tuples()[2];
            double root1 = 0;
            double root2 = 0;
            //solve polynomials
            double imaginary = (b * b) - (4 * a * c);
            //System.out.println("line 35 "+this.threadID);
            //if value is imaginary
            if (imaginary < 0) {
                double real = -b / (2*a);
                double imag = Math.sqrt(-imaginary) / (2*a);

                try {
                  sharedRoots.blockingPut(new Tuple(real, imag, threadID));
                  //System.out.println(threadID);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("Solution to " + a +"x^2 + " + b +"x + " + c + " -> Root 1: i" +root1 + " Root 2: i"+ root2);
            }//roots will be equal
            else if(imaginary == 0){
                root1 = -b  / (2 * a);
                root2 = root1;
                try {
                   sharedRoots.blockingPut(new Tuple(root1, root2, threadID ));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("Solution to " + a +"x^2 + " + b +"x + " + c + " -> Root 1: " + root1 + " Root 2: " + root2);
            }
            else{ // normal polynomial with none complex values
                root1 = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
                root2 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
                try {
                   sharedRoots.blockingPut(new Tuple(root1, root2, threadID));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println(threadID);
                //System.out.println("Solution to " + a +"x^2 + " + b +"x + " + c + " -> Root 1: " + root1 + " Root 2: " + root2);
            }
        }
    }
}
