package com.zhytnik.library.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "publisher")
public class Publisher extends DomainObject implements Serializable {
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Size(max = 100)
    @Column(name = "address", nullable = true, length = 100)
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
        //noinspection StringBufferReplaceableByString
        return new StringBuilder().append("Publisher [name = ").append(name).
                append(", address = ").append(address).
                append(", id = ").append(getId()).append("]").toString();
    }
}
