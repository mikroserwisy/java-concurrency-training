package pl.training.concurrency.ex026_collections;

import lombok.RequiredArgsConstructor;

import java.util.Queue;
import java.util.UUID;

@RequiredArgsConstructor
public class AddTask implements Runnable {

    private final Queue<String> queue;

    @Override
    public void run() {
        for (int index = 0; index < 5_000; index++) {
            queue.add(UUID.randomUUID().toString());
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
