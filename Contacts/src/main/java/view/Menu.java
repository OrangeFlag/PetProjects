package view;

import common.ReturnCode;
import controller.*;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;


public class Menu extends View {
    @Override
    public ReturnCode use() {
        ReturnCode result = super.use();
        switch (result) {
            case ToMenu:
                return this.use();
        }
        return result;
    }

    @Override
    public String setName() {
        return "menu";
    }

    @Override
    public Action setDefaultAction() {
        return null;
    }

    @Override
    public ArrayList<View> setSubViews() {
        return new ArrayList<View>() {{
            add(new Add());
            add(new Search());
            add(new List());
        }};
    }

    @Override
    public ArrayList<Action> setActions() {
        return new ArrayList<Action>() {{
            add(new Count());
            add(new Again());
            add(new QuestionMark());
            add(new Exit());
        }};
    }
}
