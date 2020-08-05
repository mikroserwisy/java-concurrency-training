package pl.training.concurrency.ex026_collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {

    public static void main(String[] args) {
        Map<Integer, Integer> storage = new ConcurrentHashMap<>();
                //Collections.synchronizedMap(new HashMap<>());
        asyncRun(() -> {
            Random random = new Random();
            while (true) {
                storage.put(random.nextInt(1_000), random.nextInt());
                Thread.sleep(200);
            }
        }).start();
        while (true) {
            for (Map.Entry<Integer, Integer> entry : storage.entrySet()) {
                System.out.println(entry.getValue());
            }
        }
    }



    interface Task {

        void run() throws InterruptedException;

    }

    private static Thread asyncRun(Task task) {
        return new Thread(() -> {
            try {
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
