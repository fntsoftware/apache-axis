/*
 * Copyright 2001-2004 The Apache Software Foundation.
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

package test.saaj;

import junit.framework.TestCase;
import org.apache.axis.components.logger.LogFactory;
import org.apache.commons.logging.Log;

import jakarta.activation.DataHandler;
import jakarta.xml.soap.AttachmentPart;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.Ignore;


/** Test the attachments load/save sample code.
 */
@Ignore
public class TestAttachmentSerialization extends TestCase {
    static Log log = LogFactory.getLog(TestAttachmentSerialization.class.getName());

    public void testAttachments() throws Exception {
        try {
            ByteArrayOutputStream bais = new ByteArrayOutputStream();
            int count1 = saveMsgWithAttachments(bais);
            int count2 = loadMsgWithAttachments(new ByteArrayInputStream(bais.toByteArray()));
            assertEquals(count1, count2);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Fault returned from test: " + e);
        }
    }

    public static final String MIME_MULTIPART_RELATED = "multipart/related";
    public static final String MIME_APPLICATION_DIME = "application/dime";
    public static final String NS_PREFIX = "jaxmtst";
    public static final String NS_URI = "http://www.jcommerce.net/soap/jaxm/TestJaxm";

    public int saveMsgWithAttachments(OutputStream os) throws Exception {
        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage msg = mf.createMessage();

        SOAPPart sp = msg.getSOAPPart();
        SOAPEnvelope envelope = sp.getEnvelope();
        SOAPHeader header = envelope.getHeader();
        SOAPBody body = envelope.getBody();

        SOAPElement el = header.addHeaderElement(envelope.createName("field4", NS_PREFIX, NS_URI));
        SOAPElement el2 = el.addChildElement("field4b", NS_PREFIX);
        SOAPElement el3 = el2.addTextNode("field4value");

        el = body.addBodyElement(envelope.createName("bodyfield3", NS_PREFIX, NS_URI));
        el2 = el.addChildElement("bodyfield3a", NS_PREFIX);
        el2.addTextNode("bodyvalue3a");
        el2 = el.addChildElement("bodyfield3b", NS_PREFIX);
        el2.addTextNode("bodyvalue3b");
        el2 = el.addChildElement("datefield", NS_PREFIX);

        AttachmentPart ap = msg.createAttachmentPart();
        ap.setContent("some attachment text...", "text/plain");
        msg.addAttachmentPart(ap);

        DataHandler dh = new DataHandler("test content", "text/plain");
        AttachmentPart ap2 = msg.createAttachmentPart(dh);
        ap2.setContentType("text/plain");
        msg.addAttachmentPart(ap2);

        // Test for Bug #17664
        if(msg.saveRequired()) {
            msg.saveChanges();
        }
        MimeHeaders headers = msg.getMimeHeaders();
        assertTrue(headers != null);
        String [] contentType = headers.getHeader("Content-Type");  
        assertTrue(contentType != null);
        
        msg.writeTo(os);
        os.flush();
        return msg.countAttachments();
    }

    public int loadMsgWithAttachments(InputStream is) throws Exception {
        MimeHeaders headers = new MimeHeaders();
        headers.setHeader("Content-Type", MIME_MULTIPART_RELATED);
        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage msg = mf.createMessage(headers, is);
        SOAPPart sp = msg.getSOAPPart();
        SOAPEnvelope envelope = sp.getEnvelope();
        assertTrue(sp != null);
        assertTrue(envelope != null);
        return msg.countAttachments();
    }
}


