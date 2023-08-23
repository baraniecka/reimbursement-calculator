package org.baraniecka.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.slf4j.Slf4j;
import org.baraniecka.ExpenseService;
import org.baraniecka.ReceiptType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

@Slf4j
public class AdminRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        log.info("Hello from admin handler");

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        ExpenseService service = ExpenseService.getInstance();
        InputStreamReader io = new InputStreamReader(exchange.getRequestBody());
        String request = new BufferedReader(io).readLine();
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");


        if (method.equals("GET")) {
            switch (path) {
                case "/api/reimbursement/admin/allowance/daily": {

                    log.info("GET {}, request: {}", path, request);

                    String response = String.valueOf(service.getDailyAllowance());
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());

                    io.close();
                    os.close();
                    break;
                }
                case "/api/reimbursement/admin/mileage/daily": {

                    log.info("Hello from GET, request: {}", request);

                    String response = String.valueOf(service.getDailyMileage());
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());

                    io.close();
                    os.close();
                    break;
                }
                case "/api/reimbursement/admin/limit/distance": {
                    log.info("Hello from GET, request: {}", request);

                    String response = String.valueOf(service.getDistanceLimit());
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());

                    io.close();
                    os.close();
                    break;
                }
                case "/api/reimbursement/admin/limit/total": {
                    log.info("Hello from GET, request: {}", request);

                    String response = String.valueOf(service.getTotalLimit());
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());

                    io.close();
                    os.close();
                    break;
                }
                case "/api/reimbursement/admin/type": {
                    log.info("Hello from GET, request: {}", request);
                    ObjectMapper mapper = new ObjectMapper();
                    List<ReceiptType> types = service.getExpenseTypes();
                    String response = mapper.writeValueAsString(types);
                    System.out.println(response);
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());

                    io.close();
                    os.close();
                    break;
                }
            }
        } else if (method.equals("POST")) {
            switch (path) {
                case "/api/reimbursement/admin/allowance/daily": {

                    log.info("Hello from POST, request: {}", request);
                    double value = Double.parseDouble(request);

                    String response = String.valueOf(service.setDailyAllowance(value));

                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());

                    io.close();
                    os.close();
                    break;
                }
                case "/api/reimbursement/admin/mileage/daily": {

                    log.info("Hello from POST, request: {}", request);
                    double value = Double.parseDouble(request);

                    String response = String.valueOf(service.setDailyMileage(value));

                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());

                    io.close();
                    os.close();
                    break;
                }
                case "/api/reimbursement/admin/limit/distance": {
                    log.info("Hello from POST, request: {}", request);
                    double value = Double.parseDouble(request);

                    String response = String.valueOf(service.setDistanceLimit(value));

                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());

                    io.close();
                    os.close();
                    break;
                }
                case "/api/reimbursement/admin/limit/total": {
                    log.info("Hello from POST, request: {}", request);
                    double value = Double.parseDouble(request);

                    String response = String.valueOf(service.setTotalLimit(value));

                    exchange.sendResponseHeaders(200, response.getBytes().length);

                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());

                    io.close();
                    os.close();
                    break;
                }
                case "/api/reimbursement/admin/limit/type": {
                    log.info("Hello from POST, request: {}", request);
                    ObjectMapper mapper = new ObjectMapper();
                    ReceiptType type = mapper.readValue(request, ReceiptType.class);

                    String response = String.valueOf(service.addLimitForReceiptType(type));
                    log.info(response);
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());

                    io.close();
                    os.close();
                    break;
                }

                case "/api/reimbursement/admin/type": {
                    log.info("Hello from POST, request: {}", request);

                    String response = String.valueOf(service.addExpenseType(request));

                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());

                    io.close();
                    os.close();
                    break;
                }
            }
        } else {

            log.error("Hello from ELSE, request: {}", request);
            log.error("Method: {}", method);
            log.error("Path: {}", path);

            exchange.sendResponseHeaders(400, -1);
            OutputStream os = exchange.getResponseBody();
            os.write(-1);
            os.close();
        }


    }
}
