package inf112.skeleton.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class CommandListener extends Thread
{
    private BufferedReader reader;
    private BlockingQueue<Command> commandQueue;
    private int id;

    CommandListener(BufferedReader reader, BlockingQueue<Command> commandQueue, int id)
    {
        this.reader = reader;
        this.commandQueue = commandQueue;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                try {
                    String line = reader.readLine();
                    if (line == null || line.isEmpty() || !line.contains("=")) {
                        System.err.println("Corrupt message; possible player disconnect.");
                        commandQueue.put(new Command(-1, "END", ""));
                        return;
                    }
                    String command = line.split("=")[0];
                    String value = line.split("=")[1];
                    commandQueue.put(new Command(id, command, value));
                } catch (IOException e) {
                    System.err.println("Failed to receive command: " + e);
                    commandQueue.put(new Command(-1, "END", ""));
                    return;
                }
            }
            catch (InterruptedException e) {
                System.err.println("Failed to send command to main thread: " + e);
                throw new RuntimeException();
            }
        }
    }
}
