package pl.training.concurrency.solution3;

import java.util.Random;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        BarberShop barberShop = new BarberShop(3);
        Thread barberThread = asyncRun(barberShop::barber);
        barberThread.start();

        for (int i = 0; i < 20; i++) {
            asyncRun(barberShop::customerWalksIn).start();
            Thread.sleep(random.nextInt(1_000));
        }

        barberThread.join();
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
