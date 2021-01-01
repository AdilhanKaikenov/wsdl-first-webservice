package com.kaikenov.ws.soap.handler;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Iterator;
import java.util.Set;

public class SiteHandler implements SOAPHandler<SOAPMessageContext> {
    @Override
    public Set<QName> getHeaders() {
        System.out.println("getHeaders");
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {

        System.out.println("handleMessage");

        Boolean isResponse = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (!isResponse) {
            SOAPMessage soapMessage = context.getMessage();
            try {
                SOAPEnvelope envelope = soapMessage.getSOAPPart().getEnvelope();
                SOAPHeader header = envelope.getHeader();
                Iterator childElements = header.getChildElements();
                while (childElements.hasNext()) {
                    Node eachNode = (Node) childElements.next();
                    String localName = eachNode.getLocalName();
                    if (localName != null && localName.equals("SiteName")) {
                        System.out.println("Site Name is ====> " + eachNode.getValue());
                    }
                }

            } catch (SOAPException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Response on the way");
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        System.out.println("handleFault");
        return false;
    }

    @Override
    public void close(MessageContext context) {
        System.out.println("close");
    }
}
