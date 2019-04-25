package view;

import controller.Action;
import controller.Again;
import controller.Back;
import controller.QuestionMark;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

public class Search extends View {
    @Override
    public String setName() {
        return "search";
    }

    @Override
    public Action setDefaultAction() {
        return new controller.Search();
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
            add(new controller.Search());
            add(new Again());
            add(new QuestionMark());
            add(new Back());
        }};
    }
}
