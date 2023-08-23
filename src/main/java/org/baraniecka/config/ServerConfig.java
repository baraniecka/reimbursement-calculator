package org.baraniecka.config;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class ServerConfig {
    public static void runServer() throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/reimbursement", new RequestHandler());
        server.setExecutor(Executors.newSingleThreadExecutor());
        server.start();
    }


}
