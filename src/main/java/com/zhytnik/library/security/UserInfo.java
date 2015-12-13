package com.zhytnik.library.security;

import com.zhytnik.library.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

import static com.zhytnik.library.security.UserRole.USER;

public class UserInfo implements UserDetails {
    private User user;
    private Collection<? extends GrantedAuthority> authorities;

    public UserInfo(User user) {
        this.user = user;
        if (!user.isConfirmed()) {
            this.user.setRole(USER);
        }
        authorities = Collections.singleton(() -> user.getRole().getAuthority());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnable();
    }
}
