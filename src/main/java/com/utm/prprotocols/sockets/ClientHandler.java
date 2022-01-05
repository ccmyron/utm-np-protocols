package com.utm.prprotocols.sockets;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final BufferedReader in;
    private final PrintWriter out;
    private final ArrayList<ClientHandler> clients;

    public ClientHandler(Socket socket, ArrayList<ClientHandler> clients) throws IOException {
        this.clientSocket = socket;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @SneakyThrows
    public void run() {
        try {
            // Create user entry
            Server.connCount++;
            Server.usernames.put(clientSocket.getPort(), "user" + Server.connCount);
            Server.LOG.info("[SERVER] {} Connected!", Server.usernames.get(clientSocket.getPort()));

            // Read input
            while (true) {
                String request = in.readLine();
                if (request.startsWith("say")) {
                    int firstSpace = request.indexOf(" ");
                    if (firstSpace != -1) {
                        Server.LOG.info("[ALL][{}] {}",
                                Server.usernames.get(clientSocket.getPort()),
                                request.substring(firstSpace + 1));
                        outToAll(request.substring(firstSpace + 1));
                    }
                } else {
                    Server.LOG.info("[{}] {}",Server.usernames.get(clientSocket.getPort()) , request);
                    out.println("received a message!");
                }
            }
        } catch (IOException e) {
            Server.LOG.debug(e.getMessage());
        } finally {
            in.close();
            out.close();
            clientSocket.close();
        }
    }

    private void outToAll(String substring) {
        for (ClientHandler client : clients) {
            client.out.println(substring);
        }
    }
}
