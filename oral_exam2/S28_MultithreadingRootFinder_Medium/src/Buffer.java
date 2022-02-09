public interface Buffer {
    // place int value into Buffer
    public void blockingPut(Tuple value) throws InterruptedException;

    // obtain int value from Buffer
    public Tuple blockingGet() throws InterruptedException;
} // end interface Buffer