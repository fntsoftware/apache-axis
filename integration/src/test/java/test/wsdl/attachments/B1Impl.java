/**
 * B1Impl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package test.wsdl.attachments;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import javax.xml.rpc.holders.FloatHolder;
import javax.xml.rpc.holders.StringHolder;

import org.apache.axis.holders.ImageHolder;

public class B1Impl implements test.wsdl.attachments.Pt1 {

    private MimeMultipart createMimeMultipart(String data) throws MessagingException {
        // create the root multipart
        MimeMultipart mpRoot = new MimeMultipart("mixed");
        
        // Add text
        MimeBodyPart mbp1 = new MimeBodyPart();
        mbp1.setText(data);
        mpRoot.addBodyPart(mbp1);
        return mpRoot;
    }

    public void getCompanyInfo(String tickerSymbol, FloatHolder result, StringHolder docs, ImageHolder logo) throws RemoteException {
        result.value = 13.4f;
        docs.value = tickerSymbol;
        BufferedImage image = new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.createGraphics();
        g.drawLine(0, 0, 24, 24);
        g.dispose();
        logo.value = image;
    }

    public java.lang.String getCompanyInfo2(float result, java.lang.String docs, java.awt.Image logo) throws java.rmi.RemoteException {
        return docs;
    }

    public void inputPlainText(java.lang.String body) throws java.rmi.RemoteException {
    }

    public java.lang.String inoutPlainText(java.lang.String body) throws java.rmi.RemoteException {
        return body;
    }

    public java.lang.String echoPlainText(java.lang.String body) throws java.rmi.RemoteException {
        return body;
    }

    public java.lang.String outputPlainText() throws java.rmi.RemoteException {
        return "OutputPlainText";
    }

    public void inputMimeMultipart(jakarta.mail.internet.MimeMultipart body) throws java.rmi.RemoteException {
    }

    public jakarta.mail.internet.MimeMultipart inoutMimeMultipart(jakarta.mail.internet.MimeMultipart body) throws java.rmi.RemoteException {
        return body;
    }

    public jakarta.mail.internet.MimeMultipart echoMimeMultipart(jakarta.mail.internet.MimeMultipart body) throws java.rmi.RemoteException {
        return body;
    }

    public jakarta.mail.internet.MimeMultipart outputMimeMultipart() throws java.rmi.RemoteException {
        try {
            return createMimeMultipart("outputMimeMultipart");
        } catch (MessagingException me) {
            throw new java.rmi.RemoteException(me.getMessage(), me);
        }
    }

    public org.apache.axis.attachments.OctetStream echoAttachment(org.apache.axis.attachments.OctetStream in) throws java.rmi.RemoteException {
        return in;
    }
}
