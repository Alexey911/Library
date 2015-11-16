import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.GenericDao;
import com.zhytnik.library.dao.jdbc.AbstractJDBCDao;
import com.zhytnik.library.dao.jdbc.mysql.BookDao;
import com.zhytnik.library.dao.jdbc.mysql.CategoryDao;
import com.zhytnik.library.dao.jdbc.mysql.PublisherDao;
import com.zhytnik.library.entity.Book;
import com.zhytnik.library.entity.Category;
import com.zhytnik.library.entity.Publisher;
import com.zhytnik.library.tools.Utils;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class MySQLBookDaoTest {
    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String JDBC_URL = "jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String USER = "user";
    private static final String PASSWORD = "";

    private GenericDao<Book> bookDao;

    public MySQLBookDaoTest() {
        bookDao = new BookDao();
        ((AbstractJDBCDao) bookDao).setDataSource(dataSource());
    }

    @BeforeClass
    public static void createSchema() throws Exception {
        String path = Util.path(MySQLBookDaoTest.class, "books-schema.sql");
        RunScript.execute(JDBC_URL, USER, PASSWORD, path, Charset.forName("UTF8"), false);
    }

    @Before
    public void importDataSet() throws Exception {
        IDataSet dataSet = readDataSet();
        cleanlyInsert(dataSet);
    }

    private IDataSet readDataSet() throws Exception {
        String path = Util.path(MySQLBookDaoTest.class, "books-data.xml");
        return new FlatXmlDataSetBuilder().build(new File(path));
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    private DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(JDBC_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    @Test
    public void createBook() {
        Book book = bookDao.create();

        assertNotNull(book);
        assertNotNull(book.getId());
    }

    @Test
    public void deleteExistingBook() {
        Book book = bookDao.create();
        bookDao.delete(book);

        assertNull(book.getId());
    }

    @Test(expected = DaoException.class)
    public void deleteNotExistingBook() {
        Book book = new Book();

        bookDao.delete(book);
    }

    @Ignore
    @Test
    public void persistWithDependencies() {
        Book book = initialBook();
        Book copy = bookDao.findById(book.getId());

        assertThat(book.getName(), is(copy.getName()));
        assertThat(book.getAuthors(), is(copy.getAuthors()));
        assertThat(book.getPublisher().getName(), is(copy.getPublisher().getName()));
    }

    private Book initialBook() {
        Book book = new Book();

        Set<Category> categories = new HashSet<>();
        Category c = new Category("name", "desc");
        categories.add(c);

        CategoryDao categoryDao = (CategoryDao) Utils.getContext().getBean("categoryDao");

        categoryDao.persist(c);

        Publisher p = new Publisher("name", "address");
        PublisherDao publisherGenericDao = (PublisherDao) Utils.getContext().getBean("publisherDao");
        publisherGenericDao.persist(p);

        book.setCategories(categories);
        book.setPublisher(p);
        book.setName("name");
        book.setAuthors("author");
        return bookDao.persist(book);
    }

    @Test
    public void updateBook() {
        Book book = initialBook();

        book.setName("new name");

        bookDao.update(book);

        Book copy = bookDao.findById(book.getId());

        assertThat(book.getName(), is(copy.getName()));
    }

    @Test
    public void getAll() {
        Set<Book> books = bookDao.getAll();
        assertTrue(books.size() == 1);
    }

    @Test
    public void findExitingById() {
        Book book = bookDao.findById(1);
        assertNotNull(book);
    }

    @Test
    public void deleteBook() {
        bookDao.delete(bookDao.findById(1));
    }
}
