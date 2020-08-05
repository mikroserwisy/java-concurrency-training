package pl.training.concurrency.ex024_volatile;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        StringBuffer buffer = new StringBuffer();
        Worker worker = new Worker(buffer);
        for (int index = 0; index < 1_000; index++) {
            new Thread(worker).start();
        }
        Thread.sleep(2_000);
        buffer.append("Before finish\n");
        worker.shouldFinish = true;
        buffer.append("After finish\n");
        System.out.println(buffer.toString());
    }

}
