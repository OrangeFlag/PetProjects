package view;

import controller.Action;
import controller.Again;
import controller.Back;
import controller.QuestionMark;

import java.util.ArrayList;

public class List extends View {
    @Override
    public String setName() {
        return "list";
    }

    @Override
    public Action setDefaultAction() {
        return new controller.List();
    }

    @Override
    public ArrayList<View> setSubViews() {
        return new ArrayList<View>() {{
            add(new Record());
        }};
    }

    @Override
    public ArrayList<Action> setActions() {
        return new ArrayList<Action>() {{
            add(new controller.List());
            add(new Again());
            add(new QuestionMark());
            add(new Back());
        }};
    }
}
