package pl.training.concurrency.ex027_tests;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.locks.Lock;

@RequiredArgsConstructor
public class TaskWithLock implements Runnable {

    private static final int TIMEOUT = 500;

    private final Lock lock;

    @Override
    public void run() {
        for (int index = 0; index < 10; index++) {
            lock.lock();
            //System.out.println("After lock " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }

}
