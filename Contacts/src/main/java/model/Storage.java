package model;

import java.util.ArrayList;

public class Storage {
    private static ArrayList<Contact> storage = new ArrayList<Contact>();

//    public static void addAll(ArrayList<Contact> storage) {
//        Storage.storage.addAll(storage);
//    }

    public static void add(Contact contact) {
        storage.add(contact);
    }

    public static ArrayList<Contact> search(String query) {
        ArrayList<Contact> result = new ArrayList<>();
        for (Contact contact : storage) {
            if (contact.getName().contains(query)) {
                result.add(contact);
            }
        }
        return result;
    }

    public static Integer size() {
        return storage.size();
    }

    public static ArrayList<Contact> getListOfContacts() {
        return storage;
    }
}
