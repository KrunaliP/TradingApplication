package com.deutschebank.interview.tradingapplication;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class RootHandlerServer implements HttpHandler {

    @Override

    public void handle(HttpExchange he) throws IOException {

        String requestedURL = "http://" + he.getRequestHeaders().getFirst("Host") + he.getRequestURI();
        URL u = new URL(requestedURL);
        String response = "<h1>Server started successfully if you see this message</h1><h1>Host post: "
                + u.getPort() + "</h1>";
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
