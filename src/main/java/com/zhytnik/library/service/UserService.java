package com.zhytnik.library.service;

import com.zhytnik.library.dao.UserDao;
import com.zhytnik.library.domain.User;
import com.zhytnik.library.security.UserRole;
import com.zhytnik.library.service.exception.NotUniqueException;
import com.zhytnik.library.service.exception.PasswordMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

import java.util.List;
import java.util.Set;

import static com.zhytnik.library.security.UserRole.LIBRARIAN;
import static com.zhytnik.library.security.UserRole.USER;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

public class UserService extends Service<User> {
    @Autowired
    private MessageDigestPasswordEncoder passwordEncoder;

    public void setPasswordEncoder(MessageDigestPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User create() {
        return new User();
    }

    public void activate(Integer id) {
        getUserDao().activate(id);
    }

    public void disable(Integer id) {
        getUserDao().disable(id);
    }

    @Override
    protected boolean isUnique(User user) {
        return getUserDao().hasUniqueLogin(user);
    }

    @Override
    public void add(User user) throws NotUniqueException {
        UserDao dao = getUserDao();
        if (dao.hasUniqueLogin(user)) {
            encodeUserPassword(user);
            boolean confirmed = USER.getRole().equals(user.getRole());
            user.setConfirmed(confirmed);
            user.setEnabled(TRUE);
            getDao().persist(user);
        } else {
            throw new NotUniqueException();
        }
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException();
    }

    public void updatePassword(Integer id, String lastPass,
                               String newPass) throws PasswordMismatchException {
        User user = getDao().findById(id);
        if (!user.getPassword().equals(encodePassword(lastPass))) {
            throw new PasswordMismatchException();
        }
        user.setPassword(encodePassword(newPass));
        getDao().update(user);
    }

    public void updateLoginRole(Integer id, String login,
                                boolean wantsBeLibrarian) throws NotUniqueException {
        if (!hasUniqueLogin(id, login)) {
            throw new NotUniqueException();
        }
        User user = getDao().findById(id);
        if (!UserRole.hasRole(LIBRARIAN, user) && wantsBeLibrarian) {
            user.setRole(LIBRARIAN.getRole());
            user.setConfirmed(FALSE);
        }
        user.setLogin(login);
        getUserDao().update(user);
    }

    private boolean hasUniqueLogin(Integer id, String login) {
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        return getUserDao().hasUniqueLogin(user);
    }

    public Set<User> getNotConfirmedUsers() {
        return getUserDao().getNotConfirmedUsers();
    }

    public void confirm(List<Integer> users) {
        if (!isNull(users) && !users.isEmpty()) {
            getUserDao().confirm(users);
        }
    }

    private UserDao getUserDao() {
        return (UserDao) getDao();
    }

    private void encodeUserPassword(User user) {
        user.setPassword(encodePassword(user.getPassword()));
    }

    private String encodePassword(String password) {
        return passwordEncoder.encodePassword(password, null);
    }

    public User findByName(String username) {
        return getUserDao().findByUserName(username);
    }
}
