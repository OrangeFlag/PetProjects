package controller;

import common.ReturnCode;
import common.ReturnResult;
import model.Contact;
import view.View;

import java.util.ArrayList;
import java.util.Map;

public class GetRecord extends Action {
    @Override
    protected ReturnResult inner_exec(View view, Map<String, String> arguments) {
        ArrayList<Contact> result = (ArrayList<Contact>) view.getFromContext("contactList");
        if (result != null) {
            int number = Integer.parseInt(arguments.get("number")) - 1;
            if (0 <= number && number < result.size()) {
                Contact contact = result.get(number);
                if (contact != null) {
                    view.putToContext("record", contact);
                    return new ReturnResult(ReturnCode.Continue, contact.toString());
                }
                return new ReturnResult(ReturnCode.Back, String.format("%d contact in null", number));
            }
            return new ReturnResult(ReturnCode.Back, String.format("There is no %d contact in list", number));
        }
        return new ReturnResult(ReturnCode.Back, "There is no any contact lists to get record");
    }

    @Override
    public String setName() {
        return "get record";
    }

    @Override
    public ArrayList<String> setRequiredArguments() {
        return new ArrayList<String>() {{
            add("number");
        }};
    }
}
