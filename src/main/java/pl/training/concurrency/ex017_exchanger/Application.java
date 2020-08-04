package pl.training.concurrency.ex017_exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Application {

    public static void main(String[] args) {
        Exchanger<List<String>> listExchanger = new Exchanger<>();
        new Thread(new Consumer(listExchanger)).start();
        new Thread(new Producer(listExchanger)).start();
    }

}
