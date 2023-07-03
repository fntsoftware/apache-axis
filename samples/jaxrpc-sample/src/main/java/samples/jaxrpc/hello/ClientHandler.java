package samples.jaxrpc.hello;

public class ClientHandler implements jakarta.xml.rpc.handler.Handler {
    public ClientHandler() {
    }

    public boolean handleRequest(jakarta.xml.rpc.handler.MessageContext context) {
        System.out.println("ClientHandler: In handleRequest");
        return true;
    }

    public boolean handleResponse(jakarta.xml.rpc.handler.MessageContext context) {
        System.out.println("ClientHandler: In handleResponse");
        return true;
    }

    public boolean handleFault(jakarta.xml.rpc.handler.MessageContext context) {
        System.out.println("ClientHandler: In handleFault");
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

