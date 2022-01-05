package com.utm.prprotocols.sockets;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Scope("Singleton")
public class Server implements Runnable {
    private static final int PORT = 9090;
    protected static final Logger LOG = LogManager.getLogger(Server.class);

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);
    protected static Map<Integer, String> usernames = new HashMap<>();
    protected static int connCount = 0;

    @SneakyThrows
    @Override
    public void run() {
        ServerSocket listener = new ServerSocket(PORT);

        while (true) {
            Socket client = listener.accept();

            ClientHandler clientThread = new ClientHandler(client, clients);
            clients.add(clientThread);

            pool.execute(clientThread);
        }
    }
}