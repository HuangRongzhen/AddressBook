/**
 * Created : 2012-2-25
 * 
 * Copyright (c) 2012 Ericsson (China) Communications Co.,Ltd. All rights reserved. The Copyright to the computer program(s) herein is the property of Ericsson
 * (China) Communications Co.,Ltd. The program(s) may be used and/or copied with the written permission from Ericsson AB or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the program(s) have been supplied.
 */
package com.ericsson.javatraining.contacts;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ericsson.javatraining.contacts.storage.Storage;

/**
 * 
 * @author eronhua
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ContactFrame.class })
public class ContactFrameTest {
    private ContactFrame testContactFrame;
    private Storage mockStorage;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mockStorage = mock(Storage.class);
        whenNew(Storage.class).withArguments(anyString()).thenReturn(mockStorage);
        
        //testContactFrame = new ContactFrame();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.ericsson.javatraining.contacts.ContactFrame#ContactFrame()}.
     */
    @Test
    public final void testContactFrame() {        
//        try {
//            verify(mockStorage).loadData();
//        } catch (IOException e) {
//            e.printStackTrace();
//            fail("testContactFrame caputre exception.");
//        }
//        
    }

    /**
     * Test method for {@link com.ericsson.javatraining.contacts.ContactFrame#setOutputBox(java.lang.String)}.
     */
    @Test
    public final void testSetOutputBox() {
       // fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.ericsson.javatraining.contacts.ContactFrame#main(java.lang.String[])}.
     */
    @Test
    public final void testMain() {
        //fail("Not yet implemented"); // TODO
    }

}
