package com.zhytnik.library.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Category extends DomainObject {
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @Size(max = 150)
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