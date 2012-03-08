/**
 * Created : 2012-1-3
 * 
 * Copyright (c) 2012 Ericsson (China) Communications Co.,Ltd. All rights reserved. The Copyright to the computer program(s) herein is the property of Ericsson
 * (China) Communications Co.,Ltd. The program(s) may be used and/or copied with the written permission from Ericsson AB or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the program(s) have been supplied.
 */
package com.ericsson.javatraining.contacts;

/**
 * 
 * @author eronhua
 */
public class Contact {
    private String name;
    private String address;
    private String phoneNumber;

    /**
     * Build a Contact data object which store contact information.
     * 
     * Parameters:
     * 
     * @param name
     *            - The name of the contact.
     * @param phoneNumber
     *            - The phone number of the contact.
     * @param address
     *            - The address of the contact.
     */
    public Contact(String name, String phoneNumber, String address) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    @Override
    
    public String toString() {
        return getName() + " " + getPhoneNumber() + " " + getAddress();
    }

    /**
     * Set contact's name.
     * 
     * Parameters:
     * 
     * @param name
     *            -name you want to modify
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set contact's address.
     * 
     * Parameters:
     * 
     * @param address
     *            -address you want to modify
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Set contact's phone number.
     * 
     * Parameters:
     * 
     * @param name
     *            -phone number you want to modify
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get contact's name.
     * 
     * Parameters:
     * 
     * @param name
     *            -name you want to get
     */
    public String getName() {
        return name;
    }

    /**
     * Get contact's address.
     * 
     * Parameters:
     * 
     * @param address
     *            -address you want to get
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get contact's phone number.
     * 
     * Parameters:
     * 
     * @param phone number
     *            -phone number you want to get
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
