import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayBlockingQueue holds implements interface buffer and allows the values to be stored.
 */
public class BlockingBuffer implements Buffer{
    private ArrayBlockingQueue<Tuple> buffer;

    /**
     * Default Contructor with capacity of 30 for 30 sets required to solve.
     */
    public BlockingBuffer(){
        buffer = new ArrayBlockingQueue<Tuple>(30);
    }

    /**
     * Constructor for custom capacity if required is above 30.
     * @param capacity
     */
    public BlockingBuffer(int capacity){
        buffer = new ArrayBlockingQueue<Tuple>(capacity);
    }

    /**
     * Overrides from the blockingPut interface, puts the value in the queue and holds it.
     * @param value
     * @throws InterruptedException
     */
    @Override
    public void blockingPut(Tuple value) throws InterruptedException {
        buffer.put(value);

    }

    /**
     * BlockingGet retrieves the value from the queue and also removes it allowing for more values to be added which means
     * good multithreading usage.
     * @return
     * @throws InterruptedException
     */
    @Override
    public Tuple blockingGet() throws InterruptedException {
        Tuple readValue = buffer.take();
        Double[] tuple = readValue.tuples();
        //System.out.println(tuple[0] + " " + tuple[1] + " " + tuple[2] + "\n");
        return readValue;
    }


}
