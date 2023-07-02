package com.deutschebank.interview.tradingapplication;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {

        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("server started at " + port);
        server.createContext("/", new RootHandlerServer());
        server.createContext("/handleSignal", new SignalHandlerServer());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}