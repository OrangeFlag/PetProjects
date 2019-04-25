package view;

import common.ReturnResult;
import controller.Action;
import common.ReturnCode;
import jdk.internal.jline.internal.Nullable;
import org.graalvm.compiler.api.replacements.Snippet;

import java.util.*;

public abstract class View {
    protected String viewName = "";
    protected ArrayList<View> subViews = new ArrayList<>();
    protected ArrayList<Action> actions = new ArrayList<>();
    protected Action defaultAction;
    protected Map<String, Object> context = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public View() {
        this.defaultAction = setDefaultAction();
        viewName = setName();
        ArrayList<View> tempSubViews = setSubViews();
        if (tempSubViews != null) {
            subViews.addAll(tempSubViews);
        }

        ArrayList<Action> tempActions = setActions();
        if (tempActions != null) {
            actions.addAll(tempActions);
        }
    }

    public ReturnCode command(@Snippet.NonNullParameter String command) {
        for (View view : subViews) {
            if (view.getName().equals(command)) {
                return view.use(context);
            }
        }

        for (Action action : actions) {
            if (action.getName().equals(command)) {
                return actionWrapper(action);

            }
        }

        System.out.println("Subview or action not found");
        return ReturnCode.Continue;
    }

    public ReturnCode actionWrapper(@Nullable Action action) {
        if (action != null) {
            ReturnResult result = action.exec(this, getActionArguments(action.getRequiredArguments()));
            if (result.getResult() != null && !result.getResult().equals("")) {
                System.out.println(result.getResult());
            }
            return result.getCode();
        }
        return ReturnCode.Continue;
    }

    protected Map<String, String> getActionArguments(ArrayList<String> requiredArguments) {
        Map<String, String> result = new HashMap<>();
        if (requiredArguments != null) {
            for (String argument : requiredArguments) {
                System.out.printf("Enter %s:\n", argument);
                result.put(argument, sc.nextLine());
            }
        }
        return result;
    }

    public ReturnCode use() {
        ReturnCode result = actionWrapper(defaultAction);
        while (true) {
            switch (result) {
                case ToMenu:
                    return ReturnCode.ToMenu;
                case Back:
                    return ReturnCode.Continue;
                case Exit:
                    return ReturnCode.Exit;
            }
            this.render();
            result = command(sc.nextLine());
        }
    }

    public ReturnCode use(Map<String, Object> context) {
        this.context.putAll(context);
        return use();
    }

    public void render() {
        System.out.printf("[%s] Enter command:\n", viewName);
    }

    public void render_full_description() {
        if (subViews.size() != 0) {
            System.out.println("Submenu:");
            for (View submenu : subViews) {
                System.out.println("\t" + submenu.getName());
            }
        }
        if (actions.size() != 0) {
            System.out.println("Actions:");
            for (Action action : actions) {
                System.out.println("\t" + action.getName());
            }
        }
    }

    public String getName() {
        return viewName;
    }

    public abstract String setName();

    public abstract Action setDefaultAction();

    public Action getLastAction() {
        return (Action) context.get("lastAction");
    }

    public abstract ArrayList<View> setSubViews();

    public abstract ArrayList<Action> setActions();

    public Object getFromContext(String key) {
        return context.get(key);
    }

    public void putToContext(String key, Object value) {
        context.put(key, value);
    }

}
