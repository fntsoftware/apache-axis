/*
 * Copyright 2002-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package samples.jaxm;

import javax.xml.messaging.URLEndpoint;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.Name;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPBodyElement;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;
import java.util.Iterator;

public class DelayedStockQuote {
    private final String url;
    
    public DelayedStockQuote() {
        this("http://64.124.140.30/soap");
    }
    
    public DelayedStockQuote(String url) {
        this.url = url;
    }
    
    public static void main(String[] args) throws Exception {
        DelayedStockQuote stockQuote = new DelayedStockQuote();
        System.out.print("The last price for SUNW is " + stockQuote.getStockQuote("SUNW"));
    }

    public String getStockQuote(String tickerSymbol) throws Exception {
        SOAPConnectionFactory scFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection con = scFactory.createConnection();

        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage();

        SOAPPart soapPart = message.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();

        SOAPHeader header = envelope.getHeader();
        SOAPBody body = envelope.getBody();

        header.detachNode();

        Name bodyName = envelope.createName("getQuote", "n", "urn:xmethods-delayed-quotes");
        SOAPBodyElement gltp = body.addBodyElement(bodyName);

        Name name = envelope.createName("symbol");
        SOAPElement symbol = gltp.addChildElement(name);
        symbol.addTextNode(tickerSymbol);

        URLEndpoint endpoint = new URLEndpoint(url);
        SOAPMessage response = con.call(message, endpoint);
        con.close();

        SOAPPart sp = response.getSOAPPart();
        SOAPEnvelope se = sp.getEnvelope();
        SOAPBody sb = se.getBody();
        Iterator it = sb.getChildElements();
        while (it.hasNext()) {
            SOAPBodyElement bodyElement = (SOAPBodyElement) it.next();
            Iterator it2 = bodyElement.getChildElements();
            while (it2.hasNext()) {
                SOAPElement element2 = (SOAPElement) it2.next();
                return element2.getValue();
            }
        }
        return null;
    }
}

