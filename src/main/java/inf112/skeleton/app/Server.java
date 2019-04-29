package inf112.skeleton.app;

import inf112.skeleton.app.Boards.ChopShop;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

public class Server extends Thread {
    private Game game;
    private int numPlayers;
    private Vector<BufferedWriter> writers;

    Server(int numPlayers) {
        this.numPlayers = numPlayers;
        game = new Game(new ChopShop(null), numPlayers, true);
    }

    // sends a message to every client, primarily used to broadcast the game state
    private void broadcast(String message) {
        for (BufferedWriter writer : writers) {
            try {
                writer.write(message);
                writer.flush();
            } catch (IOException e) {
                System.err.println("Server failed to broadcast: " + e);
                throw new RuntimeException();
            }
        }
    }

    @Override
    public void run() {
        LinkedBlockingQueue<Command> commandQueue = new LinkedBlockingQueue<>();
        Vector<Socket> clients = new Vector<>(numPlayers);
        writers = new Vector<>(numPlayers);

        System.out.println("SERVER");
        System.out.println("Waiting for " + numPlayers + " clients.");

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

                System.out.println(clients.size() + "/" + numPlayers);
            }
        } catch (IOException e) {
            System.err.println("Failed to accept clients: " + e);
            throw new RuntimeException();
        }

        System.out.println("Starting game.");

        broadcast("NumPlayers=" + numPlayers + "\n");

        while (true) {
            try {
                Command command = commandQueue.take();
                game.handleCommand(command);
            } catch (InterruptedException e) {
                System.err.println("Failed to receive command from listener thread: " + e);
                throw new RuntimeException();
            }

            broadcast(game.getState());
        }
    }
}
