package inf112.skeleton.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class StateListener extends Thread {
    private BufferedReader reader;
    private LinkedBlockingQueue<String> queue;

    StateListener(BufferedReader reader, LinkedBlockingQueue<String> queue) {
        this.reader = reader;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                queue.put(reader.readLine());
            } catch (IOException e) {
                System.err.println("Failed to receive state: " + e);
                throw new RuntimeException();
            } catch (InterruptedException e) {
                System.err.println("Failed to add state to queue: " + e);
                throw new RuntimeException();
            }
        }
    }
}
