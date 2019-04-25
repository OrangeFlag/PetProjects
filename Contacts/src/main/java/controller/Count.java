package controller;


import common.ReturnCode;
import common.ReturnResult;
import model.Storage;
import view.View;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Map;

public class Count extends Action {
    @Override
    protected ReturnResult inner_exec(View view, Map<String, String> arguments) {
        return new ReturnResult(ReturnCode.Continue, String.format("The Phone book has %d contacts.", Storage.size()));
    }

    @Override
    public String setName(){
        return "count";
    }

    @Override
    public ArrayList<String> setRequiredArguments() {
        return null;
    }
}