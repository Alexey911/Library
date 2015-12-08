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
import java.util.Set;
import java.util.stream.Collectors;

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
    public boolean hasUniqueName(Book book) throws DaoException {
        Criteria criteria = getLazyCriteria(Projections.projectionList().
                add(Projections.property("id"), "id").
                add(Projections.property("name"), "name"));
        criteria.add(Restrictions.eq("name", book.getName()));
        return isUnique(criteria, book);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Book> getBooksInfo() throws DaoException {
        return new HashSet<>(getSearchCriteria().list());
    }

    private Criteria getSearchCriteria() {
        Criteria criteria = getLazyCriteria(Projections.projectionList().
                        add(Projections.property("id").as("id")).
                        add(Projections.property("name").as("name")).
                        add(Projections.property("pageCount").as("pageCount")).
                        add(Projections.property("p.id").as("publisher.id")).
                        add(Projections.property("p.name").as("publisher.name")),
                new AliasToBeanNestedResultTransformer(Book.class));
        return criteria.createAlias("publisher", "p");
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(Integer id) throws DaoException {
        Book book = super.findById(id);
        book.getCategories().forEach(Hibernate::initialize);
        return book;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Book> getAll() throws DaoException {
        Set<Book> books = super.getAll();
        books.forEach(book -> book.getCategories().forEach(Hibernate::initialize));
        return books;
    }

    //TODO: persist only by IDs in Publisher and Categories
    @Transactional
    @Override
    public void persist(Book book) throws DaoException {
        initialize(getCurrentSession(), book);
        super.persist(book);
    }

    //TODO: explicit delete constraint
    @Transactional
    @Override
    public void delete(Integer id) throws DaoException {
        Session session = getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        book.setCategories(new HashSet<>());
        book.setPublisher(null);
        session.delete(book);
    }

    //TODO: update only by IDs in Publisher and Categories
    @Transactional
    @Override
    public void update(Book book) throws DaoException {
        initialize(getCurrentSession(), book);
        super.update(book);
    }

    private void initialize(Session session, Book book) {
        Publisher daoPublisher = (Publisher) session.get(Publisher.class, book.getPublisher().getId());
        book.setPublisher(daoPublisher);
        Set<Category> daoCategories = new HashSet<>(book.getCategories().size());
        daoCategories.addAll(book.getCategories().stream().
                map(c -> (Category) session.get(Category.class, c.getId())).
                collect(Collectors.toList()));
        book.setCategories(daoCategories);
    }
}
