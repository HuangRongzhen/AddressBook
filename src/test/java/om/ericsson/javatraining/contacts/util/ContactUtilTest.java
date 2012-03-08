/**
 * Created : 2012-2-25
 * 
 * Copyright (c) 2012 Ericsson (China) Communications Co.,Ltd. All rights reserved. The Copyright to the computer program(s) herein is the property of Ericsson
 * (China) Communications Co.,Ltd. The program(s) may be used and/or copied with the written permission from Ericsson AB or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the program(s) have been supplied.
 */
package om.ericsson.javatraining.contacts.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.reflect.Whitebox.invokeConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.ericsson.javatraining.contacts.Contact;
import com.ericsson.javatraining.contacts.util.ContactUtil;

/**
 * 
 * @author eronhua
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ContactUtil.class, LoggerFactory.class, TransformerFactory.class })
public class ContactUtilTest {
    private static final Logger mockLogger = mock(Logger.class);
    private DocumentBuilder builder;
    private Document document;
    private Transformer transformer = null;
    private TransformerFactory transformerFactory;
    private ByteArrayOutputStream outputStream;
    private TransformerFactory mockTransformerFactory;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        //mockLogger = mock(Logger.class);
        mockStatic(LoggerFactory.class);
        when(LoggerFactory.getLogger(ContactUtil.class)).thenReturn(mockLogger);

        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = builder.parse("TestContacts.xml");
        document.normalize();

        transformerFactory = TransformerFactory.newInstance();
        transformer = transformerFactory.newTransformer();

        outputStream = new ByteArrayOutputStream();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.ericsson.javatraining.contacts.util.ContactUtil#getContactString(com.ericsson.javatraining.contacts.Contact)}.
     */
    @Test
    public final void testGetContactString() {
        Contact testContact = new Contact("Marry", "12345678901", "Room 1 Street 1");
        String convertResult = ContactUtil.getContactString(testContact);

        assertEquals(convertResult, "Marry\t12345678901\tRoom 1 Street 1");
    }

    /**
     * Test method for {@link com.ericsson.javatraining.contacts.util.ContactUtil#getContactString(com.ericsson.javatraining.contacts.Contact)}.
     * 
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     */
    @Test
    public final void XMLtoStringTest() throws ParserConfigurationException, SAXException, IOException, TransformerException {
        transformer.transform(new DOMSource(document), new StreamResult(outputStream));

        assertEquals(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root root1=\"root1\">\r\n  <Contact>\r\n    <name>Alex</name>\r\n    <telephone>123456</telephone>\r\n    <address>A street room5</address>\r\n  </Contact>\r\n</root>",
                outputStream.toString());
    }

    /**
     * Test method for {@link com.ericsson.javatraining.contacts.util.ContactUtil#getContactString(com.ericsson.javatraining.contacts.Contact)}.
     * @throws TransformerConfigurationException 
     * 
     */
    @Test
    public final void XMLtoStringCreateTransformerFailTest() throws TransformerConfigurationException {
        TransformerConfigurationException testTransformerConfigurationException = new TransformerConfigurationException("Test TransformerConfigurationException");
        mockTransformerFactory = mock(TransformerFactory.class);
        mockStatic(TransformerFactory.class);
        when(TransformerFactory.newInstance()).thenReturn(mockTransformerFactory);
        Transformer mockTransformer = mock(Transformer.class);
        when(mockTransformerFactory.newTransformer()).thenThrow(testTransformerConfigurationException);

        try {
            ContactUtil.XMLtoString(document);
            verify(mockLogger).error(anyString(), eq(testTransformerConfigurationException));
        } catch (Exception e) {
            fail("XMLtoStringCreateTransformerFailTest exception captured.");            
        }
    }
    
    /**
     * Test method for {@link com.ericsson.javatraining.contacts.util.ContactUtil#getContactString(com.ericsson.javatraining.contacts.Contact)}.
     * @throws TransformerException 
     * 
     */
    @Test
    public final void XMLtoStringTransformerFailTest() throws TransformerException {
        TransformerException testTransformerException = new TransformerException("Test TransformerException");
        mockTransformerFactory = mock(TransformerFactory.class);
        mockStatic(TransformerFactory.class);
        when(TransformerFactory.newInstance()).thenReturn(mockTransformerFactory);
        Transformer mockTransformer = mock(Transformer.class);
        when(mockTransformerFactory.newTransformer()).thenReturn(mockTransformer);
        doThrow(testTransformerException).when(mockTransformer).transform(any(Source.class), any(Result.class));
        //when(mockTransformer.transform(any(Source.class), any(Result.class))).thenThrow(testTransformerException);

        try {
            ContactUtil.XMLtoString(document);
            verify(mockLogger).error(anyString(), eq(testTransformerException));
        } catch (Exception e) {
            fail("XMLtoStringTransformerFailTest exception captured");            
        }
    }

    /**
     * Sorry but this is used to show off PowerMock and to get higher coverage.
     * 
     * @throws Exception
     *             if we can not invoke the private constructor
     */
    @Test
    public void testPrivateConstructor() throws Exception {
        invokeConstructor(ContactUtil.class);
    }
}
