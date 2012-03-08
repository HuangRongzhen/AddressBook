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
    public final void testContactBuildup() {
        assertEquals(testContact.getName(), "TestName");
        assertEquals(testContact.getPhoneNumber(), "123456789011");
        assertEquals(testContact.getAddress(), "Shanghai China" );
    }
    
    @Test
    public final void testSetName() {
        testContact.setName("Modified name");
        
        assertEquals(testContact.getName(), "Modified name");
    }
    
    @Test
    public final void testSetPhoneNumber() {
        testContact.setPhoneNumber("1198838383891");
        
        assertEquals(testContact.getPhoneNumber(), "1198838383891");
    }
    
    @Test
    public final void testSetAddress() {
        testContact.setAddress("Beijing China");
        
        assertEquals(testContact.getAddress(), "Beijing China");
    }
}
