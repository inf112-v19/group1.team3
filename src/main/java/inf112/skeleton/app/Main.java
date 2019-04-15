package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (Arrays.asList(args).contains("--server")) {
            int num_clients = Integer.parseInt(args[Arrays.asList(args).indexOf("--server") + 1]);
            Server server = new Server(num_clients);
            server.setDaemon(false);
            server.start();
        } else if (Arrays.asList(args).contains("--client")) {
            String addr = args[Arrays.asList(args).indexOf("--client") + 1];

            LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
            cfg.title = "RoboRally";
            cfg.width = 768 + 300; // Add space for the UI
            cfg.height = 768;

            new LwjglApplication(new RoboRally(addr), cfg);
        } else {
            Server server = new Server(1);
            server.start();

            LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
            cfg.title = "RoboRally";
            cfg.width = 768 + 300; // Add space for the UI
            cfg.height = 768;

            new LwjglApplication(new RoboRally(), cfg);
        }
    }
}