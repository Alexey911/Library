package com.zhytnik.library.dao.jdbc.mysql.dependence;

import com.google.common.collect.Sets;
import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.jdbc.Dependence;
import com.zhytnik.library.dao.jdbc.mysql.CategoryDao;
import com.zhytnik.library.model.Book;
import com.zhytnik.library.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.zhytnik.library.tools.Utils.getContext;

public class BookToCategoriesDependence extends Dependence<Book> {
    public BookToCategoriesDependence() {

    }

    @Override
    protected void update(Book book, Connection connection) throws DaoException {
        Set<Category> storedCategories = getBookStoredCategories(book.getId(), connection);
        Set<Category> newCategories;
        if (book.getCategories() == null) {
            newCategories = storedCategories;
        } else {
            newCategories = Sets.difference(book.getCategories(), storedCategories);
        }
        create(book.getId(), newCategories, connection);
        book.setCategories(getBookStoredCategories(book.getId(), connection));
    }

    private Set<Category> getBookStoredCategories(Integer id, Connection connection) {
        String sql = "SELECT id_category FROM book_categories WHERE id_book = ?";
        Set<Integer> ids = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("id_category"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        Set<Category> result = new HashSet<>(ids.size());

        CategoryDao dao = (CategoryDao) getContext().getBean("categoryDao");

        result.addAll(ids.stream().map(dao::findById).collect(Collectors.toList()));
        return result;
    }

    private void create(Integer id, Set<Category> categories, Connection connection) {
        String sql = "INSERT INTO book_categories (id_book, id_category)  VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Category category : categories) {
                statement.setInt(1, id);
                statement.setInt(2, category.getId());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void delete(Book book, Connection connection) throws DaoException {
        String sql = "DELETE FROM book_categories WHERE id_book = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        book.setCategories(null);
    }
}