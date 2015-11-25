package com.zhytnik.library.domain;

import javax.persistence.*;

@MappedSuperclass
public abstract class DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().toString() + " ID = " + id;
    }
}