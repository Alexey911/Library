import com.zhytnik.library.dao.CategoryDao;
import com.zhytnik.library.dao.hibernate.CategoryDaoImpl;
import com.zhytnik.library.domain.Category;
import com.zhytnik.library.service.CategoryService;
import com.zhytnik.library.service.exception.NotFoundItemException;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {
    private CategoryService service;
    private CategoryDao dao;

    @Before
    public void initial() {
        service = new CategoryService();
        dao = Mockito.mock(CategoryDaoImpl.class);
        service.setDao(dao);
    }

    @Test
    public void create() {
        Category category = service.create();
        assertNotNull(category);
    }

    @Test
    public void isUnique() {
        Category category = new Category();

        when(dao.isUniqueName("nam")).thenReturn(false);
        category.setName("nam");
        assertFalse(service.isUnique(category));

        when(dao.isUniqueName("name")).thenReturn(true);
        category.setName("name");
        assertTrue(service.isUnique(category));

        category.setId(1);
        when(dao.isUniqueName("name")).thenReturn(false);
        when(dao.findById(1)).thenReturn(category);
        assertTrue(service.isUnique(category));

        category.setName("name");
        category.setId(2);
        when(dao.isUniqueName("name")).thenReturn(false);
        Category categoryById = new Category("nam", null);
        categoryById.setId(2);
        when(dao.findById(2)).thenReturn(categoryById);
        assertFalse(service.isUnique(category));
    }

    @Test
    public void findById() {
        Category category = new Category();
        when(dao.findById(1)).thenReturn(category);
        assertThat(service.findById(1), is(category));

        when(dao.findById(2)).thenReturn(null);
        try {
            service.findById(2);
            fail();
        } catch (NotFoundItemException ignored) {

        }
    }

    @Test
    public void add() {
        Category category = new Category("name", "desc");

        Category savedCategory = new Category("name", "desc");
        savedCategory.setId(1);
        when(dao.isUniqueName("name")).thenReturn(true);
        when(dao.persist(category)).thenReturn(savedCategory);
        service.add(category);
        assertThat(category.getId(), is(savedCategory.getId()));
    }

    @Test
    public void update() {
        Category category = new Category("name", "desc");
        when(dao.isUniqueName("name")).thenReturn(true);
        service.update(category);
    }

    @Test
    public void delete() {
        Integer id = 1;
        service.delete(id);
    }

    @Test
    public void getAll() {
        Set<Category> categories = Collections.singleton(new Category("name", "desc"));
        when(dao.getAll()).thenReturn(categories);
        assertTrue(CollectionUtils.isEqualCollection(categories, service.getAll()));
    }
}
