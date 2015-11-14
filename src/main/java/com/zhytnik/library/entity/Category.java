package com.zhytnik.library.entity;

public class Category extends DomainObject {
    private String name;
    private String description;

    public Category() {

    }

    public Category(String name, String desc) {
        this.name = name;
        description = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        description = desc;
    }

    @Override
    public String toString() {
        return "Category (name = " + name + ", description = " + description + ", id = " + getId() + ")";
    }
}