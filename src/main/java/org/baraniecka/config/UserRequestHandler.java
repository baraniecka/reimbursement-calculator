package org.baraniecka.config;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.slf4j.Slf4j;
import org.baraniecka.ExpenseService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class UserRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        log.info("Hello from user handler");

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        ExpenseService service = ExpenseService.getInstance();
        InputStreamReader io = new InputStreamReader(exchange.getRequestBody());
        String request = new BufferedReader(io).readLine();
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        if (method.equals("GET")) {
            switch (path){
                case "/api/reimbursement/business-trip/exclude": {
                    log.info("GET {}, request {}", path, request);


                }
            }
        }
    }
}
