package com.zhytnik.library.service;

import com.zhytnik.library.dao.PublisherDao;
import com.zhytnik.library.model.Publisher;

import static java.util.Objects.isNull;

public class PublisherService extends Service<Publisher> {
    public PublisherService() {
        super();
    }

    @Override
    public Publisher create() {
        return new Publisher();
    }

    public void setPublisherDao(PublisherDao publisherDao) {
        setDao(publisherDao);
    }

    @Override
    public boolean isUnique(Publisher publisher) {
        String name = publisher.getName();
        PublisherDao dao = (PublisherDao) getDao();
        if (dao.isUniqueName(name)) {
            return true;
        }
        if (isNull(publisher.getId())) {
            return false;
        }
        Publisher daoItem = dao.findById(publisher.getId());
        return daoItem.getName().equals(name);
    }
}