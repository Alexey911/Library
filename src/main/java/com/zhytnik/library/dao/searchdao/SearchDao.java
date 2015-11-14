package com.zhytnik.library.dao.searchdao;

import com.zhytnik.library.entity.DomainObject;

import java.util.Set;

public interface SearchDao<T extends DomainObject> {
    Set<T> findByCriteria(Criteria criteria);
}