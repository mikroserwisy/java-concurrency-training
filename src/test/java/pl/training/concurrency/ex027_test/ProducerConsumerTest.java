package pl.training.concurrency.ex027_test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import org.junit.Before;

import java.util.concurrent.LinkedTransferQueue;

public class ProducerConsumerTest extends MultithreadedTestCase {

    private LinkedTransferQueue<String> store = new LinkedTransferQueue<>();

    @Before
    @Override
    public void initialize() {
        super.initialize();
        //setTrace(true);
        System.out.println("Init...");
    }

    @Override
    public void finish() {
        super.finish();
        assertTrue(store.isEmpty());
    }

    void thread1() throws InterruptedException {
        System.out.println("Thread 1: " + store.take());
    }

    void thread2() throws InterruptedException {
        waitForTick(2);
        System.out.println("Thread 1: " + store.take());
    }

    void thread3() {
        waitForTick(1);
        store.put("Java");
        waitForTick(2);
        waitForTick(1); // ignored
        store.put("Scala");
        System.out.println("Thread 3: Inserted 2 elements");
    }

}
