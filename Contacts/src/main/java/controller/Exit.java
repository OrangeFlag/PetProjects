package controller;

import common.ReturnCode;
import common.ReturnResult;
import view.View;

import java.util.ArrayList;
import java.util.Map;

public class Exit extends Action {
    @Override
    protected ReturnResult inner_exec(View view, Map<String, String> arguments) {
        return new ReturnResult(ReturnCode.Back);
    }

    @Override
    public String setName() {
        return "exit";
    }

    @Override
    public ArrayList<String> setRequiredArguments() {
        return null;
    }
}
