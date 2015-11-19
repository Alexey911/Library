package com.zhytnik.library.dao;

import com.zhytnik.library.model.DomainObject;

import java.util.Set;

public interface SearchDao<T extends DomainObject> extends GenericDao<T> {
    Set<T> find(Object param);
}