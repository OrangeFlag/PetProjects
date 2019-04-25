package view;

import controller.*;
import controller.Menu;

import java.util.ArrayList;

public class Record extends View {

    @Override
    public String setName() {
        return "record";
    }

    @Override
    public Action setDefaultAction() {
        return new GetRecord();
    }

    @Override
    public ArrayList<View> setSubViews() {
        return null;
    }

    @Override
    public ArrayList<Action> setActions() {
        return new ArrayList<Action>() {{
            add(new GetRecord());
            add(new Again());
            add(new QuestionMark());
            add(new Menu());
            add(new Back());
        }};
    }
}
