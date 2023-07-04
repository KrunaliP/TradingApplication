package com.deutschebank.interview.tradingapplication;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;

/**
 * This is your team’s code and should be changed as you see fit.
 */
class Application implements SignalHandler {

    Algo algo = new Algo();

    public void handleSignal(int signal) {

        try {
            //reading the config xml
            String configFilePath = "signalhandler/src/main/resources/signalsConfig.xml";

            //this if condition is currently for testing purpose
            if (System.getProperty("user.dir").endsWith("signalhandler"))
                configFilePath = "src/main/resources/signalsConfig.xml";

            File file = new File(configFilePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            String xpathExpression = "/signals/signal[id =" + signal + "]";
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();

            XPathExpression expr = xpath.compile(xpathExpression);
            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            if (nodeList.getLength() == 1) {
                Node node = nodeList.item(0);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    NodeList commandList = eElement.getElementsByTagName("command");
                    for (int index = 0; index < commandList.getLength(); index++) {
                        String currentCommand = eElement.getElementsByTagName("command").item(index).getTextContent();
                        if (currentCommand.equals("doAlgo")) algo.doAlgo();
                        else if (currentCommand.equals("cancelTrades")) algo.cancelTrades();
                        else if (currentCommand.equals("reverse")) algo.reverse();
                        else if (currentCommand.equals("submitToMarket")) algo.submitToMarket();
                        else if (currentCommand.equals("performCalc")) algo.performCalc();
                        else if (currentCommand.equals("setUp")) algo.setUp();
                        else if (currentCommand.startsWith("setAlgoParam")) {
                            String[] currentCommandList = currentCommand.split(",");
                            algo.setAlgoParam(Integer.parseInt(currentCommandList[1]), Integer.parseInt(currentCommandList[2]));
                        }
                    }
                }
            } else {
                algo.cancelTrades();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        algo.doAlgo();
    }
}
