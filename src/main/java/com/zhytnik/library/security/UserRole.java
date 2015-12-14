package com.zhytnik.library.security;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    USER("ROLE_USER", 0),
    LIBRARIAN("ROLE_LIBRARIAN", 1),
    ADMIN("ROLE_ADMIN", 2);

    private final String role;
    private final int securityLevel;

    UserRole(String role, int securityLevel) {
        this.role = role;
        this.securityLevel = securityLevel;
    }

    public static boolean hasAccess(UserRole current, UserRole goal) {
        return current.securityLevel >= goal.securityLevel;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }
}
