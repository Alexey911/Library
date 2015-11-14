package com.zhytnik.library.dao.searchdao;

public class SimpleCriteria implements Criteria {
    private Object param;

    @Override
    public void setParameter(Object param) {
        this.param = param;
    }

    @Override
    public Object getParameter() {
        return param;
    }
}