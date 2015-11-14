package com.zhytnik.library.web.model;

import java.util.Map;

public interface Model {
    void addObject(String name, Object object);

    Map<String, Object> getData();
}