package com.zhytnik.library.dao.searchdao;

public interface Criteria {
    void setParameter(Object param);

    Object getParameter();
}