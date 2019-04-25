package controller;

import common.ContactListToString;
import common.ReturnCode;
import common.ReturnResult;
import model.Contact;
import model.Storage;
import view.View;

import java.util.ArrayList;
import java.util.Map;

public class List extends Action {
    @Override
    protected ReturnResult inner_exec(View view, Map<String, String> arguments) {
        ArrayList<Contact> result = Storage.getListOfContacts();
        view.putToContext("contactList", result);
        return new ReturnResult(ReturnCode.Continue, ContactListToString.convert(result));
    }

    @Override
    public String setName() {
        return "list";
    }

    @Override
    public ArrayList<String> setRequiredArguments() {
        return null;
    }
}
