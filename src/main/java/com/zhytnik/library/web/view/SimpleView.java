package com.zhytnik.library.web.view;

public class SimpleView implements View {
    private String forward;
    private boolean redirect;

    public SimpleView(String forward) {
        this.forward = forward;
    }

    @Override
    public String getForward() {
        return forward;
    }

    @Override
    public boolean isRedirected() {
        return redirect;
    }

    @Override
    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }
}