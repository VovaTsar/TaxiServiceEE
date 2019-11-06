package my.project.model.dao.connector;

import my.project.model.exception.InvalidDatabaseConnectionException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ResourceBundle;

public final class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static final BasicDataSource DATA_SOURCE = new BasicDataSource();

    public ConnectionPool(String fileConfigName) {
        ResourceBundle resource = ResourceBundle.getBundle(fileConfigName);

        DATA_SOURCE.setDriverClassName("com.mysql.cj.jdbc.Driver");
        DATA_SOURCE.setUrl(resource.getString("db.url"));
        DATA_SOURCE.setUsername(resource.getString("db.user"));
        DATA_SOURCE.setPassword(resource.getString("db.password"));
        DATA_SOURCE.setMinIdle(5);
        DATA_SOURCE.setMaxIdle(10);
        DATA_SOURCE.setMaxOpenPreparedStatements(100);
    }

    public Connection getConnection() {
        try {
            return DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Could not get connection from database" + e.getMessage());
            throw new InvalidDatabaseConnectionException("Could not get connection from database " + e.getMessage());
        }
    }
//    public class ConnectionPool {
//        private static volatile DataSource dataSource;
//
//        public static DataSource getDataSource() {
//            if ( dataSource == null ) {
//                synchronized (DataSource.class) {
//                    if ( dataSource == null ) {
//                        BasicDataSource bs = new BasicDataSource();
//                        bs.setUrl(ResourceBundle.getBundle("dbconnection").getString("url"));
//                        bs.setDriverClassName(ResourceBundle.getBundle("dbconnection").getString("driver"));
//                        bs.setUsername(ResourceBundle.getBundle("dbconnection").getString("login"));
//                        bs.setPassword(ResourceBundle.getBundle("dbconnection").getString("pass"));
//                        bs.setMinIdle(5);
//                        bs.setMaxIdle(10);
//                        bs.setMaxOpenPreparedStatements(100);
//                        dataSource = bs;
//                    }
//                }
//            }
//            return dataSource;
//        }
//
    }
