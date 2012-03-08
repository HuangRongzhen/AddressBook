/**
 * Created : 2012-1-4
 * 
 * Copyright (c) 2012 Ericsson (China) Communications Co.,Ltd. All rights reserved. The Copyright to the computer program(s) herein is the property of Ericsson
 * (China) Communications Co.,Ltd. The program(s) may be used and/or copied with the written permission from Ericsson AB or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the program(s) have been supplied.
 */
package com.ericsson.javatraining.contacts.util;

import java.io.ByteArrayOutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import com.ericsson.javatraining.contacts.Contact;

/**
 * 
 * @author eronhua
 */
public final class ContactUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactUtil.class);

    public static String getContactString(Contact contact) {
        return contact.getName() + "\t" + contact.getPhoneNumber() + "\t" + contact.getAddress();
    }

    private ContactUtil() {
        super();
    }

    public static String xmlToString(Document document) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            try {
                transformer.transform(new DOMSource(document), new StreamResult(outputStream));
            } catch (TransformerException e) {
                LOGGER.error("Exception capture when transforming XML.", e);
            }

        } catch (TransformerConfigurationException e) {
            LOGGER.error("Exception capture when create XML transformer.", e);
        }
        return outputStream.toString();
    }
}