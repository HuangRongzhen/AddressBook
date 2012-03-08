/**
 * Created : 2012-1-3
 * 
 * Copyright (c) 2012 Ericsson (China) Communications Co.,Ltd. All rights reserved. The Copyright to the computer program(s) herein is the property of Ericsson
 * (China) Communications Co.,Ltd. The program(s) may be used and/or copied with the written permission from Ericsson AB or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the program(s) have been supplied.
 */
package com.ericsson.javatraining.contacts.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ericsson.javatraining.contacts.Contact;
import com.ericsson.javatraining.contacts.util.ContactUtil;

/**
 * 
 * @author eronhua
 */
public class Storage {
    // private static String filename = "Contacts.txt";
    private static String filename = "Contacts.xml";
    private final static List<Contact> listContacts = new ArrayList<Contact>();
    private static final Logger LOGGER = LoggerFactory.getLogger(Storage.class);

    /**
     * Create the storage object with certain filename.
     * 
     */
    public Storage(String filename) {
        setDataFile(filename);
    }

    public void DestoryData() {
        listContacts.clear();
    }

    /**
     * Get contacts list from storage.
     * 
     */
    public List<Contact> getContacts() {
        return listContacts;
    }

    /**
     * Assign the data file to store the contacts.
     * 
     * Parameters:
     * 
     * @param fileName
     *            - File name with absolute path.
     */
    public void setDataFile(String fileName) {
        filename = fileName;
    }

    /**
     * Get the filename which is set.
     * 
     */
    public String getDataFile() {
        return filename;
    }

    /**
     * Flush the data to the persistence storage.
     * 
     */
    public void saveData() throws IOException {
        String data = "";
        Document document = null;

        try {
            document = convertContactsToXMLDocument();
            data = ContactUtil.xmlToString(document);

            // Save to file
            FileWriter writer = new FileWriter(filename);
            writer.write(data);
            writer.close();
            LOGGER.info("Data saved successed!");
        } catch (ParserConfigurationException e) {
            LOGGER.error("saveData exception captured.", e);
        }
    }

    /**
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private Document convertContactsToXMLDocument() throws IOException, ParserConfigurationException {
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.newDocument();
        document.setXmlVersion("1.0");

        Element root = document.createElement("root");

        document.appendChild(root);

        for (Contact contact : listContacts) {
            Element contactElement = document.createElement("Contact");

            Element name = document.createElement("name");
            name.setTextContent(contact.getName());
            contactElement.appendChild(name);

            Element telephone = document.createElement("telephone");
            telephone.setTextContent(contact.getPhoneNumber());
            contactElement.appendChild(telephone);

            Element address = document.createElement("address");
            address.setTextContent(contact.getAddress());
            contactElement.appendChild(address);
            root.appendChild(contactElement);
        }
        return document;
    }

    /**
     * Load data from the persistence storage.
     * 
     */
    public void loadData() throws IOException {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            try {
                Document document = builder.parse(filename);
                document.normalize();
                NodeList nodeList = document.getElementsByTagName("Contact");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    listContacts.add(getContactFromData(nodeList.item(i).getChildNodes()));
                }
            } catch (SAXException e) {
                LOGGER.error("loadData SAXException captured.", e);
            }
        } catch (ParserConfigurationException e) {
            LOGGER.error("loadData ParserConfigurationException captured.", e);
        }
    }

    private Contact getContactFromData(NodeList nodeList) {
        return new Contact(nodeList.item(1).getTextContent(), // name
                nodeList.item(3).getTextContent(), // phoneNumber
                nodeList.item(5).getTextContent() // address
        );
    }
}
