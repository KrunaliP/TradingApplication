package com.deutschebank.interview.tradingapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.lang.reflect.Field;
import static org.mockito.Mockito.*;

class ApplicationTest {

    Application app;
    Algo algo;

    @BeforeEach
    void initService() throws NoSuchFieldException, IllegalAccessException {
        algo = Mockito.spy(new Algo());
        app = new Application();
        Field field = Application.class.getDeclaredField("algo");
        field.setAccessible(true);
        field.set(app, algo);
    }

    @Test
    void handleSignal() {
        app.handleSignal(1);
        verify(algo, times(1)).doAlgo();
    }
}