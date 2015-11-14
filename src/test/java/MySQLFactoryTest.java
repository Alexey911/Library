import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.DaoFactory;
import com.zhytnik.library.dao.GenericDao;
import com.zhytnik.library.dao.jdbc.mysql.MySQLDaoFactory;
import com.zhytnik.library.entity.Book;
import com.zhytnik.library.entity.Publisher;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class MySQLFactoryTest {
    private DaoFactory factory;

    public MySQLFactoryTest() {
        factory = new MySQLDaoFactory();
    }

    @Test
    public void getBookDao() {
        GenericDao dao = factory.getDao(Book.class);
        assertNotNull(dao);
    }

    @Test
    public void getPublisherDao() {
        GenericDao dao = factory.getDao(Publisher.class);
        assertNotNull(dao);
    }

    @Test
    public void getCategoryDao() {
        GenericDao dao = factory.getDao(Publisher.class);
        assertNotNull(dao);
    }

    @Test
    public void getObjectDao() {
        GenericDao dao = null;
        try {
            dao = factory.getDao(Object.class);
        } catch (DaoException ignored) {
        }
        assertNull(dao);
    }
}
