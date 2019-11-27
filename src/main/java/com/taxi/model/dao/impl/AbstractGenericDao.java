package com.taxi.model.dao.impl;



import com.taxi.model.exception.DataBaseRuntimeException;
import com.taxi.model.dao.connection.PoolConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractGenericDao<E> {

    protected PoolConnection connector;
    protected static final Logger LOGGER = Logger.getLogger(AbstractGenericDao.class);

    protected AbstractGenericDao(PoolConnection connector) {
        this.connector = connector;
    }

    protected void insert(E element, String query) {
        LOGGER.info("Inserting element");
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setInsertElementProperties(statement, element);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("inser exeption");
            throw new DataBaseRuntimeException("Insert exeption", e);
        }
    }

    protected E getElementByIntegerParam(Integer id, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return parseToOneElement(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("getElementByIntegerParam error",e);
            throw new DataBaseRuntimeException("getElementByIntegerParam error", e);
        }
        return null;
    }
    protected boolean updateByIntegerParam(Integer dataInt, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, dataInt);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can not update element", e);
            throw new DataBaseRuntimeException("Can not update element", e);
        }
        return true;
    }
    protected boolean update(E entity, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setUpdateElementProperties(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can not update element", e);
            throw new DataBaseRuntimeException("Can not update element", e);
        }
        return true;
    }


    protected E getElementByStringParam(String data, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, data);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return parseToOneElement(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(" getElementByStringParam error",e);
            throw new DataBaseRuntimeException("getElementByStringParam error", e);
        }
        return null;
    }

    protected E getElementByTwoStringParam(String data, String secondData, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, data);
            statement.setString(2, secondData);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return parseToOneElement(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(" getElementByStringParam error",e);
            throw new DataBaseRuntimeException("getElementByStringParam error", e);
        }
        return null;
    }

    protected Integer getPointByStringParam(String firstParam, String secondParam, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, firstParam);
            ps.setString(2, secondParam);
            final ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("getElementByIntegerParam error", e);
            throw new DataBaseRuntimeException("getElementByIntegerParam error", e);
        }
        return -1;
    }

    protected List<E> getList( String query) {
        LOGGER.info("Getting");
        ResultSet resultSet = null;
        List<E> list;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            resultSet = statement.executeQuery();
            list = parseAllElements(resultSet);
        } catch (SQLException e) {
            LOGGER.error("getList error", e);
            throw new DataBaseRuntimeException("getList error", e);
        }
        LOGGER.info("Returning list  assigned to route");
        return list;
    }

    protected List<E> parseAllElements(ResultSet resultSet) {
        List<E> elements = new ArrayList<>();
        try {
            while (resultSet.next()) {
                elements.add(parseToOneElement(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("parseAllElements ResultSet", e);
            throw new DataBaseRuntimeException("parseAllElements ResultSet", e);
        }
        return elements;
    }

    protected boolean isExist(String data, String secondData,String query) {

        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, data);
            ps.setString(2, secondData);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException isExist", e);
        }
        return false;
    }

    protected boolean isExistWithOneStringParametr (String data,String query) {

        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, data);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException isExist", e);
        }
        return false;
    }
    protected boolean isExistWithIntegerAndStringParametr (Integer id, String data,String query) {

        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.setString(2, data);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException isExist", e);
        }
        return false;
    }

    protected abstract void setInsertElementProperties(PreparedStatement statement, E element)  throws SQLException;

    protected abstract void setUpdateElementProperties(PreparedStatement statement, E element)  throws SQLException;

    protected abstract E parseToOneElement(ResultSet resultSet) throws SQLException;
}