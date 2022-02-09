
import java.math.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MainMaster{
    /**
     * Main method or Master thread that launches the slaves and program.
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Scanner input = new Scanner(System.in);
        System.out.println("Enter 1 for 30 randomly generated sets or 2 for 3000 randomly generated sets: ");
        int in = input.nextInt();

        //Prompts user to choose between 30 solutions or 3000 randomly generated sets that show statistics.
        if(in == 1) {
            BlockingBuffer buffer = new BlockingBuffer();
            BlockingBuffer roots = new BlockingBuffer();
            int i = 1;
            int j = 0;
            int k = 0;
            while(j< 30){
                //Note, any value that has the sign -0 in the print is a complex root.
                Tuple val = new Tuple(randNum(), randNum(), randNum());
                buffer.blockingPut(val);
                j++;
            }
            while (i < 11) {
                //System.out.println("master "+i);
                SlaveCreation obj= new SlaveCreation(buffer, roots, i);
                executorService.execute(obj);

                i++;
            }
            while(k < 30){
                System.out.println(roots.blockingGet().toString());
                k++;
            }
        }
        else{
            BlockingBuffer buffer = new BlockingBuffer(3000);
            BlockingBuffer roots = new BlockingBuffer(3000);

            int i = 1;
            int j = 0;
            int k = 0;
            while (j< 3000) {
                buffer.blockingPut(new Tuple(randNum(), randNum(), randNum()));
                j++;
            }
            while(i<11){
                SlaveCreation slave = new SlaveCreation(buffer,roots, i);
                executorService.execute(slave);
                i++;
            }
            int[] solved = new int[11];
            while(k<3000){
                solved[roots.blockingGet().getID()]++;
                k++;
            }
            for(int x = 0; x < 11; x++) {
                System.out.println("Thread:" + x + " solved " + solved[x] + " polynomials");
            }
        }
        executorService.shutdown();
    }

    public static Double randNum(){
        return ThreadLocalRandom.current().nextDouble(0, 50);
    }
}
