package com.zhytnik.library.service;

import com.zhytnik.library.model.Publisher;

import static com.google.common.base.Strings.isNullOrEmpty;

public class PublisherService extends Service<Publisher> {
    public PublisherService() {
        super();
    }

    @Override
    public Publisher create() {
        return new Publisher();
    }

    @Override
    protected boolean isUniqueItem(Publisher object) {
        return false;
    }

    @Override
    public void validateFullness(Publisher p) {
        if (isNullOrEmpty(p.getName())) {
            throwIllegalArgException("Set name of publisher: " + p);
        }
        String format = "%s length must be less than or equals to %d";
        if (p.getName().length() > 40) {
            throwIllegalArgException(String.format(format, "Name", 40));
        }
        if (p.getAddress().length() > 100) {
            throwIllegalArgException(String.format(format, "Address", 100));
        }
    }
}