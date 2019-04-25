package common;

import model.Contact;

import java.util.ArrayList;

public class ContactListToString {
    public static String convert(ArrayList<Contact> contacts){
        StringBuilder strResult = new StringBuilder();
        int i = 1;
        for (Contact contact : contacts) {
            strResult.append(String.format("%d)\n", i));
            strResult.append(contact.toString());
            i++;
        }
        return strResult.toString();
    }
}
