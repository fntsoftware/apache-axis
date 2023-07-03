package test.wsdl.jaxrpchandlereval;

import javax.xml.namespace.QName;
import jakarta.xml.rpc.handler.Handler;
import jakarta.xml.rpc.handler.HandlerInfo;
import jakarta.xml.rpc.handler.MessageContext;
import jakarta.xml.rpc.handler.soap.SOAPMessageContext;
import jakarta.xml.soap.Name;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPHeaderElement;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;
import java.util.Iterator;
import jakarta.xml.soap.*;
import jakarta.xml.rpc.handler.soap.SOAPMessageContext;
import jakarta.xml.rpc.soap.*;
import jakarta.xml.rpc.*;

/**
 */
public class ClientHandler2 implements Handler {

private final static String _actorURI = "myActorURI";
/**
 * Constructor for ClientHandler2.
 */
public ClientHandler2() {
    System.out.println("ClientHandler2:Constructor");
}

/**
 * @see jakarta.xml.rpc.handler.Handler#handleRequest(MessageContext)
 */
public boolean handleRequest(MessageContext context) {
    System.out.println("ClientHandler2:handleRequest");
	HandlerTracker.addClientHandler("clienthandler2.handleRequest");

    if (context instanceof SOAPMessageContext) {
        try {
            SOAPMessageContext soapMsgCtx = (SOAPMessageContext)context;
            SOAPMessage soapMsg = soapMsgCtx.getMessage();
            SOAPPart sp = soapMsg.getSOAPPart();
            SOAPEnvelope se = sp.getEnvelope();
            SOAPBody sb = se.getBody();
            SOAPHeader sh = se.getHeader();

            Name name = se.createName("ClientHandler2-handleRequest", "", "");
            SOAPHeaderElement hdr = sh.addHeaderElement(name);
            hdr.addTextNode("Processed");

            String instruction = sb.toString();

            if (instruction.indexOf("client-throw-jaxrpcexception") >= 0) {
                soapMsgCtx.setProperty("fault", "Throwing a client side exception from ClientHandler2.handleRequest");

                QName faultcode = new QName("Testimg Exception",
                                            "http://example.org/security/");
                // According to JAX-RPC spec client handlers cannot throw SOAPFaultException
                throw new JAXRPCException();
            } else if (instruction.indexOf("client-return-false") >= 0) {
                soapMsgCtx.setProperty("fault", "Returning false from ClientHandler2.handleRequest");
                return false;
            }


        } catch (SOAPException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    return true;
}

/**
 * @see jakarta.xml.rpc.handler.Handler#handleResponse(MessageContext)
 */
public boolean handleResponse(MessageContext context) {
    System.out.println("ClientHandler2:handleResponse");
	HandlerTracker.addClientHandler("clienthandler2.handleResponse");

    if (context instanceof SOAPMessageContext) {
        try {
            SOAPMessageContext soapMsgCtx = (SOAPMessageContext)context;
            SOAPMessage soapMsg = soapMsgCtx.getMessage();
            if (soapMsg == null) {
                soapMsg = prepareError(soapMsgCtx);
            }
            SOAPPart sp = soapMsg.getSOAPPart();
            SOAPEnvelope se = sp.getEnvelope();
            SOAPBody sb = se.getBody();
            SOAPHeader sh = se.getHeader();

            Name name = se.createName("ClientHandler2-handleResponse", "", "");
            SOAPHeaderElement hdr = sh.addHeaderElement(name);
            hdr.addTextNode("Processed");

        } catch (SOAPException ex) {
            ex.printStackTrace();
            return false;
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
             throw new JAXRPCException(ex);
         }

    }
    return true;
}

public SOAPMessage prepareError(SOAPMessageContext soapMsgCtx) throws Exception{
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMsg = messageFactory.createMessage();
        soapMsgCtx.setMessage(soapMsg);

        String fault = (String)soapMsgCtx.getProperty("fault");
        if (fault != null) {
            SOAPFault soapFault =
                soapMsg.getSOAPPart().getEnvelope().getBody().addFault();
            soapFault.setFaultString(fault);
        }
        return soapMsg;
}
/**
 * @see jakarta.xml.rpc.handler.Handler#handleFault(MessageContext)
 */
public boolean handleFault(MessageContext context) {
    System.out.println("ClientHandler2:handleFault");
	HandlerTracker.addClientHandler("clienthandler2.handleFault");
    if (context instanceof SOAPMessageContext) {
        try {
            SOAPMessageContext soapMsgCtx = (SOAPMessageContext)context;
            SOAPMessage soapMsg = soapMsgCtx.getMessage();
            if (soapMsg == null) {
                soapMsg = prepareError(soapMsgCtx);
            }
            SOAPPart sp = soapMsg.getSOAPPart();
            SOAPEnvelope se = sp.getEnvelope();
            SOAPBody sb = se.getBody();
            SOAPHeader sh = se.getHeader();

            Name name = se.createName("ClientHandler2-handleFault", "", "");
            SOAPHeaderElement hdr = sh.addHeaderElement(name);
            hdr.addTextNode("Processed");
        } catch (SOAPException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();

             throw new JAXRPCException(ex);
         }
    }
    return true;
}

/**
 * @see jakarta.xml.rpc.handler.Handler#init(HandlerInfo)
 */
public void init(HandlerInfo config) {
    System.out.println("ClientHandler2.init");

}

/**
 * @see jakarta.xml.rpc.handler.Handler#destroy()
 */
public void destroy() {
    System.out.println("ClientHandler2.destroy");

}

/**
 * @see jakarta.xml.rpc.handler.Handler#getHeaders()
 */
public QName[] getHeaders() {
    System.out.println("ClientHandler2.getheaders");

    return null;
}

}

