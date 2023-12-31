package test.functional;

import junit.framework.TestCase;
import org.apache.axis.AxisEngine;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import test.HttpTestUtil;

import javax.xml.messaging.URLEndpoint;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.Name;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPBodyElement;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPMessage;

/**
 * Test string encoding roundtrip.
 */
public class TestEncoding extends TestCase {
    Call call = null;

    public TestEncoding(String s) {
        super(s);
    }

    protected void setUp() throws Exception {
        if (call == null) {
            Service service = new Service();
            service.getEngine().setOption(AxisEngine.PROP_XML_ENCODING, "UTF-8");
            call = (Call) service.createCall();
            call.setTargetEndpointAddress(HttpTestUtil.getTestEndpoint("http://localhost:8080/axis/EchoHeaders.jws"));
        }
    }

    private void runtest(String send, String get) throws Exception {
        String ret = (String) call.invoke("echo", new Object[]{send});
        assertEquals(ret, get);
    }

    private void runtest(String value) throws Exception {
        runtest(value, value);
    }

    public void testSimpleString() throws Exception {
        runtest("a simple string");
    }

    public void testStringWithApostrophes() throws Exception {
        runtest("this isn't a simple string");
    }

    public void testStringWithEntities() throws Exception {
        runtest("&amp;&lt;&gt;&apos;&quot;", "&amp;&lt;&gt;&apos;&quot;");
    }

    public void testStringWithRawEntities() throws Exception {
        runtest("&<>'\"", "&<>'\"");
    }

    public void testStringWithLeadingAndTrailingSpaces() throws Exception {
        runtest("          centered          ");
    }

    public void testWhitespace() throws Exception {
        runtest(" \n \t "); // note: \r fails
    }

    public void testFrenchAccents() throws Exception {
        runtest("\u00e0\u00e2\u00e4\u00e7\u00e8\u00e9\u00ea\u00eb\u00ee\u00ef\u00f4\u00f6\u00f9\u00fb\u00fc");
    }

    public void testFrenchAccents2() throws Exception {
        runtest("Une chane avec des caractres accentus");
    }

    public void testGermanUmlauts() throws Exception {
        runtest(" Some text \u00df with \u00fc special \u00f6 chars \u00e4.");
    }

    public void testWelcomeUnicode() throws Exception {
        // welcome in several languages
        runtest(
                "Chinese (trad.) : \u6b61\u8fce  \n" +
                "Greek : \u03ba\u03b1\u03bb\u03ce\u03c2 \u03bf\u03c1\u03af\u03c3\u03b1\u03c4\u03b5 \n" +
                "Japanese : \u3088\u3046\u3053\u305d");
    }

    public void testSynchronization() throws Exception {
        SOAPConnectionFactory scFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection con = scFactory.createConnection();

        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage();
        String requestEncoding = "UTF-16";
        message.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, requestEncoding);

        SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
        SOAPBody body = envelope.getBody();

        Name bodyName = envelope.createName("echo");
        SOAPBodyElement bodyElement = body.addBodyElement(bodyName);

        Name name = envelope.createName("arg0");
        SOAPElement symbol = bodyElement.addChildElement(name);
        symbol.addTextNode("Hello");

        URLEndpoint endpoint = new URLEndpoint(HttpTestUtil.getTestEndpoint("http://localhost:8080/axis/EchoHeaders.jws").toString());
        SOAPMessage response = con.call(message, endpoint);
        String responseEncoding = (String) response.getProperty(SOAPMessage.CHARACTER_SET_ENCODING);
        assertEquals(requestEncoding.toLowerCase(), responseEncoding.toLowerCase());
    }
}
