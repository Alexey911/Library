package com.zhytnik.library.service;

import com.zhytnik.library.dao.PublisherDao;
import com.zhytnik.library.domain.Publisher;

import static java.util.Objects.isNull;

public class PublisherService extends AbstractService<Publisher> {
    public PublisherService() {
        super();
    }

    @Override
    public Publisher create() {
        return new Publisher();
    }

    protected void prepare(Publisher p) {
        p.setName(p.getName().trim());
        if (!isNull(p.getAddress())) {
            p.setAddress(p.getAddress().trim());
        }
    }

    @Override
    public boolean isUnique(Publisher publisher) {
        return ((PublisherDao) getDao()).hasUniqueName(publisher);
    }
}
