package com.zhytnik.service;

import com.zhytnik.library.dao.CategoryDao;
import com.zhytnik.library.domain.Category;
import com.zhytnik.library.service.CategoryService;
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
public class CategoryServiceTest {
    @InjectMocks
    private CategoryService service = new CategoryService();

    @Mock
    private CategoryDao dao;

    @Test
    public void create() {
        Category category = service.create();
        Assertions.assertThat(category).isNotNull();
    }

    @Test()
    public void findById() {
        Category category = new Category();
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
    public void addUniqueCategory() {
        Category category = new Category("name", "desc");
        when(dao.hasUniqueName(category)).thenReturn(true);
        service.add(category);
        verify(dao).persist(category);
    }

    @Test(expected = NotUniqueException.class)
    public void addNotUniqueCategory() {
        Category category = new Category("name", "desc");
        service.add(category);
    }

    @Test
    public void update() {
        Category category = new Category("name", "desc");
        when(dao.hasUniqueName(category)).thenReturn(true);
        service.update(category);
        verify(dao).update(category);
    }

    @Test
    public void delete() {
        Integer id = 1;
        service.delete(id);
        verify(dao).delete(id);
    }

    @Test
    public void getAll() {
        List<Category> categories = new ArrayList<>();
        Category c = new Category("name", "desc");
        categories.add(c);
        when(dao.getAll()).thenReturn(categories);
        assertTrue(CollectionUtils.isEqualCollection(categories, service.getAll()));
    }
}
