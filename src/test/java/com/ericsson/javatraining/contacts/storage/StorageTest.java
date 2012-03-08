/**
 * Created : 2012-2-23
 * 
 * Copyright (c) 2012 Ericsson (China) Communications Co.,Ltd. All rights reserved. The Copyright to the computer program(s) herein is the property of Ericsson
 * (China) Communications Co.,Ltd. The program(s) may be used and/or copied with the written permission from Ericsson AB or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the program(s) have been supplied.
 */
package com.ericsson.javatraining.contacts.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.ericsson.javatraining.contacts.Contact;
import com.ericsson.javatraining.contacts.searchtool.SearchTool;

/**
 * 
 * @author eronhua
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Storage.class, LoggerFactory.class, DocumentBuilderFactory.class })
public class StorageTest {
    private Storage testStorage;
    private final FileWriter mockFileWriter = mock(FileWriter.class);
    private static final Logger mockLogger = mock(Logger.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mockStatic(LoggerFactory.class);
        when(LoggerFactory.getLogger(Storage.class)).thenReturn(mockLogger);
        testStorage = new Storage("TestContacts.xml");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        testStorage.DestoryData();
    }

    @Test
    public void checkDataTest() throws IOException {
        String testContactRecord = null;

        testStorage.loadData();
        testContactRecord = SearchTool.getSearchRecordsString("Alex", testStorage);
        assertEquals(testContactRecord, "Name\tPhoneNumber\tAddress\nAlex\t123456\tA street room5\n");
    }

    @Test
    public void setDataFileTest() throws IOException {
        testStorage.setDataFile("testPath:\testFile");
        assertEquals(testStorage.getDataFile(), "testPath:\testFile");
    }

    @Test
    public void saveDataTest() throws Exception {
        List<Contact> testContacts = new ArrayList<Contact>();
        whenNew(FileWriter.class).withArguments(anyString()).thenReturn(mockFileWriter);
        testContacts = testStorage.getContacts();
        testContacts.add(new Contact("A", "12345678901", "Address1"));
        testContacts.add(new Contact("B", "92345678901", "Address2"));
        testStorage.saveData();

        verify(mockFileWriter).write(anyString());
        verify(mockFileWriter).close();
    }

    @Test
    public void saveDataExceptionTest() throws Exception {
        DocumentBuilderFactory mockDocumentBuilderFactory = mock(DocumentBuilderFactory.class);
        ParserConfigurationException testParserConfigurationException = new ParserConfigurationException("test ParserConfigurationException");

        mockStatic(DocumentBuilderFactory.class);
        when(DocumentBuilderFactory.newInstance()).thenReturn(mockDocumentBuilderFactory);
        when(mockDocumentBuilderFactory.newDocumentBuilder()).thenThrow(testParserConfigurationException);
        try {
            testStorage.loadData();
            testStorage.saveData();
        } catch (Exception e) {
            fail("saveDataExceptionTest exception captured.");
        }
    }

    @Test
    public void loadDataSAXExceptionTest() throws Exception {
        DocumentBuilderFactory mockDocumentBuilderFactory = mock(DocumentBuilderFactory.class);
        DocumentBuilder mockDocumentBuilder = mock(DocumentBuilder.class);
        SAXException testSAXException = new SAXException("test SAXException");

        mockStatic(DocumentBuilderFactory.class);
        when(DocumentBuilderFactory.newInstance()).thenReturn(mockDocumentBuilderFactory);
        when(mockDocumentBuilderFactory.newDocumentBuilder()).thenReturn(mockDocumentBuilder);

        when(mockDocumentBuilder.parse(anyString())).thenThrow(testSAXException);

        try {
            testStorage.loadData();
        } catch (Exception e) {
            fail("loadDataExceptionTest exception captured.");
        }
    }

    @Test
    public void loadDataParserConfigurationExceptionTest() throws Exception {
        DocumentBuilderFactory mockDocumentBuilderFactory = mock(DocumentBuilderFactory.class);
        ParserConfigurationException testParserConfigurationException = new ParserConfigurationException("test ParserConfigurationException");

        mockStatic(DocumentBuilderFactory.class);
        when(DocumentBuilderFactory.newInstance()).thenReturn(mockDocumentBuilderFactory);
        when(mockDocumentBuilderFactory.newDocumentBuilder()).thenThrow(testParserConfigurationException);

        try {
            testStorage.loadData();
        } catch (Exception e) {
            fail("loadDataParserConfigurationExceptionTest exception captured.");
        }
    }
}
