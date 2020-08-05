package pl.training.concurrency.common;

public class ThreadUtils {

    public static Thread asyncRun(Task task) {
        return new Thread(() -> {
            try {
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
