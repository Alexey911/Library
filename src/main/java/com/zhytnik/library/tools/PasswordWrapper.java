package com.zhytnik.library.tools;

import javax.validation.constraints.Size;

public class PasswordWrapper {
    @Size(min = 1, max = 65)
    private String lastPassword;

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
