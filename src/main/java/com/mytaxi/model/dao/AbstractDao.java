package com.mytaxi.model.dao;

import com.mytaxi.model.customExceptions.DatabaseRuntimeException;
import com.mytaxi.model.dao.connection.PoolConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<E> implements CrudDao<Integer, E> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDao.class);

    private final String saveQuery;
    private final String findByIdQuery;
    private final String findAllQuery;
    private final String updateQuery;

    protected PoolConnection connector;

    public AbstractDao(String saveQuery, String findByIdQuery, String findAllQuery, String updateQuery, PoolConnection connector) {
        this.saveQuery = saveQuery;
        this.findByIdQuery = findByIdQuery;
        this.findAllQuery = findAllQuery;
        this.updateQuery = updateQuery;
        this.connector = connector;
    }

    protected boolean isExistWithIntegerAndStringParametr(Integer id, String parametr, String query) {

        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.setString(2, parametr);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException isExist", e);
        }
        return false;
    }

    protected boolean isEntityExistByStringParam(String param, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, param);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(" isEntityExistByStringParam error", e);
            throw new DatabaseRuntimeException("isEntityExistByStringParam error", e);
        }
        return false;
    }

    protected boolean isEntityExistByTwoStringParam(String paramFirst, String paramSecond, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, paramFirst);
            statement.setString(2, paramSecond);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(" isEntityExistByStringParam error", e);
            throw new DatabaseRuntimeException("isEntityExistByStringParam error", e);
        }
        return false;
    }

    protected E getEntityByTwoStringParam(String paramFirst, String paramSecond, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, paramFirst);
            statement.setString(2, paramSecond);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(" getElementByStringParam error", e);
            throw new DatabaseRuntimeException("getElementByStringParam error", e);
        }
        return null;
    }

    public void save(E entity) {
        LOGGER.info("Inserting element");
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(saveQuery)) {
            createStatementMapper(entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("inser exeption");
            throw new DatabaseRuntimeException("inser exeption", e);
        }
    }

    public E findById(Integer id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(" getElementByStringParam error",e);
            throw new DatabaseRuntimeException("getElementByStringParam error", e);
        }
        return null;
    }

    public List<E> findAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<E> entities = new ArrayList<>();
                while (resultSet.next()) {
                    entities.add(mapResultSetToEntity(resultSet));
                }
                return entities;
            }
        } catch (SQLException e) {
//            LOGGER.error("");
            throw new DatabaseRuntimeException(e);
        }
    }


    public boolean update(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            updateStatementMapper(entity, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Invalid entity updating", e);
            throw new DatabaseRuntimeException("Invalid entity updating", e);
        }
        return true;
    }


    protected abstract void updateStatementMapper(E entity, PreparedStatement preparedStatement) throws SQLException;

    protected abstract void createStatementMapper(E entity, PreparedStatement preparedStatement) throws SQLException;

    protected abstract E mapResultSetToEntity(ResultSet entity) throws SQLException;
}