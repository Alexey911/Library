package com.zhytnik.library.dao.jdbc.mysql;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.DaoFactory;
import com.zhytnik.library.dao.GenericDao;
import com.zhytnik.library.dao.jdbc.AbstractJDBCDao;
import com.zhytnik.library.dao.jdbc.mysql.dependence.BookToCategoriesDependence;
import com.zhytnik.library.dao.jdbc.mysql.dependence.BookToPublisherDependence;
import com.zhytnik.library.entity.Book;
import com.zhytnik.library.entity.Category;
import com.zhytnik.library.entity.Publisher;
import com.zhytnik.library.tools.Settings;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class MySQLDaoFactory implements DaoFactory {
    private static DataSource dataSource;
    private static Logger logger;

    static {
        logger = Logger.getLogger(MySQLDaoFactory.class);
        BasicDataSource dataSource = new BasicDataSource();
        Settings s = Settings.getInstance();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(s.getString("mysql.host"));
        dataSource.setUsername(s.getString("mysql.user"));
        dataSource.setPassword(s.getString("mysql.pass"));
        dataSource.setInitialSize(s.getInteger("mysql.initial_size"));
        dataSource.setMaxIdle(s.getInteger("mysql.max_idle"));
        dataSource.setMaxActive(s.getInteger("mysql.max_active"));
        dataSource.addConnectionProperty("characterEncoding", s.getString("mysql.encoding"));
        dataSource.setDefaultAutoCommit(false);
        MySQLDaoFactory.dataSource = dataSource;
    }

    private Map<Class, DaoCreator> repositories;

    public MySQLDaoFactory() {
        repositories = new HashMap<>();
        repositories.put(Book.class, () -> {
            BookDao dao = new BookDao();
            dao.addDependence(new BookToPublisherDependence(MySQLDaoFactory.this));
            dao.addDependence(new BookToCategoriesDependence(MySQLDaoFactory.this));
            return dao;
        });
        repositories.put(Category.class, CategoryDao::new);
        repositories.put(Publisher.class, PublisherDao::new);
    }

    @Override
    public GenericDao getDao(Class entityClass) throws DaoException {
        DaoCreator creator = repositories.get(entityClass);
        if (creator == null) {
            String msg = "Dao object for " + entityClass + " not found!";
            logger.log(Level.WARN, msg);
            throw new DaoException(msg);
        }
        AbstractJDBCDao dao = creator.create();
        dao.setDataSource(dataSource);
        return dao;
    }

    //ONLY FOR DEBUG
    @Deprecated
    public void setDataSource(DataSource dataSource) {
        MySQLDaoFactory.dataSource = dataSource;
    }

    private interface DaoCreator {
        AbstractJDBCDao create();
    }
}