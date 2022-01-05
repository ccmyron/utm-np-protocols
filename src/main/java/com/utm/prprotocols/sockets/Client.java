package com.utm.prprotocols.sockets;

import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        ServerConnection serverConnection = new ServerConnection(socket);
        new Thread(serverConnection).start();
        while (true) {
            System.out.print("> ");
            String command = keyboard.readLine();

            if (command.equals("exit")) {
                break;
            }

            out.println(command);
        }
    }
}