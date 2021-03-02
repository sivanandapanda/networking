package com.example.jdk;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class MyHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestParamValue = null;
        if("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(exchange);
        } /*else if("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(exchange);
        }*/
        handleRequest(exchange, requestParamValue);
    }

    private void handleRequest(HttpExchange exchange, String requestParamValue) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("<html><body>")
                .append("<h1>Hello ").append(requestParamValue).append("</h1>")
                .append("</body></html>");

        exchange.sendResponseHeaders(200, htmlBuilder.length());
        outputStream.write(htmlBuilder.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        URI requestURI = httpExchange.getRequestURI();
        System.out.println("URI " + requestURI);
        String queryParam = requestURI.toString();
        System.out.println("Param " + queryParam);
        return queryParam != null ? queryParam.split("\\?")[1].split("=")[1] : "Naughty";
    }

}
