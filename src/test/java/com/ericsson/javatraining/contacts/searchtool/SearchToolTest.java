/**
 * Created : 2012-2-24
 * 
 * Copyright (c) 2012 Ericsson (China) Communications Co.,Ltd. All rights reserved. The Copyright to the computer program(s) herein is the property of Ericsson
 * (China) Communications Co.,Ltd. The program(s) may be used and/or copied with the written permission from Ericsson AB or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the program(s) have been supplied.
 */
package com.ericsson.javatraining.contacts.searchtool;

import static org.junit.Assert.assertEquals;
import static org.powermock.reflect.Whitebox.invokeConstructor;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ericsson.javatraining.contacts.storage.Storage;

/**
 * 
 * @author eronhua
 */
public class SearchToolTest {
    private Storage testStorage;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        testStorage = new Storage("TestContacts.xml");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        testStorage.DestoryData();
    }

    /**
     * Test method for
     * {@link com.ericsson.javatraining.contacts.searchtool.SearchTool#getSearchRecordsString(java.lang.String, com.ericsson.javatraining.contacts.storage.Storage)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testSearchRecordsWithNoRecord() throws IOException {
        String searchResult;
        testStorage.loadData();
        searchResult = SearchTool.getSearchRecordsString("NoSuchName", testStorage);

        assertEquals(searchResult, "Record not found.");
    }

    /**
     * Test method for
     * {@link com.ericsson.javatraining.contacts.searchtool.SearchTool#getSearchRecordsString(java.lang.String, com.ericsson.javatraining.contacts.storage.Storage)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testSearchRecordsWithRecordFound() throws IOException {
        String testContactRecord = null;

        testStorage.loadData();
        testContactRecord = SearchTool.getSearchRecordsString("Alex", testStorage);
        assertEquals(testContactRecord, "Name\tPhoneNumber\tAddress\nAlex\t123456\tA street room5\n");
    }

    /**
     * Sorry but this is used to show off PowerMock and to get higher coverage.
     * 
     * @throws Exception
     *             if we can not invoke the private constructor
     */
    @Test
    public void testPrivateConstructor() throws Exception {
        invokeConstructor(SearchTool.class);
    }
}
