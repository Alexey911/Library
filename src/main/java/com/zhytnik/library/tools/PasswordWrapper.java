package com.zhytnik.library.tools;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PasswordWrapper {
    @NotNull
    @Size(min = 1, max = 65)
    private String lastPassword;

    @NotNull
    @Size(min = 1, max = 65)
    private String newPassword;

    private Integer ownerId;

    public String getLastPassword() {
        return lastPassword;
    }

    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}
