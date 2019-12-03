package com.taxi.model.dao.connection;

import com.taxi.model.exception.DataBaseRuntimeException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PoolConnection {

    private static final Logger logger = Logger.getLogger(PoolConnection.class);
    private DataSource dataSource;

    public PoolConnection() {
        try {
            ResourceBundle dbConfig = ResourceBundle.getBundle("db");
            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setUrl(dbConfig.getString("db.connection.url"));
            basicDataSource.setDriverClassName(dbConfig.getString("db.connection.driver"));
            basicDataSource.setUsername(dbConfig.getString("db.connection.username"));
            basicDataSource.setPassword(dbConfig.getString("db.connection.password"));
            basicDataSource.setMinIdle(Integer.parseInt(dbConfig.getString("db.connection.minIdle")));
            basicDataSource.setMaxIdle(Integer.parseInt(dbConfig.getString("db.connection.maxIdle")));
            basicDataSource.setMaxActive(Integer.parseInt(dbConfig.getString("db.connection.maxActive")));
            basicDataSource.setMaxOpenPreparedStatements(Integer.parseInt(dbConfig.getString("db.connection.maxOpenPreparedStatements")));
            dataSource = basicDataSource;
        } catch (Exception e) {
            logger.warn("Error connection", e);
        }
    }


    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.warn("getConnection error", e);
            throw new DataBaseRuntimeException();
        }
    }

}