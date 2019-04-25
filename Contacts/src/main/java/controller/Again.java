package controller;

import common.ReturnResult;
import view.View;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Map;

public class Again extends Action {
    @Override
    protected ReturnResult inner_exec(View view, Map<String, String> arguments) {
        Action lastAction = view.getLastAction();
        if (lastAction != null) {
            return new ReturnResult(view.actionWrapper(lastAction));
        }
        return new ReturnResult();
    }

    @Override
    public String setName() {
        return "again";
    }

    @Override
    public ArrayList<String> setRequiredArguments() {
        return null;
    }

    protected void putToLastAction(View view) {
    }
}
