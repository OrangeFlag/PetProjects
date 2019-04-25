package view;

import controller.Action;

import java.util.ArrayList;

public class Add extends View {
    @Override
    public String setName() {
        return "add";
    }

    @Override
    public Action setDefaultAction() {
        return null;
    }

    @Override
    public ArrayList<View> setSubViews() {
        return null;
    }

    @Override
    public ArrayList<Action> setActions() {
        return null;
    }
}
