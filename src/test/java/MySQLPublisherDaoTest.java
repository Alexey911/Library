public class MySQLPublisherDaoTest {/*
    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String JDBC_URL = "jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String USER = "user";
    private static final String PASSWORD = "";

    private GenericDao<Publisher> publisherDao;

    public MySQLPublisherDaoTest() {
        PublisherDao dao = new PublisherDao();
        dao.setDataSource(dataSource());
        publisherDao = dao;
    }

    @BeforeClass
    public static void createSchema() throws Exception {
        String path = Util.path(MySQLPublisherDaoTest.class, "publishers-schema.sql");
        RunScript.execute(JDBC_URL, USER, PASSWORD, path, Charset.forName("UTF8"), false);
    }

    @Before
    public void importDataSet() throws Exception {
        IDataSet dataSet = readDataSet();
        cleanlyInsert(dataSet);
    }

    private IDataSet readDataSet() throws Exception {
        String path = Util.path(MySQLBookDaoTest.class, "publishers-data.xml");
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
    public void createPublisher() {
        Publisher p = publisherDao.create();

        assertNotNull(p);
        assertNotNull(p.getId());
    }

    @Test
    public void deleteExistingPublisher() {
        Publisher p = publisherDao.create();

        publisherDao.delete(p);
        assertNull(p.getId());
    }

    @Test(expected = Exception.class)
    public void deleteNullPublisher() {
        publisherDao.delete(null);
    }

    @Test(expected = DaoException.class)
    public void deleteNotExistingPublisher() {
        Publisher p = new Publisher();

        publisherDao.delete(p);
    }

    @Test
    public void findExistingById() {
        Publisher p = publisherDao.create();

        assertNotNull(publisherDao.findById(p.getId()));
    }

    @Test(expected = DaoException.class)
    public void findNotExistingById() {
        Publisher p = publisherDao.create();
        publisherDao.findById(p.getId() + 10_000);
    }

    @Test(expected = Exception.class)
    public void findCategoryWithNullId() {
        publisherDao.findById(null);
    }

    @Test
    public void getAllPublishers() {
        Set<Publisher> publishers = publisherDao.showEditPage();
        int size = publishers.size();
        Publisher p = new Publisher();
        publisherDao.persist(p);
        publishers = publisherDao.showEditPage();

        assertNotNull(publishers);
        assertTrue(publishers.size() - size == 1);
    }

    @Test
    public void persistNotExistingPublisher() {
        Publisher p = new Publisher();
        publisherDao.persist(p);

        assertNotNull(p.getId());
    }

    @Test(expected = DaoException.class)
    public void persistExistingPublisher() {
        Publisher p = new Publisher();
        p.setId(20);
        publisherDao.persist(p);
    }

    @Test
    public void updatePublisher() {
        Publisher p = new Publisher("name", "desc");
        p = publisherDao.persist(p);
        p.setAddress("new desc");
        p.setName("new name");
        publisherDao.update(p);
        p = publisherDao.findById(p.getId());

        assertThat(p.getName(), is("new name"));
        assertThat(p.getAddress(), is("new desc"));
    }*/
}
