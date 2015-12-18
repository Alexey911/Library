package com.zhytnik.service;

import com.zhytnik.library.dao.PublisherDao;
import com.zhytnik.library.domain.Publisher;
import com.zhytnik.library.service.PublisherService;
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
public class PublisherServiceTest {
    @InjectMocks
    private PublisherService service = new PublisherService();

    @Mock
    private PublisherDao dao;

    @Test
    public void create() {
        Publisher category = service.create();
        Assertions.assertThat(category).isNotNull();
    }

    @Test()
    public void findById() {
        Publisher publisher = new Publisher();
        Integer id = 1;
        when(dao.findById(id)).thenReturn(publisher);
        Assertions.assertThat(service.findById(id)).isNotNull();
    }

    @Test(expected = NotFoundItemException.class)
    public void findByIdNotExists() {
        Integer id = 2;
        when(dao.findById(id)).thenReturn(null);
        service.findById(id);
    }

    @Test
    public void addUniquePublisher() {
        Publisher p = new Publisher("name", "address");
        when(dao.hasUniqueName(p)).thenReturn(true);
        service.add(p);
        verify(dao).persist(p);
    }

    @Test(expected = NotUniqueException.class)
    public void addNotUniquePublisher() {
        Publisher publisher = new Publisher("name", "address");
        service.add(publisher);
    }

    @Test
    public void update() {
        Publisher p = new Publisher("name", "address");
        when(dao.hasUniqueName(p)).thenReturn(true);
        service.update(p);
        verify(dao).update(p);
    }

    @Test
    public void delete() {
        Integer id = 1;
        service.delete(id);
        verify(dao).delete(id);
    }

    @Test
    public void getAll() {
        List<Publisher> publishers = new ArrayList<>();
        Publisher p = new Publisher("name", "address");
        publishers.add(p);
        when(dao.getAll()).thenReturn(publishers);
        assertTrue(CollectionUtils.isEqualCollection(publishers, service.getAll()));
    }
}
