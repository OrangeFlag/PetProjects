package controller;

import common.ReturnCode;
import common.ReturnResult;
import view.View;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Map;

public class QuestionMark extends Action {
    @Override
    protected ReturnResult inner_exec(View view, Map<String, String> arguments) {
        view.render_full_description();
        return new ReturnResult();
    }

    @Override
    public String setName() {
        return "?";
    }

    @Override
    public ArrayList<String> setRequiredArguments() {
        return null;
    }
}