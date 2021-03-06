package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import inf112.skeleton.app.Boards.ChopShop;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "RoboRally";
        cfg.width = 768 + 300; // Add space for the UI
        cfg.height = 768;

        if (Arrays.asList(args).contains("--server")) { // server mode, no window, waits for the specified number of clients before starting the game logic.
            int num_clients = Integer.parseInt(args[Arrays.asList(args).indexOf("--server") + 1]);
            Game gameServer = new Game(new ChopShop(null), num_clients, true);
            Server server = new Server(num_clients, gameServer::handleCommand, gameServer::getState);
            gameServer.supplyBroadcaster(server::broadcast);
            server.setDaemon(false);
            server.start();
        } else if (Arrays.asList(args).contains("--client")) { // client mode, connects to specified existing server
            String addr = args[Arrays.asList(args).indexOf("--client") + 1];
            new LwjglApplication(new RoboRally(addr), cfg);
        } else { // Combined/single player mode, starts a server expecting 1 player and connects to it locally.
            int num_clients = 1;
            Game gameServer = new Game(new ChopShop(null), num_clients, true);
            Server server = new Server(num_clients, gameServer::handleCommand, gameServer::getState);
            gameServer.supplyBroadcaster(server::broadcast);
            server.start();

            new LwjglApplication(new RoboRally(), cfg);
        }
    }
}