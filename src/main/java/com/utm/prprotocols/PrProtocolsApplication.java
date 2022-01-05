package com.utm.prprotocols;

import com.utm.prprotocols.sockets.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrProtocolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrProtocolsApplication.class, args);

        Server socketServer = new Server();
        socketServer.run();
    }
}
