package com.deutschebank.interview.tradingapplication;

public class Main {
    public static void main(String[] args) {

        Application application = new Application();

        for (int i = 1; i <= 3; i++) {
            application.handleSignal(i);
        }
    }
}