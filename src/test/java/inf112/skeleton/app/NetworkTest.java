package inf112.skeleton.app;

import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.assertEquals;

public class NetworkTest {
    Server server;

    private Socket socket;
    private BufferedWriter server_writer;
    private BufferedReader server_reader;

    private BlockingQueue<Command> commandQueue;

    public NetworkTest() {
        commandQueue = new LinkedBlockingQueue<>();
        server = new Server(1, this::receiver, this::broadcast);
        server.start();
    }

    @Test
    public void networkTest() throws IOException, InterruptedException {
        socket = new Socket("localhost", 2243);
        server_writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        server_reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        assertEquals(server_reader.readLine(), "NumPlayers=1");
        sendCommand("TEST", "Hello");
        assertEquals(commandQueue.take().value, "Hello");
        assertEquals(server_reader.readLine(), "Greetings");
        server.broadcast("Nice\n");
        assertEquals(server_reader.readLine(), "Nice");

        socket.close();
    }

    void sendCommand(String command, String value) throws IOException {
        server_writer.write(command + "=" + value + "\n");
        server_writer.flush();
    }

    void receiver(Command command) {
        commandQueue.add(command);
    }

    String broadcast() {
        return "Greetings\n";
    }
}
