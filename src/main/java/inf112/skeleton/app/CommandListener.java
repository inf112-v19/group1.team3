package inf112.skeleton.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class CommandListener extends Thread
{
    private BufferedReader reader;
    private BlockingQueue<String> commandQueue;
    private int id;

    CommandListener(BufferedReader reader, BlockingQueue<String> commandQueue, int id)
    {
        this.reader = reader;
        this.commandQueue = commandQueue;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                commandQueue.put("Player " + id + ": " + reader.readLine());
            } catch (IOException e) {
                System.err.println("Failed to receive command: " + e);
                throw new RuntimeException();
            }
            catch (InterruptedException e) {
                System.err.println("Failed to send command to main thread: " + e);
                throw new RuntimeException();
            }
        }
    }
}
