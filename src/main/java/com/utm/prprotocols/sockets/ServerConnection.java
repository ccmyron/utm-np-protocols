package com.utm.prprotocols.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable {
    private final Socket serverSocket;
    private final BufferedReader in;
    private final PrintWriter out;

    public ServerConnection(Socket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
        in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        out = new PrintWriter(serverSocket.getOutputStream(), true);
    }


    @Override
    public void run() {
        try {
            while (true) {
                String serverResponse;
                serverResponse = in.readLine();

                if (serverResponse == null) {
                    break;
                }

                System.out.println("Server: " + serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}