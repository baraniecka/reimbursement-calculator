package org.baraniecka;

import org.baraniecka.config.ServerConfig;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            ServerConfig.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
1. Set up a HttpServer +
2. Set up a HttpHandler 1/2
3. Set different method handling for different requests
4. Call a method in html template



 */