public class MySQLCategoryDaoTest {/*
    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String JDBC_URL = "jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String USER = "user";
    private static final String PASSWORD = "";

    private GenericDao<Category> categoryDao;

    public MySQLCategoryDaoTest() {
        CategoryDao dao = new CategoryDao();
        dao.setDataSource(dataSource());
        categoryDao = dao;
    }

    @BeforeClass
    public static void createSchema() throws Exception {
        String path = Util.path(MySQLCategoryDaoTest.class, "categories-schema.sql");
        RunScript.execute(JDBC_URL, USER, PASSWORD, path, Charset.forName("UTF8"), false);
    }

    @Before
    public void importDataSet() throws Exception {
        IDataSet dataSet = readDataSet();
        cleanlyInsert(dataSet);
    }

    private IDataSet readDataSet() throws Exception {
        String path = Util.path(MySQLBookDaoTest.class, "categories-data.xml");
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
    public void createCategory() {
        Category category = categoryDao.create();

        assertNotNull(category);
        assertNotNull(category.getId());
    }

    @Test
    public void deleteExistingCategory() {
        Category category = categoryDao.create();

        categoryDao.delete(category);
        assertNull(category.getId());
    }

    @Test(expected = Exception.class)
    public void deleteNullCategory() {
        categoryDao.delete(null);
    }

    @Test(expected = DaoException.class)
    public void deleteNotExistingCategory() {
        Category category = new Category();

        categoryDao.delete(category);
    }

    @Test
    public void findExistingById() {
        Category category = categoryDao.create();

        assertNotNull(categoryDao.findById(category.getId()));
    }

    @Test(expected = DaoException.class)
    public void findNotExistingById() {
        Category category = categoryDao.create();
        categoryDao.findById(category.getId() + 10_000);
    }

    @Test(expected = Exception.class)
    public void findCategoryWithNullId() {
        categoryDao.findById(null);
    }

    @Test
    public void getAllCategories() {
        Set<Category> categories = categoryDao.showEditPage();
        int size = categories.size();
        Category category = new Category();
        categoryDao.persist(category);
        categories = categoryDao.showEditPage();

        assertNotNull(categories);
        assertTrue(categories.size() - size == 1);
    }

    @Test
    public void persistNotExistingCategory() {
        Category category = new Category();
        categoryDao.persist(category);

        assertNotNull(category.getId());
    }

    @Test(expected = DaoException.class)
    public void persistExistingCategory() {
        Category category = new Category();
        category.setId(20);
        categoryDao.persist(category);
    }

    @Test
    public void updateCategory() {
        Category category = new Category("name", "desc");
        category = categoryDao.persist(category);
        category.setDescription("new desc");
        category.setName("new name");
        categoryDao.update(category);
        category = categoryDao.findById(category.getId());

        assertThat(category.getName(), is("new name"));
        assertThat(category.getDescription(), is("new desc"));
    }*/
}
