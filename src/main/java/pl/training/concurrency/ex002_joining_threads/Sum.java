package pl.training.concurrency.ex002_joining_threads;

public class Sum implements Runnable {

    private static final int SLEEP_TIME = 3_000;

    private final int firstValue;
    private final int secondValue;

    public Sum(int firstValue, int secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            System.out.println("Stopping sum thread");
        }
        System.out.printf("Result: %s\n", firstValue + secondValue);
    }

}
