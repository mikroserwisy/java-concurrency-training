package pl.training.concurrency.ex011_chat_v2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ChatWorkers {

    private final List<ChatWorker> chatWorkers = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(ChatWorker chatWorker) {
        lock.writeLock().lock();
        chatWorkers.add(chatWorker);
        lock.writeLock().unlock();
    }

    public void remove(ChatWorker chatWorker) {
        lock.writeLock().lock();
        chatWorkers.remove(chatWorker);
        lock.writeLock().unlock();
    }

    public void broadcast(String text) {
        lock.readLock().lock();
        chatWorkers.forEach(chatWorker -> chatWorker.send(text));
        lock.readLock().unlock();
    }

}
