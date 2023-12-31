package test.saaj;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;

public class TestMessageProperty2 extends junit.framework.TestCase {
    private static String GoodSoapMessage = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:tns=\"http://helloservice.org/wsdl\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><tns:hello><String_1 xsi:type=\"xsd:string\">&lt;Bozo&gt;</String_1></tns:hello></soap:Body></soap:Envelope>";

    private SOAPMessage createTestMessage(String encoding, boolean xmlDecl) throws Exception {
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage();
        SOAPPart sp = message.getSOAPPart();
        SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
        SOAPHeader header = envelope.getHeader();

        ByteArrayInputStream bais =
                new ByteArrayInputStream(GoodSoapMessage.getBytes(encoding));
        StreamSource ssrc = new StreamSource(bais);
        sp.setContent(ssrc);

        message.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, encoding);
        message.setProperty(SOAPMessage.WRITE_XML_DECLARATION, xmlDecl ? "true" : "false");

        return message;
    }


    private SOAPMessage createMessageFromInputStream(InputStream is) throws Exception {
        MessageFactory mf = MessageFactory.newInstance();
        return mf.createMessage(new MimeHeaders(), is);
    }

    public void testUTF8withXMLDecl() throws Exception {
        SOAPMessage msg = createTestMessage("UTF-8", true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        msg.writeTo(baos);
        String xml = new String(baos.toByteArray(),"UTF-8");
        assertTrue(xml.indexOf("UTF-8") != -1);
        assertTrue(xml.indexOf("&lt;Bozo&gt;") != -1);
    }

    public void testUTF16withXMLDecl() throws Exception {
        SOAPMessage msg = createTestMessage("UTF-16", true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        msg.writeTo(baos);
        String xml = new String(baos.toByteArray(),"UTF-16");
        assertTrue(xml.indexOf("UTF-16") != -1);
        assertTrue(xml.indexOf("&lt;Bozo&gt;") != -1);
    }
}
