package inf112.skeleton.app;

import inf112.skeleton.app.Boards.ChopShop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server extends Thread
{
    private Game game;
    private int numPlayers;

    Server(int numPlayers)
    {
        this.numPlayers = numPlayers;
        game = new Game(new ChopShop(null), numPlayers, true);
    }

    @Override
    public void run()
    {
        LinkedBlockingQueue<String> commandQueue = new LinkedBlockingQueue<>();
        Vector<Socket> clients = new Vector<>(numPlayers);

        System.out.println("SERVER");
        System.out.println("Waiting for " + numPlayers + " clients.");

        ServerSocket socket;
        try { socket = new ServerSocket(2243); }
        catch (IOException e) { System.err.println("Failed to bind socket: " + e); throw new RuntimeException(); }

        try { while(clients.size() < numPlayers)
        {
            Socket client = socket.accept();
            clients.add(client);
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            CommandListener listener = new CommandListener(reader, commandQueue, clients.size() - 1);
            listener.setDaemon(false);
            listener.start();
            System.out.println(clients.size() + "/" + numPlayers);
        } }
        catch (IOException e) { System.err.println("Failed to accept clients: " + e); throw new RuntimeException(); }

        System.out.println("Starting game.");


        while(true)
        {
            try { System.out.println(commandQueue.take()); }
            catch (InterruptedException e) { System.err.println("Failed to receive command from listener thread: " + e); throw new RuntimeException(); }
        }
    }
}
