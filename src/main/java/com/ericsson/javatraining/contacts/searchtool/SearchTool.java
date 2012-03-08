/**
 * Created : 2012-1-4
 * 
 * Copyright (c) 2012 Ericsson (China) Communications Co.,Ltd. All rights reserved. The Copyright to the computer program(s) herein is the property of Ericsson
 * (China) Communications Co.,Ltd. The program(s) may be used and/or copied with the written permission from Ericsson AB or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the program(s) have been supplied.
 */
package com.ericsson.javatraining.contacts.searchtool;

import java.util.ArrayList;
import java.util.List;

import com.ericsson.javatraining.contacts.Contact;
import com.ericsson.javatraining.contacts.storage.Storage;
import com.ericsson.javatraining.contacts.util.ContactUtil;

/**
 * 
 * @author eronhua
 */
public final class SearchTool {

    public static List<Contact> getSearchObject(String searchKey, Storage storage) {
        List<Contact> searchedContacts = new ArrayList<Contact>();

        for (Contact contact : storage.getContacts()) {
            if (contact.toString().contains(searchKey)) {
                searchedContacts.add(contact);
            }
        }

        return searchedContacts;
    }

    public static String getSearchRecordsString(String searchKey, Storage storage) {
        String result = "Name" + "\t" + "PhoneNumber" + "\t" + "Address" + "\n";
        List<Contact> searchedContacts = new ArrayList<Contact>();
        int recordCount = 0;

        searchedContacts = getSearchObject(searchKey, storage);

        for (Contact contact : searchedContacts) {
            if (contact.toString().contains(searchKey)) {
                result = result + ContactUtil.getContactString(contact) + "\n";
                recordCount++;
            }
        }

        if (0 == recordCount) {
            result = "Record not found.";
        }

        return result;
    }

    private SearchTool() {
        super();
    }
}
