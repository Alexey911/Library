package com.zhytnik.library.web.view;

public class SimpleView implements View {
    private String forward;

    public SimpleView(String forward) {
        this.forward = forward;
    }

    @Override
    public String getForward() {
        return forward;
    }
}