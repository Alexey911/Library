package com.zhytnik.library.domain;

import com.zhytnik.library.security.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User extends DomainObject implements Serializable {
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotNull
    @Size(min = 1, max = 65)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Column(name = "confirmed", nullable = false)
    private Boolean confirmed;

    public User() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEnable() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Boolean isConfirmed() {
        return confirmed;
    }

    @Override
    public String toString() {
        //noinspection StringBufferReplaceableByString
        return new StringBuilder().append("User [login = ").append(login).
                append(", enables = ").append(enabled).
                append(", confirmed = ").append(confirmed).
                append(", role = ").append(role).append("]").toString();
    }
}
