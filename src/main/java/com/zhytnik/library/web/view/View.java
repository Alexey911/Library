package com.zhytnik.library.web.view;

public interface View {
    String getForward();

    boolean isRedirected();

    void setRedirect(boolean redirect);
}
