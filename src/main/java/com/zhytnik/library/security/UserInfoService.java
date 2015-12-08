package com.zhytnik.library.security;

import com.zhytnik.library.dao.UserDao;
import com.zhytnik.library.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static java.util.Objects.isNull;

public class UserInfoService implements UserDetailsService {
    @Autowired
    @Qualifier("userDao")
    private UserDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findByUserName(username);
        if (isNull(user)) {
            throw new UsernameNotFoundException(String.format("User with name %s not found", username));
        }
        return new UserInfo(user);
    }
}
