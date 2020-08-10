package pl.training.concurrency.ex027_tests;

import lombok.RequiredArgsConstructor;

import java.util.Random;
import java.util.concurrent.Phaser;

import static pl.training.concurrency.common.ThreadUtils.sleep;

@RequiredArgsConstructor
public class TaskWithPhases implements Runnable, Comparable<Long> {

    private static final int MAX_TIMEOUT = 10_000;

    private final long priority;
    private final Phaser phaser;
    private final Random random = new Random();

    @Override
    public void run() {
        phaser.arrive();
        System.out.printf("%s: Starting phase %d\n", Thread.currentThread().getName(), phaser.getPhase());
        for (int index = 0; index < 20; index++) {
            sleep(random.nextInt(MAX_TIMEOUT));
            phaser.arriveAndAwaitAdvance();
            System.out.printf("%s: Starting phase %d\n", Thread.currentThread().getName(), phaser.getPhase());
        }
        sleep(random.nextInt(MAX_TIMEOUT));
        phaser.arriveAndDeregister();
    }

    @Override
    public int compareTo(Long prioryty) {
        if (this.priority < prioryty) {
            return 1;
        }
        if (this.priority > prioryty) {
            return -1;
        }
        return 0;
    }

}
