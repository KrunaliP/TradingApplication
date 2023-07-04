package com.deutschebank.interview.tradingapplication;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SignalHandlerServerTest {

    @Test
    void parseQuery() throws UnsupportedEncodingException {
        SignalHandlerServer signalHandlerServer = new SignalHandlerServer();
        Map<String, Object> parameters = new HashMap<>();
        String query = "signal=1";
        signalHandlerServer.parseQuery(query, parameters);
        for (String key : parameters.keySet()) {
            assertEquals(key, "signal");
            assertEquals(parameters.get(key), "1");
        }
    }
}