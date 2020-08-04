package pl.training.concurrency.ex013_semaphore;

public class BoundedSemaphore {

    private final int maxPermits;
    private int usedPermits;

    public BoundedSemaphore(int maxPermits) {
        this.maxPermits = maxPermits;
    }

    public synchronized void acquire() throws InterruptedException {
        while (usedPermits == maxPermits) {
            wait();
        }
        notify();
        usedPermits++;
    }

    public synchronized void release() throws InterruptedException {
        if (usedPermits > 0) {
            usedPermits--;
        }
        notify();
    }

}
