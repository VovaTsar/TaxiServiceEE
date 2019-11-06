package my.project.model.dao.impl;

import my.project.model.entity.*;
import my.project.model.dao.AbstractDao;
import my.project.model.dao.connector.ConnectionPool;
import my.project.model.dao.OrderDao;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl extends AbstractDao<OrderEntity> implements OrderDao {
    private static final String INSERT_ORDER = "INSERT INTO tservice.orders(order_cost, order_startPoint, order_endPoint, order_status, user_id, taxi_id) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM tservice.orders WHERE orders_id = ?";
    private static final String FIND_ALL_ORDERS = "SELECT * FROM tservice.orders";
    private static final String UPDATE_ORDER = "UPDATE tservice.orders SET order_cost = ?, order_startPoint = ?, order_endPoint = ?, order_status = ?, user_id = ?, taxi_id = ? WHERE orders_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM tservice.orders WHERE orders_id = ?";


    public OrderDaoImpl(ConnectionPool connector) {
        super(connector);
    }

    @Override
    public boolean save(OrderEntity story) {
        return save(story, INSERT_ORDER);
    }

    @Override
    public Optional<OrderEntity> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<OrderEntity> findAll() {
        return findAll(FIND_ALL_ORDERS);
    }

    @Override
    public void update(OrderEntity story) {
        update(story, UPDATE_ORDER);
    }


    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    protected Optional<OrderEntity> mapResultSetToEntity(ResultSet order) throws SQLException {
        AddressEntity startPoint = AddressEntity.builder()
                .withId(order.getInt(3))
                .build();
        AddressEntity endPoint = AddressEntity.builder()
                .withId(order.getInt(4))
                .build();

        UserEntity userEntity = UserEntity.builder()
                .withId(order.getInt(6))
                .build();
        TaxiEntity taxiEntity = TaxiEntity.builder()
                .withId(order.getInt(7))
                .build();

        return Optional.of(OrderEntity.builder().withId(order.getInt(1))
                .withCost(order.getDouble(2))
                .withStartPoint(startPoint)
                .withEndPoint(endPoint)
                .withStatus(OrderStatus.valueOf(order.getString(5)))
                .withUser(userEntity)
                .withTaxi(taxiEntity)
                .build());
    }

    @Override
    protected void updateStatementMapper(OrderEntity orderEntity, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(orderEntity, preparedStatement);
        preparedStatement.setInt(7, orderEntity.getId());
    }

    @Override
    protected void createStatementMapper(OrderEntity orderEntity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setDouble(1, orderEntity.getCost());
        preparedStatement.setInt(2, orderEntity.getStartPoint().getId());
        preparedStatement.setInt(3, orderEntity.getEndPoint().getId());
        preparedStatement.setString(4, orderEntity.getStatus().toString());
        preparedStatement.setInt(5, orderEntity.getUserEntity().getId());
        preparedStatement.setInt(6, orderEntity.getTaxiEntity().getId());

    }



    @Override
    public Optional<TaxiEntity> findByCarTypeAndBusy(CarType carType) {
        return Optional.empty();
    }

    @Override
    public void updateOrderStatus(OrderEntity story) {

    }
}
