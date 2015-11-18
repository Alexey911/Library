package com.zhytnik.library.web.old;

import com.zhytnik.library.web.old.model.Model;
import com.zhytnik.library.web.old.view.SimpleView;
import com.zhytnik.library.web.old.view.View;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView implements View, Model {
    private Map<String, Object> model;
    private View view;

    public ModelAndView(String forward) {
        model = new HashMap<>();
        view = new SimpleView(forward);
    }

    @Override
    public void addObject(String name, Object object) {
        model.put(name, object);
    }

    @Override
    public String getForward() {
        return view.getForward();
    }

    @Override
    public Map<String, Object> getData() {
        return model;
    }
}