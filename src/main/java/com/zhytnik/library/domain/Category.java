package com.zhytnik.library.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "category")
public class Category extends DomainObject implements Serializable {
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 150)
    @Column(name = "description", nullable = true, length = 150)
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
        //noinspection StringBufferReplaceableByString
        return new StringBuilder().append("Category [name = ").append(name).
                append(", description = ").append(description).
                append(", id = ").append(getId()).append("]").toString();
    }
}
