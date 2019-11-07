package my.project.model.dao.impl;

import my.project.model.dao.connector.ConnectionPool;
import my.project.model.entity.AddressEntity;
import my.project.model.entity.TaxiEntity;
import my.project.model.entity.CarType;
import my.project.model.entity.UserEntity;
import my.project.model.dao.AbstractDao;
import my.project.model.dao.TaxiDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TaxiDaoImpl extends AbstractDao<TaxiEntity> implements TaxiDao {
    private static final String INSERT_TAXI = "INSERT INTO tservice.taxis( taxi_number, taxi_type, taxi_driver, taxi_busy) VALUES(?, ?, ?, ? )";
    private static final String FIND_BY_ID = "SELECT * FROM tservice.taxis WHERE taxi_id = ?";
    private static final String FIND_ALL_TAXIS = "SELECT * FROM tservice.taxis";
    private static final String UPDATE_TAXI = "UPDATE tservice.taxis SET  taxi_number = ?, taxi_type = ?, taxi_driver = ?, taxi_busy = ? WHERE taxi_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM tservice.taxis WHERE taxi_id = ?";

    public TaxiDaoImpl(ConnectionPool connector) {
        super(connector);
    }

    @Override
    public boolean save(TaxiEntity goal) {
        return save(goal, INSERT_TAXI);
    }

    @Override
    public Optional<TaxiEntity> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<TaxiEntity> findAll() {
        return findAll(FIND_ALL_TAXIS);
    }

    @Override
    public void update(TaxiEntity goal) {
        update(goal, UPDATE_TAXI);
    }

    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    protected void updateStatementMapper(TaxiEntity taxiEntity, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(taxiEntity, preparedStatement);
        preparedStatement.setInt(5, taxiEntity.getId());
    }

    @Override
    protected void createStatementMapper(TaxiEntity taxiEntity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, taxiEntity.getCarNumber());
        preparedStatement.setString(2, taxiEntity.getCarType().toString());
        preparedStatement.setInt(3, taxiEntity.getDriver().getId());
        preparedStatement.setBoolean(4, taxiEntity.isBusy());
    }

    @Override
    protected Optional<TaxiEntity> mapResultSetToEntity(ResultSet taxi) throws SQLException {

        UserEntity userEntity = UserEntity.builder()
                .withId(4)
                .build();

        return Optional.of(TaxiEntity.builder().withId(taxi.getInt(1))
                .withCarNumber(taxi.getString(2))
                .withCarType(CarType.valueOf(taxi.getString(3)))
                .withDriver(userEntity)
                .withBusy(taxi.getBoolean(5))
                .build());
    }


}
