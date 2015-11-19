package com.zhytnik.library.model;

public class Publisher extends DomainObject {
    private String name;
    private String address;

    public Publisher() {

    }

    public Publisher(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Publisher (name = " + name + ", address = " + address + ", id = " + getId() + ")";
    }
}