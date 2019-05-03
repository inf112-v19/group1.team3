package inf112.skeleton.app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Server extends Thread {
    private Game game;
    protected int numPlayers;
    private Vector<BufferedWriter> writers;
    private Consumer<Command> commandFunction;
    private Supplier<String> broadcastFunction;

    public Server(int numPlayers, Consumer<Command> commandFunction, Supplier<String> broadcastFunction) {
        this.numPlayers = numPlayers;
        this.commandFunction = commandFunction;
        this.broadcastFunction = broadcastFunction;
    }

    // sends a message to every client, primarily used to broadcast the game state
    boolean broadcast(String message) {
        boolean success = true;
        int failures = 0;

        for (BufferedWriter writer : writers) {
            try {
                writer.write(message);
                writer.flush();
            } catch (IOException e) {
                System.err.println("Server failed to broadcast: " + e);
                success = false;
                failures += 1;
            }
        }

        if (failures == writers.size()) {
            System.err.println("Total loss of clients, closing server.");
            System.exit(1);
        }

        return success;
    }

    @Override
    public void run() {
        LinkedBlockingQueue<Command> commandQueue = new LinkedBlockingQueue<>();
        Vector<Socket> clients = new Vector<>(numPlayers);
        writers = new Vector<>(numPlayers);

        ServerSocket socket;
        try {
            socket = new ServerSocket(2243);
        } catch (IOException e) {
            System.err.println("Failed to bind socket: " + e);
            throw new RuntimeException();
        }

        try {
            while (clients.size() < numPlayers) {
                Socket client = socket.accept();
                clients.add(client);

                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                CommandListener listener = new CommandListener(reader, commandQueue, clients.size() - 1);
                listener.setDaemon(false);
                listener.start();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                writers.add(writer);
            }
        } catch (IOException e) {
            System.err.println("Failed to accept clients: " + e);
            throw new RuntimeException();
        }

        broadcast("NumPlayers=" + numPlayers + "\n");

        while (true) {
            try {
                Command command = commandQueue.take();
                commandFunction.accept(command);
            } catch (InterruptedException e) {
                System.err.println("Failed to receive command from listener thread: " + e);
                throw new RuntimeException();
            }

            broadcast(broadcastFunction.get());
        }
    }
}
