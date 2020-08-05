package pl.training.concurrency.ex024_volatile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Worker implements Runnable {

    private final StringBuffer buffer;
    volatile boolean shouldFinish;

    @Override
    public void run() {
        while (!shouldFinish) {
            String text = Thread.currentThread().getName() + " " + shouldFinish + "\n";
            buffer.append(text);
        }
    }

}
