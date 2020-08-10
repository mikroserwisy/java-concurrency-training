package pl.training.concurrency.ex027_test;

import org.junit.Before;
import org.junit.Test;
import pl.training.concurrency.common.TestExecutor;
import pl.training.concurrency.common.TestPhaser;
import pl.training.concurrency.common.TestThreadFactory;
import pl.training.concurrency.common.ThreadUtils;
import pl.training.concurrency.ex027_tests.TaskWithPhases;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static pl.training.concurrency.common.ThreadUtils.sleep;

public class TaskWithPhasesTest {

    private static final int TIMEOUT = 1_000;
    private static final int PARTIES =3;

    private final Phaser phaser = new TestPhaser(PARTIES);
    private final ThreadPoolExecutor executor = new TestExecutor(3, 10,
            10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    @Before
    public void init() {
        executor.setThreadFactory(new TestThreadFactory());
        for (int index = 0; index < PARTIES; index++) {
            executor.execute(new TaskWithPhases(index,phaser));
        }
    }

    @Test
    public void monitorThreads() {
        sleep(TIMEOUT);
        executor.shutdown();
    }

}
