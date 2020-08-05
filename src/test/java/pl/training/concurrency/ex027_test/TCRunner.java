package pl.training.concurrency.ex027_test;

import edu.umd.cs.mtc.TestFramework;

public class TCRunner {

    public static void main(String[] args) throws Throwable {
        ProducerConsumerTest test = new ProducerConsumerTest();
        TestFramework.runManyTimes(test, 1);
    }

}
