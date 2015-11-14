package com.zhytnik.library.entity;

public abstract class DomainObject {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().toString() + " ID = " + id;
    }
}