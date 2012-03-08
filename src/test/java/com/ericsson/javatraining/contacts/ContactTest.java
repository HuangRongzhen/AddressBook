/**
 * Created : 2012-2-23
 * 
 * Copyright (c) 2012 Ericsson (China) Communications Co.,Ltd. All rights reserved. The Copyright to the computer program(s) herein is the property of Ericsson
 * (China) Communications Co.,Ltd. The program(s) may be used and/or copied with the written permission from Ericsson AB or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the program(s) have been supplied.
 */
package com.ericsson.javatraining.contacts;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author eronhua
 */
public class ContactTest {
    private Contact testContact;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        testContact = new Contact("TestName", "123456789011", "Shanghai China");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testContactBuildup() {
        assertEquals("testContactBuildup name fail", testContact.getName(), "TestName");
        assertEquals("testContactBuildup phoneNumber fail", testContact.getPhoneNumber(), "123456789011");
        assertEquals("testContactBuildup address fail", testContact.getAddress(), "Shanghai China");
    }

    @Test
    public void testSetName() {
        testContact.setName("Modified name");

        assertEquals("testSetName fail", testContact.getName(), "Modified name");
    }

    @Test
    public void testSetPhoneNumber() {
        testContact.setPhoneNumber("1198838383891");

        assertEquals("testSetPhoneNumber fail", testContact.getPhoneNumber(), "1198838383891");
    }

    @Test
    public void testSetAddress() {
        testContact.setAddress("Beijing China");

        assertEquals("testSetAddress fail", testContact.getAddress(), "Beijing China");
    }
}
