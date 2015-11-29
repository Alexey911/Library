package com.zhytnik.library.service;

import com.zhytnik.library.dao.PublisherDao;
import com.zhytnik.library.domain.Publisher;

import static java.util.Objects.isNull;

public class PublisherService extends Service<Publisher> {
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
    public boolean isUnique(Publisher p) {
        String name = p.getName();
        PublisherDao dao = (PublisherDao) getDao();
        if (dao.isUniqueName(name)) {
            return true;
        }
        if (isNull(p.getId())) {
            return false;
        }
        Publisher daoItem = dao.findById(p.getId());
        return daoItem.getName().equals(name);
    }
}