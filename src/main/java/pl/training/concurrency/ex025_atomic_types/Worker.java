package pl.training.concurrency.ex025_atomic_types;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

@Data
public class Worker implements Runnable {

    private AtomicLong value = new AtomicLong();

    @Override
    public void run() {
       value.incrementAndGet();
    }

}
