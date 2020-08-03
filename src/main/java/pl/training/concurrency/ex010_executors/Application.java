package pl.training.concurrency.ex010_executors;

import java.util.concurrent.*;

public class Application {

    private static final int THREADS_COUNT = 2;
    private static final int WAIT_TIME = 2;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Sum sum = new Sum(1, 5);
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);
        Future<Integer> futureTask = executorService.submit(sum);
        executorService.shutdown();
        executorService.awaitTermination(WAIT_TIME, TimeUnit.SECONDS);
        System.out.printf("Result: %d", futureTask.get());
    }

}
