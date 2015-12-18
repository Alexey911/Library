package com.zhytnik.library.service;

import com.zhytnik.library.dao.UserDao;
import com.zhytnik.library.domain.User;
import com.zhytnik.library.security.UserRole;
import com.zhytnik.library.service.exception.NotUniqueException;
import com.zhytnik.library.service.exception.PasswordMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import java.util.List;

import static com.zhytnik.library.security.UserRole.USER;
import static com.zhytnik.library.security.UserRole.hasAccess;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

@SuppressWarnings("deprecation")
public class UserService extends AbstractService<User> {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User create() {
        return new User();
    }

    public void activate(Integer id) {
        User user = getDao().findById(id);
        user.setEnabled(true);
        getDao().update(user);
    }

    public void disable(Integer id) {
        User user = getDao().findById(id);
        user.setEnabled(false);
        getDao().update(user);
    }

    @Override
    protected boolean isUnique(User user) {
        return getUserDao().hasUniqueLogin(user);
    }

    @Override
    public void add(User user) throws NotUniqueException {
        tryPersist(user, false);
    }

    public void addConfirmedUser(User user) throws NotUniqueException {
        tryPersist(user, true);
    }

    private void tryPersist(User user, boolean confirmed) {
        UserDao dao = getUserDao();
        if (dao.hasUniqueLogin(user)) {
            encodeUserPassword(user);
            if (confirmed) {
                user.setConfirmed(TRUE);
            } else {
                determineAndSetConfirm(user);
            }
            user.setEnabled(TRUE);
            dao.persist(user);
        } else {
            throw new NotUniqueException();
        }
    }

    private void determineAndSetConfirm(User user) {
        boolean confirmed = (user.getRole() == USER);
        user.setConfirmed(confirmed);
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

    public void updateLoginRole(User user) throws NotUniqueException {
        UserDao dao = getUserDao();
        if (!dao.hasUniqueLogin(user)) {
            throw new NotUniqueException();
        }
        fill(user);
        dao.update(user);
    }

    private void fill(User user) {
        User daoUser = getDao().findById(user.getId());

        UserRole newRole = user.getRole();
        boolean confirmed = daoUser.isConfirmed() && hasAccess(daoUser.getRole(), newRole);
        user.setConfirmed(confirmed);

        user.setEnabled(true);
        user.setPassword(daoUser.getPassword());
    }

    public List<User> getUnconfirmedUsers() {
        return getUserDao().getUnconfirmedUsers();
    }

    public void confirm(List<Integer> users) {
        if (!isNull(users)) {
            users.forEach(this::confirm);
        }
    }

    public void confirm(Integer id) {
        setConfirm(id, true);
    }

    public void resetConfirm(Integer id) {
        setConfirm(id, false);
    }

    private void setConfirm(Integer id, boolean confirmed) {
        UserDao dao = getUserDao();
        User user = dao.findById(id);
        user.setConfirmed(confirmed);
        dao.update(user);
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
