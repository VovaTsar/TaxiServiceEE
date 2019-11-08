package my.project.model.dao.impl;

import my.project.model.dao.AbstractDao;
import my.project.model.dao.AddressDao;
import my.project.model.dao.connector.ConnectionPool;
import my.project.model.entity.AddressEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AddressDaoImpl extends AbstractDao<AddressEntity> implements AddressDao {
    private static final String INSERT_ADDRESS = "INSERT INTO tservice.addresses(address_street, address_house, address_x, address_y) VALUES(?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM tservice.addresses WHERE address_id = ?";
    private static final String FIND_ALL_ADDRESSES = "SELECT * FROM tservice.addresses LIMIT ?, ?";
    private static final String COUNT = "SELECT * FROM tservice.addresses";
    private static final String UPDATE_ADDRESS = "UPDATE tservice.addresses SET address_street = ?, address_house = ?, address_x = ?, address_y = ? WHERE address_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM tservice.addresses WHERE address_id = ?";


    public AddressDaoImpl(ConnectionPool connector) {
        super(connector);
    }

    @Override
    public boolean save(AddressEntity entity) {
        return save(entity, INSERT_ADDRESS);
    }

    @Override
    public Optional<AddressEntity> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<AddressEntity> findAll(int currentPage, int recordsPerPage) {
        return findAll(FIND_ALL_ADDRESSES, currentPage, recordsPerPage);
    }
    @Override
    public void update(AddressEntity entity) {
        update(entity, UPDATE_ADDRESS);
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
    protected void updateStatementMapper(AddressEntity addressEntity, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(addressEntity, preparedStatement);
        preparedStatement.setInt(5, addressEntity.getId());
    }

    @Override
    protected void createStatementMapper(AddressEntity addressEntity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, addressEntity.getStreet());
        preparedStatement.setInt(2, addressEntity.getHouse());
        preparedStatement.setInt(3, addressEntity.getCoordinateX());
        preparedStatement.setInt(4, addressEntity.getCoordinateY());
    }

    @Override
    protected Optional<AddressEntity> mapResultSetToEntity(ResultSet order) throws SQLException {
        return Optional.of(AddressEntity.builder().withId(order.getInt(1))
                .withStreet(order.getString(2))
                .withHouse(order.getInt(3))
                .withCoordinateX(order.getInt(4))
                .withCoordinateY(order.getInt(5))
                .build());
    }
}
