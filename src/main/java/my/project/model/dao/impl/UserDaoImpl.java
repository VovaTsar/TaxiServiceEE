package my.project.model.dao.impl;

import my.project.model.dao.connector.ConnectionPool;
import my.project.model.entity.Role;
import org.apache.log4j.Logger;
import my.project.model.entity.UserEntity;
import my.project.model.dao.AbstractDao;
import my.project.model.dao.UserDao;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<UserEntity> implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private static final String INSERT_USER = "INSERT INTO tservice.users(user_name, user_surname, user_email, user_password, user_role) VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM tservice.users WHERE user_id = ?";
    private static final String FIND_ALL_USERS = "SELECT * FROM tservice.users LIMIT ?, ?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM tservice.users WHERE user_email = ?";
    private static final String COUNT = "SELECT * FROM tservice.users";
    private static final String UPDATE_USER = "UPDATE tservice.users SET user_name = ?, user_surname = ?, user_email = ?, user_password = ?, user_role = ?,  WHERE user_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM tservice.users WHERE user_id = ?";


    public UserDaoImpl(ConnectionPool connector) {
        super(connector);
    }

    @Override
    public boolean save(UserEntity userEntity) {
        return save(userEntity, INSERT_USER);
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<UserEntity> findAll(int currentPage, int recordsPerPage) {
        return findAll(FIND_ALL_USERS, currentPage, recordsPerPage);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return findOneByStringParam(email, FIND_BY_EMAIL);
    }


    @Override
    public void update(UserEntity userEntity) {
        update(userEntity, UPDATE_USER);
    }

    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    public int getNumberOfRows() {
        return getNumberOfRows(COUNT);
    }


    @Override
    protected Optional<UserEntity> mapResultSetToEntity(ResultSet user) throws SQLException {


        return Optional.ofNullable(UserEntity.builder()
                .withId(user.getInt(1))
                .withName(user.getString(2))
                .withSurname(user.getString(3))
                .withEmail(user.getString(4))
                .withPassword(user.getString(5))
                .withRole(Role.valueOf(user.getString(6)))
                .build());
    }

    @Override
    protected void updateStatementMapper(UserEntity userEntity, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(userEntity, preparedStatement);
        preparedStatement.setInt(6, userEntity.getId());
    }

    @Override
    protected void createStatementMapper(UserEntity userEntity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, userEntity.getName());
        preparedStatement.setString(2, userEntity.getSurname());
        preparedStatement.setString(3, userEntity.getEmail());
        preparedStatement.setString(4, userEntity.getPassword());
        preparedStatement.setString(5, userEntity.getRole().toString());
    }

}
