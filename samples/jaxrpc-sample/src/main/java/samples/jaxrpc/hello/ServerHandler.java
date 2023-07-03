package samples.jaxrpc.hello;

public class ServerHandler implements jakarta.xml.rpc.handler.Handler {
    public ServerHandler() {
    }

    public boolean handleRequest(jakarta.xml.rpc.handler.MessageContext context) {
        System.out.println("ServerHandler: In handleRequest");
        return true;
    }

    public boolean handleResponse(jakarta.xml.rpc.handler.MessageContext context) {
        System.out.println("ServerHandler: In handleResponse");
        return true;
    }

    public boolean handleFault(jakarta.xml.rpc.handler.MessageContext context) {
        System.out.println("ServerHandler: In handleFault");
        return true;
    }

    public void init(jakarta.xml.rpc.handler.HandlerInfo config) {
    }

    public void destroy() {
    }

    public javax.xml.namespace.QName[] getHeaders() {
        return null;
    }
}

