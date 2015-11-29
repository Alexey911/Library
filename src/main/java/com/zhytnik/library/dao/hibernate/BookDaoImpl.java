package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.BookDao;
import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.domain.Book;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

public class BookDaoImpl extends AbstractHibernateDao<Book> implements BookDao {
    public BookDaoImpl() {
        super(Book.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Book> findBooksInPublisherCategories(Integer publisher, Set<Integer> categories) {
        Criteria criteria = getCurrentSession().createCriteria(Book.class);
        criteria.add(Restrictions.eq("publisher.id", publisher)).
                createCriteria("categories").add(Restrictions.in("id", categories));
        return new HashSet<>(criteria.list());
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Book> findBooksByCategories(Set<Integer> categories) {
        Criteria criteria = getCurrentSession().createCriteria(Book.class);
        criteria.createCriteria("categories").add(Restrictions.in("id", categories));
        return new HashSet<>(criteria.list());
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isUniqueName(String name) throws DaoException {
        Criteria criteria = getCurrentSession().createCriteria(Book.class);
        Object result = criteria.add(Restrictions.eq("name", name)).uniqueResult();
        return isNull(result);
    }
}
