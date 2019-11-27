package com.taxi.model.dao.impl;

import com.taxi.model.dao.*;
import com.taxi.model.dao.connection.PoolConnection;
import com.taxi.model.entity.Coupon;
import com.taxi.model.entity.Driver;
import com.taxi.model.entity.Order;
import com.taxi.model.entity.enums.OrderStatus;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderDaoImpl extends AbstractGenericDao<Order> implements OrderDao {
    private static final String READ_BY_ID = "SELECT * FROM  taxi_database.order WHERE  id_order  =(?);";

    private static final String READ_BY_ID_DRIVER = "SELECT * FROM  taxi_database.order WHERE  id_driver  =(?);";

    private static final String READ_BY_ID_DRIVER_WITH_LIMIT = "SELECT * FROM  taxi_database.order WHERE  id_driver  =(?) ORDER BY  id_order  DESC limit ?, ?;";

    private static final String READ_ALL = "SELECT * FROM taxi_database.order ";

    private static final String INSERT = "INSERT INTO  taxi_database.order ( order_status, id_client, id_driver, id_adress_departure, id_adress_arrive, id_coupon, cost, cost_with_discount ) VALUES ((?), (?), (?), (?), (?), (?), (?), (?));";

    private static final String INSERT_WITHOUT_COUPON = "INSERT INTO  taxi_database.order (order_status, id_client, id_driver, id_adress_departure, id_adress_arrive, cost, cost_with_discount ) VALUES ((?), (?), (?), (?), (?), (?), (?));";

    private static final String UPDATE = "UPDATE  taxi_database.order SET  order_status  = (?) WHERE id_order = (?);";

    private static final String IS_SUCH_VOYAGE = "SELECT * FROM  taxi_database.order WHERE  id_order = (?) AND id_driver  = (?) AND order_status  = (?);";

    private static final String GET_COUNT_ORDERS = "SELECT count(*) FROM  taxi_database.order WHERE id_driver=?;";

    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);

    private ClientDao clientDao;
    private DriverDao driverDao;
    private AddressDao addressDao;
    private CouponDao couponDao;


    public OrderDaoImpl(PoolConnection connector, ClientDao clientDao, DriverDao driverDao, AddressDao addressDao, CouponDao couponDao) {
        super(connector);
        this.clientDao = clientDao;
        this.driverDao = driverDao;
        this.addressDao = addressDao;
        this.couponDao = couponDao;

    }

    @Override
    public void create(Order order) {
        try (PreparedStatement statement = connector.getConnection().prepareStatement(INSERT)) {
            LOG.debug("Executed query" + INSERT);
            setInsertElementProperties(statement, order);
            statement.setInt(6, order.getCoupon().getIdCoupon());
            statement.setInt(7, order.getCost());
            statement.setInt(8, order.getCostWithDiscount());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("SQLException occurred in OrderDaoImpl", e);
        }
    }


    @Override
    public void createWithoutCoupon(Order order) {
        try (PreparedStatement statement = connector.getConnection().prepareStatement(INSERT_WITHOUT_COUPON)) {
            LOG.debug("Executed query" + INSERT_WITHOUT_COUPON);
            setInsertElementProperties(statement, order);
            statement.setInt(6, order.getCost());
            statement.setInt(7, order.getCostWithDiscount());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("SQLException occurred in OrderDaoImpl", e);
        }
    }


    @Override
    public long findCountOrders(Integer idDriver) {
        long countOrders = 0;
        try (PreparedStatement ps = connector.getConnection().prepareStatement(GET_COUNT_ORDERS)) {
            ps.setInt(1, idDriver);

            final ResultSet rs = ps.executeQuery();

            LOG.debug("Executed query" + GET_COUNT_ORDERS);
            if (rs.next()) {
                LOG.debug("check is rs has next");
                countOrders = rs.getLong(1);
            }
        } catch (SQLException e) {
            LOG.error("SQLException occurred in OrderDaoImpl", e);
        }
        return countOrders;
    }


    @Override
    public Order findById(Integer id) {
        return super.getElementByIntegerParam(id, READ_BY_ID);
    }


    @Override
    public List<Order> findAllOrdersByDriverId(Integer idDriver, int row, int limit) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement ps = connector.getConnection().prepareStatement(READ_BY_ID_DRIVER_WITH_LIMIT)) {
            ps.setInt(1, idDriver);
            ps.setInt(2, row);
            ps.setInt(3, limit);
            final ResultSet rs = ps.executeQuery();
            return getOrders(orders, rs);
        } catch (SQLException e) {
            LOG.error("SQLException occurred in OrderDaoImpl", e);
            return null;
        }
    }

    private List<Order> getOrders(List<Order> orders, ResultSet rs) throws SQLException {
        LOG.debug("Executed query" + READ_BY_ID_DRIVER);
        while (rs.next()) {
            LOG.debug("check is rs has next");
            Order order = parseToOneElement(rs);
            orders.add(order);
        }
        return orders;
    }


    @Override
    public List<Order> findAll() {
        return super.getList(READ_ALL);
    }


    @Override
    public boolean updateOrderStatus(Integer idOrder, OrderStatus orderStatus) {
        try (PreparedStatement ps = connector.getConnection().prepareStatement(UPDATE)) {
            ps.setString(1, orderStatus.toString());
            ps.setInt(2, idOrder);
            ps.execute();
            LOG.debug("Executed query" + UPDATE);
            return true;
        } catch (SQLException e) {
            LOG.error("SQLException occurred in OrderDaoImpl", e);
        }
        return false;
    }


    @Override
    public boolean isCorrespondOrderAndDriver(Integer idOrder, Integer idDriver) {
        try (PreparedStatement ps = connector.getConnection().prepareStatement(IS_SUCH_VOYAGE)) {
            ps.setInt(1, idOrder);
            ps.setInt(2, idDriver);
            ps.setString(3, OrderStatus.EXECUTING.toString());
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query" + IS_SUCH_VOYAGE);
            if (rs.next()) {
                LOG.debug("check is rs has next");
                return true;
            }
        } catch (SQLException e) {
            LOG.error("SQLException occurred in OrderDaoImpl", e);
        }
        return false;
    }


    @Override
    public boolean update(Order order) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Order element) throws SQLException {
        statement.setString(1, element.getOrderStatus().toString());
        statement.setInt(2, element.getClient().getPersonId());
        statement.setInt(3, element.getDriver().getPersonId());
        statement.setInt(4, element.getAddressDeparture().getIdAddress());
        statement.setInt(5, element.getAddressArrive().getIdAddress());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Order element) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Order parseToOneElement(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setIdOrder(resultSet.getInt("id_order"));
        order.setOrderStatus(OrderStatus.valueOf(resultSet.getString("order_status")));
        order.setClient(clientDao.findById(resultSet.getInt("id_client")));

        Driver driver = driverDao.findById(resultSet.getInt("id_driver"));
        order.setDriver(driver);

        order.setAddressDeparture(addressDao.findById(resultSet.getInt("id_adress_departure")));
        order.setAddressArrive(addressDao.findById(resultSet.getInt("id_adress_arrive")));

        Coupon coupon = couponDao.findById(resultSet.getInt("id_coupon"));

        order.setCoupon(coupon);


        order.setCost(resultSet.getInt("cost"));
        order.setCostWithDiscount(resultSet.getInt("cost_with_discount"));
        return order;
    }
}
