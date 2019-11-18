package com.robosh.model.dao.impl;

import com.robosh.model.customExceptions.DatabaseRuntimeException;
import com.robosh.model.dao.AbstractDao;

import com.robosh.model.dao.OrderDao;
import com.robosh.model.dao.connection.PoolConnection;
import com.robosh.model.entity.*;
import com.robosh.model.entity.enums.OrderStatus;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    private static final String FIND_BY_ID = "SELECT * FROM  taxi_database.order WHERE  id_order  =(?);";

    private static final String FIND_BY_ID_DRIVER = "SELECT * FROM  taxi_database.order WHERE  id_driver  =(?);";

    private static final String FIND_BY_ID_DRIVER_WITH_LIMIT = "SELECT * FROM  taxi_database.order WHERE  id_driver  =(?) ORDER BY  id_order  DESC limit ?, ?;";

    private static final String FIND_ALL = "SELECT * FROM taxi_database.order ";

    private static final String SAVE_QUERY = "INSERT INTO  taxi_database.order ( order_status, id_client, id_driver, id_adress_departure, id_adress_arrive, id_coupon, cost, cost_with_discount ) VALUES ((?), (?), (?), (?), (?), (?), (?), (?));";

    private static final String INSERT_WITHOUT_COUPON = "INSERT INTO  taxi_database.order (order_status, id_client, id_driver, id_adress_departure, id_adress_arrive, cost, cost_with_discount ) VALUES ((?), (?), (?), (?), (?), (?), (?));";

    private static final String UPDATE_QUERY = "UPDATE  taxi_database.order SET  order_status  = (?) WHERE id_order = (?);";

    private static final String IS_SUCH_VOYAGE = "SELECT * FROM  taxi_database.order WHERE  id_order = (?) AND id_driver  = (?) AND order_status  = (?);";

    private static final String GET_COUNT_ORDERS = "SELECT count(*) FROM  taxi_database.order WHERE id_driver=?;";

    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);

    public OrderDaoImpl(PoolConnection connector) {
        super(SAVE_QUERY, FIND_BY_ID, FIND_ALL, UPDATE_QUERY, connector);
    }

    @Override
    public List<Order> getAllOrdersByDriverId(Integer idDriver, int row, int limit) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement ps = connector.getConnection().prepareStatement(FIND_BY_ID_DRIVER_WITH_LIMIT)) {
            ps.setInt(1, idDriver);
            ps.setInt(2, row);
            ps.setInt(3, limit);
            final ResultSet rs = ps.executeQuery();
            return getOrders(orders, rs);
        } catch (SQLException e) {
            LOG.error("SQLException occurred in OrderDao", e);
            return null;
        }
    }

    private List<Order> getOrders(List<Order> orders, ResultSet rs) throws SQLException {

        while (rs.next()) {
            LOG.debug("check is rs has next");
            Order order = mapResultSetToEntity(rs);
            orders.add(order);
        }
        return orders;
    }
    @Override
    public void createWithoutCoupon(Order order) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WITHOUT_COUPON)) {
            preparedStatement.setString(1, order.getOrderStatus().toString());
            preparedStatement.setInt(2, order.getClient().getPersonId());
            preparedStatement.setInt(3, order.getDriver().getPersonId());
            preparedStatement.setInt(4, order.getAddressDeparture().getIdAddress());
            preparedStatement.setInt(5, order.getAddressArrive().getIdAddress());
            preparedStatement.setInt(6, order.getCost());
            preparedStatement.setInt(7, order.getCostWithDiscount());
            preparedStatement.execute();

        } catch (SQLException e) {
            LOGGER.error("Invalid user search" , e);
            throw new DatabaseRuntimeException("Invalid user search", e);
        }

    }

    @Override
    public long getCountOrders(Integer idDriver) {
        long countOrders = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNT_ORDERS)) {
            preparedStatement.setInt(1, idDriver);


            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                LOG.debug("check is rs has next");
                countOrders = rs.getLong(1);
            }
        } catch (SQLException e) {
            LOG.error("SQLException occurred in OrderDao", e);
        }
        return countOrders;
    }

    @Override
    public boolean updateOrderStatus(Integer idOrder, OrderStatus orderStatus) {
        return isExistWithIntegerAndStringParametr(idOrder,orderStatus.toString(),UPDATE_QUERY);
    }

    @Override
    public boolean isCorrespondOrderAndDriver(Integer idOrder, Integer idDriver) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(IS_SUCH_VOYAGE)) {
            preparedStatement.setInt(1, idOrder);
            preparedStatement.setInt(2, idDriver);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Invalid user search" , e);
            throw new DatabaseRuntimeException("Invalid user search", e);
        }
        return false;
    }

    @Override
    protected void updateStatementMapper(Order entity, PreparedStatement preparedStatement) throws SQLException {

    }
    //order_status, id_client, id_driver, id_adress_departure, id_adress_arrive, id_coupon, cost, cost_with_discount
    @Override
    protected void createStatementMapper(Order entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getOrderStatus().toString());
        preparedStatement.setInt(2, entity.getClient().getPersonId());
        preparedStatement.setInt(3, entity.getDriver().getPersonId());
        preparedStatement.setInt(4, entity.getAddressDeparture().getIdAddress());
        preparedStatement.setInt(5, entity.getAddressArrive().getIdAddress());
        preparedStatement.setInt(6, entity.getCoupon().getIdCoupon());
        preparedStatement.setInt(7, entity.getCost());
        preparedStatement.setInt(8, entity.getCostWithDiscount());
    }

    @Override
    protected Order mapResultSetToEntity(ResultSet entity) throws SQLException {
        Client client = Client.builder()
                .withPersonId(entity.getInt("id_client"))
                .build();
        Driver driver = Driver.builder()
                .withPersonId(entity.getInt("id_driver"))
                .build();
        Address departure = Address.builder()
                .withIdAddress(entity.getInt("id_adress_departure"))
                .build();
        Address arrive = Address.builder()
                .withIdAddress(entity.getInt("id_adress_arrive"))
                .build();
        Coupon coupon = Coupon.builder()
                .withIdCoupon(entity.getInt("id_coupon"))
                .build();
        return Order.builder()
                .withIdOrder(entity.getInt("id_order"))
                .withOrderStatus(OrderStatus.valueOf(entity.getString("order_status")))
                .withClient(client)
                .withDriver(driver)
                .withAddressDeparture(departure)
                .withAddressArrive(arrive)
                .withCoupon(coupon)
                .withCost(entity.getInt("cost"))
                .withCostWithDiscount(entity.getInt("cost_with_discount"))
                .build();
    }
}
