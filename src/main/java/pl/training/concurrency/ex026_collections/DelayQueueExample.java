package pl.training.concurrency.ex026_collections;

import java.util.Date;
import java.util.concurrent.DelayQueue;

public class DelayQueueExample {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Event> queue = new DelayQueue<>();
        Date date = new Date();
        date.setTime(date.getTime() + 60 * 1_000);
        Event event = new Event(date);
        queue.add(event);
        System.out.println(queue.poll());
        Thread.sleep(60_000);
        System.out.println(queue.poll());
    }

}
