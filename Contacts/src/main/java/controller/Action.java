package controller;

import common.ReturnCode;
import common.ReturnResult;
import org.graalvm.compiler.api.replacements.Snippet;
import view.View;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Map;

public abstract class Action {
    protected String actionName;
    protected ArrayList<String> requiredArguments;

    public Action() {
        actionName = setName();
        assert actionName != null;
        requiredArguments = setRequiredArguments();
    }


    public ReturnResult exec(View view, Map<String, String> arguments) {
        if (requiredArguments != null) {
            if (arguments == null) {
                return new ReturnResult(ReturnCode.IncorrectArguments, "Arguments is null");
            }
            for (String arg : requiredArguments) {
                if (arguments.get(arg) == null) {
                    return new ReturnResult(ReturnCode.IncorrectArguments, "There is no argument: " + arg);
                }
            }
        }
        putToLastAction(view);
        return inner_exec(view, arguments);
    }

    protected abstract ReturnResult inner_exec(View view, Map<String, String> arguments);

    public String getName() {
        return actionName;
    }

    public abstract String setName();

    public ArrayList<String> getRequiredArguments() {
        return requiredArguments;
    }

    public abstract ArrayList<String> setRequiredArguments();

    protected void putToLastAction(View view) {
        view.putToContext("lastAction", this);
    }
}
