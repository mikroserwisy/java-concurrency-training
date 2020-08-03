package pl.training.concurrency.ex007_thread_local;

public class Application {

    public static void main(String[] args) {
        Counter counter = new Counter();
        new Thread(counter).start();
        new Thread(counter).start();
    }

}
