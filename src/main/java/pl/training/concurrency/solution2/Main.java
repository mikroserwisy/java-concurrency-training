package pl.training.concurrency.solution2;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Factory factory = new Factory();
        Thread t1 = new Thread(new Producer(factory,"H", 2));
        Thread t2 = new Thread(new Producer(factory,"O", 1));
        t2.start();
        t1.start();
        Thread.sleep(10_000);
    }

}