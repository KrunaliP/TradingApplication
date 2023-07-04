package com.deutschebank.interview.tradingapplication;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class AlgoTest {

    Algo algo = mock(Algo.class);
    @Test
    void doAlgo() {
        algo.doAlgo();
        verify(algo, times(1)).doAlgo();
    }

    @Test
    void cancelTrades() {
        algo.cancelTrades();
        verify(algo, times(1)).cancelTrades();
    }

    @Test
    void reverse() {
        algo.reverse();
        verify(algo, times(1)).reverse();
    }

    @Test
    void submitToMarket() {
        algo.submitToMarket();
        verify(algo, times(1)).submitToMarket();
    }

    @Test
    void performCalc() {
        algo.performCalc();
        verify(algo, times(1)).performCalc();
    }

    @Test
    void setUp() {
        algo.setUp();
        verify(algo, times(1)).setUp();
    }

    @Test
    void setAlgoParam() {
        algo.setAlgoParam(1, 2);
        verify(algo, times(1)).setAlgoParam(1, 2);
    }
}