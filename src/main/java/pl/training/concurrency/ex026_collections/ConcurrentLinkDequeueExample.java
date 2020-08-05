package pl.training.concurrency.ex026_collections;

import java.util.concurrent.ConcurrentLinkedDeque;

public class ConcurrentLinkDequeueExample {

    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> deque = new ConcurrentLinkedDeque<>();
        for (int index = 0; index < 1_000; index++) {
            new Thread(new AddTask(deque)).start();
            new Thread(new PoolTask(deque)).start();
        }
    }

}
