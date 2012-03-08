/**
 * Created : 2012-1-3
 * 
 * Copyright (c) 2012 Ericsson (China) Communications Co.,Ltd. All rights reserved. The Copyright to the computer program(s) herein is the property of Ericsson
 * (China) Communications Co.,Ltd. The program(s) may be used and/or copied with the written permission from Ericsson AB or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the program(s) have been supplied.
 */
package com.ericsson.javatraining.contacts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.javatraining.contacts.searchtool.SearchTool;
import com.ericsson.javatraining.contacts.storage.Storage;

public class ContactFrame extends JFrame {
    private Storage storage;
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactFrame.class);

    private final JTextField inputName = new JTextField(100);
    private final JTextField inputPhoneNumber = new JTextField(100);
    private final JTextField inputAddress = new JTextField(100);
    private final JTextField inputSearchKey = new JTextField(100);

    private final JTextArea outPutBox = new JTextArea(4, 100);

    private final JButton buttonAddContact = new JButton("Add contact");
    private final JButton buttonSearchContacts = new JButton("Search contact");
    private final JButton buttonDeleteContacts = new JButton("Delete contact");

    private void initGUI() {
        JPanel pane = new JPanel();
        pane.setLayout(null);

        // Draw "Name" input box
        JLabel labelInputName = new JLabel("Name: ");
        pane.add(labelInputName);
        pane.add(inputName);
        labelInputName.setBounds(10, 10, 100, 30);
        inputName.setBounds(150, 10, 500, 30);

        // Draw "Phone Number" input box
        JLabel labelInputPhoneNumber = new JLabel("Phone Number: ");
        pane.add(labelInputPhoneNumber);
        pane.add(inputPhoneNumber);
        labelInputPhoneNumber.setBounds(10, 50, 100, 30);
        inputPhoneNumber.setBounds(150, 50, 500, 30);

        // Draw "Address" input box
        JLabel labelInputAddress = new JLabel("Address: ");
        pane.add(labelInputAddress);
        pane.add(inputAddress);
        labelInputAddress.setBounds(10, 90, 100, 30);
        inputAddress.setBounds(150, 90, 500, 30);

        // Draw "Search key" input box
        JLabel labelInputSearchKey = new JLabel("Search Key: ");
        pane.add(labelInputSearchKey);
        pane.add(inputSearchKey);
        labelInputSearchKey.setBounds(10, 200, 100, 30);
        inputSearchKey.setBounds(150, 200, 500, 30);

        // Draw "Output" text box
        JLabel labelOutput = new JLabel("Search results: ");
        pane.add(labelOutput);
        pane.add(outPutBox);
        labelOutput.setBounds(10, 300, 100, 30);
        outPutBox.setBounds(150, 300, 500, 200);
        // Set Output style
        outPutBox.setLineWrap(true);
        outPutBox.setWrapStyleWord(true);

        // Draw "Add contact" button
        pane.add(buttonAddContact);
        buttonAddContact.setBounds(665, 45, 150, 40);

        // Draw "Search contact" button
        pane.add(buttonSearchContacts);
        buttonSearchContacts.setBounds(665, 195, 150, 40);

        // Draw "Delete contact" button
        pane.add(buttonDeleteContacts);
        buttonDeleteContacts.setBounds(665, 245, 150, 40);
        setContentPane(pane);
        show();
    }

    private void addAction() {
        buttonAddContact.addActionListener(new ButtonAddContact());
        buttonSearchContacts.addActionListener(new ButtonSearchContactByPhoneNumber());
        buttonDeleteContacts.addActionListener(new ButtonDeleteContact());
    }

    private void initData() {
        storage = new Storage("Contacts.xml");
        try {
            storage.loadData();
        } catch (IOException e) {
            LOGGER.error("Save data IOException.", e);
        }
    }

    public ContactFrame() {
        super("Contact");
        setSize(850, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initGUI();
        addAction();
        initData();
    }

    public void setOutputBox(String content) {
        outPutBox.setText(content);
    }

    public void appendOutputBox(String content) {
        outPutBox.append(content);
    }

    public static void main(String args[]) {
        new ContactFrame();
    }

    class ButtonAddContact implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = inputName.getText();
            String phoneNumber = inputPhoneNumber.getText();
            String address = inputAddress.getText();

            setOutputBox("Start to store contact......\n");

            storage.getContacts().add(new Contact(name, phoneNumber, address));
            LOGGER.info("Add contact: name:" + name + "\tPhone Number:" + phoneNumber + "\tAddress:" + address);
            try {
                storage.saveData();
            } catch (IOException e1) {
                LOGGER.error("Save data IOException when adding contact.");
            }
            appendOutputBox("Finish store this record.");
        }
    }

    class ButtonSearchContactByPhoneNumber implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchResult = null;
            searchResult = SearchTool.getSearchRecordsString(inputSearchKey.getText(), storage);

            setOutputBox(searchResult);
        }
    }

    class ButtonDeleteContact implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchKey = inputSearchKey.getText();
            List<Contact> contactsList = storage.getContacts();
            int deleteCounter = 0;

            if (searchKey.isEmpty()) {
                return;
            }

            setOutputBox("Start to remove contact......\n");

            for (int i = 0; i < contactsList.size(); i++) {
                if (contactsList.get(i).toString().contains(searchKey)) {
                    String outPutString = "Remove contact:\nname:" + contactsList.get(i).getName() + "\tPhone Number:" + contactsList.get(i).getPhoneNumber()
                            + "\tAddress:" + contactsList.get(i).getAddress();
                    appendOutputBox(outPutString + "\n");
                    LOGGER.info(outPutString);
                    contactsList.remove(i);
                    i--;
                    deleteCounter++;
                }
            }

            if (0 == deleteCounter) {
                appendOutputBox("No record is deleted.\n");
            }

            appendOutputBox("Finished.");

            try {
                storage.saveData();
            } catch (IOException e1) {
                LOGGER.error("Save data IOException when deleting contact.");
            }
        }
    }
}