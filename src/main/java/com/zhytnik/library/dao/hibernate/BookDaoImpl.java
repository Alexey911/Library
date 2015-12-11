package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.BookDao;
import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.domain.Book;
import com.zhytnik.library.domain.Category;
import com.zhytnik.library.domain.Publisher;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookDaoImpl extends AbstractHibernateDao<Book> implements BookDao {
    public BookDaoImpl() {
        super(Book.class);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean hasUniqueName(Book book) throws DaoException {
        Criteria criteria = getLazyCriteria(Projections.projectionList().
                add(Projections.property("id"), "id").
                add(Projections.property("name"), "name"));
        criteria.add(Restrictions.eq("name", book.getName()));
        return isUnique(criteria, book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getBooksInfo() throws DaoException {
        return super.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findByPublisher(Integer publisher) throws DaoException {
        Criteria criteria = getCurrentSession().createCriteria(Book.class);
        criteria.add(Restrictions.eq("publisher.id", publisher));
        return criteria.list();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findByCategory(Integer category) {
        Criteria criteria = getCurrentSession().createCriteria(Book.class);
        criteria.createAlias("categories", "c").
                add(Restrictions.eq("c.id", category));
        return criteria.list();
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(Integer id) throws DaoException {
        Book book = super.findById(id);
        Hibernate.initialize(book.getCategories());
        return book;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() throws DaoException {
        List<Book> books = super.getAll();
        books.forEach(book -> Hibernate.initialize(book.getCategories()));
        return books;
    }

    @Transactional
    @Override
    public void update(Book book) throws DaoException {
        super.update(initialize(book));
    }

    @Transactional
    @Override
    public void persist(Book book) throws DaoException {
        super.persist(initialize(book));
    }

    private Book initialize(Book book) {
        Session session = getCurrentSession();
        Publisher daoPublisher = (Publisher) session.get(Publisher.class, book.getPublisher().getId());
        book.setPublisher(daoPublisher);
        Set<Category> daoCategories = new HashSet<>(book.getCategories().size());
        daoCategories.addAll(book.getCategories().stream().
                map(c -> (Category) session.get(Category.class, c.getId())).
                collect(Collectors.toList()));
        book.setCategories(daoCategories);
        return book;
    }
}
