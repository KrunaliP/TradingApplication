# TradingApplication

This is a application that handles a received signal from a HTTP endpoint and performs a series of predefined commands related to the signal.

Once the application is deployed we can trigger the signal by invoking the below get method.
http://localhost:8080/handleSignal?signal=1

Here the parameter signal accepts a single signal-id in integer format and performs the calculations related to the signal.

In case the signal-id is not yet implemented it will perform the default calculation defined.

# Classes

**Main.java** -> As the name suggests, it's the starting point of the application. Its starts the HTTP server on port 8080.

**Algo.java** -> It's implemented in a third-party library, and we use it to perform the set of calculations

**SignalHandler.java** -> The upcall from the Trading System.

**SignalHandlerServer.java** -> The related implementation for the server which reads the parameter(_signal_) passed and calls the subsequent methods.

**Application.java** -> This is the main implementation for handling the signal passed. It reads a config file(_signalsConfig.xml_) filters out the logic defined for the signal-id passed and performs the set of commands defined. In case the signal-id is not available in the config it will perform the default command.

# Config file

The config file is defined in 'signalhandler/src/main/resources/signalsConfig.xml'. This file is the driving factor of the implementation

It's defined in teh below format:
```
<signals>
    <signal>
        <id>1</id>
        <command>setupUp</command>
        <command>setAlgoParam,1,60</command>
        <command>performCalc</command>
        <command>submitToMarket</command>
    </signal>
</signals>
```

When a new signal is to be defined or added we need to add a new child node 'signal' inside parent node 'signals' in the application should be able to handle the new defined signal.

In real-world if this config is placed inside a generic location or database(preferably a document based), updating only the config should suffice addition of new signal which in turn saves the time for deployment; if and when we don't have any new implementation changes.