import com.zhytnik.library.dao.CategoryDao;
import com.zhytnik.library.domain.Category;
import com.zhytnik.library.service.CategoryService;
import com.zhytnik.library.service.exception.NotFoundItemException;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Ignore
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

    @Test
    public void isUnique() {
        Category category = new Category();

        /*when(dao.hasUniqueName("nam")).thenReturn(false);
        category.setName("nam");
        assertFalse(service.isUnique(category));

        when(dao.hasUniqueName("name")).thenReturn(true);
        category.setName("name");
        assertTrue(service.isUnique(category));

        category.setId(1);
        when(dao.hasUniqueName("name")).thenReturn(false);
        when(dao.findById(1)).thenReturn(category);
        assertTrue(service.isUnique(category));

        category.setName("name");
        category.setId(2);
        when(dao.hasUniqueName("name")).thenReturn(false);
        Category categoryById = new Category("nam", null);
        categoryById.setId(2);
        when(dao.findById(2)).thenReturn(categoryById);
        assertFalse(service.isUnique(category));*/
    }

    @Test()
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
        /*when(dao.hasUniqueName("name")).thenReturn(true);*/
        //when(dao.persist(category)).thenReturn(savedCategory);
        service.add(category);
        assertThat(category.getId(), is(savedCategory.getId()));
    }

    @Test
    public void update() {
        Category category = new Category("name", "desc");
       /* when(dao.hasUniqueName("name")).thenReturn(true);*/
        service.update(category);
    }

    @Test
    public void delete() {
        Integer id = 1;
        service.delete(id);
        verify(dao).delete(id);
    }

    @Test
    public void getAll() {
        Set<Category> categories = Collections.singleton(new Category("name", "desc"));
        when(dao.getAll()).thenReturn(categories);
        assertTrue(CollectionUtils.isEqualCollection(categories, service.getAll()));
    }
}
