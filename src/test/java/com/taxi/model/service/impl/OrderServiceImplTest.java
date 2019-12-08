package com.taxi.model.service.impl;

import com.taxi.model.dao.OrderDao;
import com.taxi.model.domain.*;
import com.taxi.model.domain.enums.DriverStatus;
import com.taxi.model.domain.enums.OrderStatus;
import com.taxi.model.domain.enums.Role;
import com.taxi.model.entity.*;
import com.taxi.model.exception.InputDataUnCorrectRuntimeException;
import com.taxi.model.mapper.OrderMapper;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    private static final ClientEntity CLIENT_ENTITY = getClientEntity();

    private static final Client CLIENT = getClient();

    private static final Car CAR = getCar();

    private static final CarEntity CAR_ENTITY = getCarEntity();

    private static final DriverEntity DRIVER_ENTITY = getDriverEntity();

    private static final Driver DRIVER = getDriver();

    private static final AddressEntity ADDRESS_ENTITY = getAddressEntity();

    private static final Address ADDRESS = getAddress();

    private static final CouponEntity COUPON_ENTITY = getCouponEntity();

    private static final Coupon COUPON = getCoupon();

    private static final OrderEntity ORDER_ENTITY = getOrderEntity();

    private static final Order ORDER = getOrder();

    private static final List<OrderEntity> ORDER_ENTITIES = Arrays.asList(ORDER_ENTITY, ORDER_ENTITY);

    private static final List<Order> ORDERS = Arrays.asList(ORDER, ORDER);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private OrderDao dao;

    @Mock
    private OrderMapper mapper;

    @After
    public void resetMock() {
        reset(dao, mapper);
    }

    @InjectMocks
    private OrderServiceImpl service;


    @Test
    public void getAllOrderByIdDriverShouldReturnListOfOrders() {
        when(dao.findAllOrdersByDriverId(anyInt(), anyInt(), anyInt())).thenReturn(ORDER_ENTITIES);
        when(mapper.orderEntityToOrder(any(OrderEntity.class))).thenReturn(ORDER);

        List<Order> actual = service.getAllOrderByIdDriver(1, 1, 1);

        assertThat(actual, is(ORDERS));
    }

    @Test
    public void getAllOrderByIdDriverShouldThrowInputDataUnCorrectRuntimeExceptionWithNegativeParam() {
        exception.expect(InputDataUnCorrectRuntimeException.class);
        exception.expectMessage("IdDriver, row,limit must be positive");

        service.getAllOrderByIdDriver(-2, -3, -5);
    }

    @Test
    public void createOrderWithCouponInDBShouldCreateOrderWithCoupon() {
        when(mapper.orderToOrderEntity(any(Order.class))).thenReturn(ORDER_ENTITY);

        service.createOrderWithCouponInDB(ORDER);

        verify(dao).create(any(OrderEntity.class));
    }

    @Test
    public void createOrderWithCouponInDBShouldThrowInputDataUnCorrectRuntimeExceptionWithEmptyParam() {
        exception.expect(InputDataUnCorrectRuntimeException.class);
        exception.expectMessage("Order must be not null");

        service.createOrderWithCouponInDB(null);
    }

    @Test
    public void createOrderWithoutCouponInDBShouldCreateOrderWithoutCoupon() {
        when(mapper.orderToOrderEntity(any(Order.class))).thenReturn(ORDER_ENTITY);

        service.createOrderWithoutCouponInDB(ORDER);

        verify(dao).createWithoutCoupon(any(OrderEntity.class));
    }

    @Test
    public void createOrderWithoutCouponInDBShouldThrowInputDataUnCorrectRuntimeExceptionWithEmptyParam() {
        exception.expect(InputDataUnCorrectRuntimeException.class);
        exception.expectMessage("Order must be not null");

        service.createOrderWithoutCouponInDB(null);
    }

    @Test
    public void getAllOrdersCountShouldCreateOrderWithoutCoupon() {
        when(dao.findCountOrders(anyInt())).thenReturn(2L);
        long actual = service.getAllOrdersCount(2);

        assertThat(actual, is(2L));
    }

    @Test
    public void getAllOrdersCountShouldThrowInputDataUnCorrectRuntimeExceptionWithNegativeParam() {
        exception.expect(InputDataUnCorrectRuntimeException.class);
        exception.expectMessage("Id driver must be not null");

        service.getAllOrdersCount(-1);
    }

    @Test
    public void updateOrderStatusShouldUpdateOrderStatus() {
        when(dao.updateOrderStatus(anyInt(), any())).thenReturn(true);
        boolean actual = service.updateOrderStatus(1, OrderStatus.EXECUTING);

        assertThat(actual, is(true));
    }

    @Test
    public void updateOrderStatusShouldThrowInputDataUnCorrectRuntimeExceptionWithEmptyParam() {
        exception.expect(InputDataUnCorrectRuntimeException.class);
        exception.expectMessage("Id order and orderStatus must be not null");

        service.updateOrderStatus(-1, null);
    }

    @Test
    public void isCorrespondOrderAndDriverShouldCreateOrderWithCoupon() {
        when(dao.isCorrespondOrderAndDriver(anyInt(), anyInt())).thenReturn(true);

        boolean actual = service.isCorrespondOrderAndDriver(1, 1);

        assertThat(actual, is(true));
    }

    @Test
    public void isCorrespondOrderAndDriverShouldThrowInputDataUnCorrectRuntimeExceptionWithEmptyParam() {
        exception.expect(InputDataUnCorrectRuntimeException.class);
        exception.expectMessage("Id order and Id driver must be not null");

        service.isCorrespondOrderAndDriver(-1, -1);
    }

    private static ClientEntity getClientEntity() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setPersonId(1);
        clientEntity.setName("User");
        clientEntity.setSurname("User");
        clientEntity.setPassword("cerber");
        clientEntity.setPhoneNumber("+380968174473");
        clientEntity.setRole(Role.CLIENT);
        clientEntity.setEmail("user@gmail.com");

        return clientEntity;
    }

    private static Client getClient() {
        Client client = new Client();
        client.setPersonId(1);
        client.setName("User");
        client.setSurname("User");
        client.setPassword("cerber");
        client.setPhoneNumber("+380968174473");
        client.setRole(Role.CLIENT);
        client.setEmail("user@gmail.com");
        return client;
    }

    private static DriverEntity getDriverEntity() {
        DriverEntity driverEntity = new DriverEntity();

        driverEntity.setPersonId(1);
        driverEntity.setName("User");
        driverEntity.setSurname("User");
        driverEntity.setPassword("cerber");
        driverEntity.setPhoneNumber("+380968174473");
        driverEntity.setRole(Role.DRIVER);
        driverEntity.setDriverStatus(DriverStatus.FREE);
        driverEntity.setCar(CAR_ENTITY);
        driverEntity.setMiddleName("Ivanov");

        return driverEntity;
    }

    private static Driver getDriver() {
        Driver driver = new Driver();

        driver.setPersonId(1);
        driver.setName("User");
        driver.setSurname("User");
        driver.setPassword("cerber");
        driver.setPhoneNumber("+380968174473");
        driver.setRole(Role.DRIVER);
        driver.setDriverStatus(DriverStatus.FREE);
        driver.setCar(CAR);
        driver.setMiddleName("Ivanov");

        return driver;
    }

    private static Order getOrder() {
        Order order = new Order();
        order.setIdOrder(1);
        order.setOrderStatus(OrderStatus.EXECUTING);
        order.setClient(CLIENT);
        order.setDriver(DRIVER);
        order.setAddressArrive(ADDRESS);
        order.setAddressDeparture(ADDRESS);
        order.setCoupon(COUPON);
        order.setCost(100);
        order.setCostWithDiscount(80);

        return order;
    }

    private static OrderEntity getOrderEntity() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setIdOrder(1);
        orderEntity.setOrderStatus(OrderStatus.EXECUTING);
        orderEntity.setClient(CLIENT_ENTITY);
        orderEntity.setDriver(DRIVER_ENTITY);
        orderEntity.setAddressArrive(ADDRESS_ENTITY);
        orderEntity.setAddressDeparture(ADDRESS_ENTITY);
        orderEntity.setCoupon(COUPON_ENTITY);
        orderEntity.setCost(100);
        orderEntity.setCostWithDiscount(80);

        return orderEntity;
    }

    private static Car getCar() {
        return new Car(1, "AA777AA", "Audi", "White", "minivan");
    }

    private static CarEntity getCarEntity() {
        return new CarEntity(1, "AA777AA", "Audi", "White", "minivan");
    }

    private static CouponEntity getCouponEntity() {
        return new CouponEntity(1, "AAA", 20);
    }

    private static Coupon getCoupon() {
        return new Coupon(1, "AAA", 20);
    }

    private static AddressEntity getAddressEntity() {
        return new AddressEntity(1, "Polytech", "2B");
    }

    private static Address getAddress() {
        return new Address(1, "Polytech", "2B");
    }
}
