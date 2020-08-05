package pl.training.concurrency.ex026_collections;

import lombok.RequiredArgsConstructor;

import java.util.Deque;
import java.util.Queue;
import java.util.UUID;

@RequiredArgsConstructor
public class PoolTask implements Runnable {

    private final Deque<String> queue;

    @Override
    public void run() {
        for (int index = 0; index < 5_000; index++) {
            System.out.println(queue.pollFirst());
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
