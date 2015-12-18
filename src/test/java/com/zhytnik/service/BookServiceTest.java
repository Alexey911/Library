package com.zhytnik.service;

import com.zhytnik.library.dao.BookDao;
import com.zhytnik.library.domain.Book;
import com.zhytnik.library.service.BookService;
import com.zhytnik.library.service.exception.NotFoundItemException;
import com.zhytnik.library.service.exception.NotUniqueException;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    @InjectMocks
    private BookService service = new BookService();

    @Mock
    private BookDao dao;

    @Test
    public void create() {
        Book book = service.create();
        Assertions.assertThat(book).isNotNull();
    }

    @Test
    public void findById() {
        Book category = new Book();
        Integer id = 1;
        when(dao.findById(id)).thenReturn(category);
        Assertions.assertThat(service.findById(id)).isNotNull();
    }

    @Test(expected = NotFoundItemException.class)
    public void findByIdNotExists() {
        Integer id = 2;
        when(dao.findById(id)).thenReturn(null);
        service.findById(id);
    }

    @Test
    public void addUniqueBook() {
        Book book = new Book();
        fillFields(book);
        when(dao.hasUniqueName(book)).thenReturn(true);
        service.add(book);
        verify(dao).persist(book);
    }

    @Test(expected = NotUniqueException.class)
    public void addNotUniqueCategory() {
        Book book = new Book();
        fillFields(book);
        service.add(book);
    }

    @Test
    public void update() {
        Book b = new Book();
        fillFields(b);
        when(dao.hasUniqueName(b)).thenReturn(true);
        service.update(b);
        verify(dao).update(b);
    }

    @Test
    public void delete() {
        Integer id = 1;
        service.delete(id);
        verify(dao).delete(id);
    }

    @Test
    public void getAll() {
        List<Book> books = getBooks();
        when(dao.getAll()).thenReturn(books);
        assertTrue(CollectionUtils.isEqualCollection(books, service.getAll()));
    }

    private void fillFields(Book book) {
        book.setName("name");
        book.setAuthors("authors");
    }

    private List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        Book b = new Book();
        fillFields(b);
        books.add(b);
        return books;
    }

    @Test
    public void getBooksInfo() {
        List<Book> books = getBooks();
        when(dao.getBooksInfo()).thenReturn(books);
        assertTrue(CollectionUtils.isEqualCollection(books, service.getBooksInfo()));
    }

    @Test
    public void findByPublisher() {
        List<Book> books = getBooks();
        Integer id = 1;
        when(dao.findByPublisher(id)).thenReturn(books);
        assertTrue(CollectionUtils.isEqualCollection(books, service.findByPublisher(id)));
    }

    @Test
    public void findByCategory() {
        List<Book> books = getBooks();
        Integer id = 1;
        when(dao.findByCategory(id)).thenReturn(books);
        assertTrue(CollectionUtils.isEqualCollection(books, service.findByCategory(id)));
    }
}
