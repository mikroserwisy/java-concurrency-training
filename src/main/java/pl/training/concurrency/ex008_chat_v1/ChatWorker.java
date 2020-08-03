package pl.training.concurrency.ex008_chat_v1;

import java.net.Socket;

public class ChatWorker implements Runnable {

    private final Socket socket;
    private final ChatWorkers chatWorkers;

    public ChatWorker(Socket socket, ChatWorkers chatWorkers) {
        this.socket = socket;
        this.chatWorkers = chatWorkers;
    }

    @Override
    public void run() {

    }

    public void send(String text) {

    }

}
