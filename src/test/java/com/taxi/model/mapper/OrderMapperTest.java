package com.taxi.model.mapper;

import com.taxi.model.domain.*;
import com.taxi.model.domain.enums.DriverStatus;
import com.taxi.model.domain.enums.OrderStatus;
import com.taxi.model.domain.enums.Role;
import com.taxi.model.entity.*;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderMapperTest {

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


    @Mock
    private ClientMapper clientMapper;

    @Mock
    private DriverMapper driverMapper;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private CouponMapper couponMapper;

    @InjectMocks
    private OrderMapper orderMapper;

    @After
    public void resetMock() {
        reset(clientMapper, driverMapper, addressMapper, couponMapper);
    }

    @Test
    public void shouldMapOrderEntityToOrder() {
        when(clientMapper.clientEntityToClient(any(ClientEntity.class))).thenReturn(CLIENT);
        when(driverMapper.driverEntityToDriver(any(DriverEntity.class))).thenReturn(DRIVER);
        when(addressMapper.addressEntityToAddress(any(AddressEntity.class))).thenReturn(ADDRESS);
        when(couponMapper.couponEntityToCoupon(any(CouponEntity.class))).thenReturn(COUPON);

        Order actual = orderMapper.orderEntityToOrder(ORDER_ENTITY);

        assertThat(actual.getIdOrder(), is(ORDER.getIdOrder()));
        assertThat(actual.getOrderStatus(), is(ORDER.getOrderStatus()));
        assertThat(actual.getClient(), is(ORDER.getClient()));
        assertThat(actual.getDriver(), is(ORDER.getDriver()));
        assertThat(actual.getAddressArrive(), is(ORDER.getAddressArrive()));
        assertThat(actual.getAddressDeparture(), is(ORDER.getAddressDeparture()));
        assertThat(actual.getCoupon(), is(ORDER.getCoupon()));
        assertThat(actual.getCost(), is(ORDER.getCost()));
        assertThat(actual.getCostWithDiscount(), is(ORDER.getCostWithDiscount()));
    }

    @Test
    public void shouldMapOrderToOrderEntity() {
        when(clientMapper.clientToClientEntity(any(Client.class))).thenReturn(CLIENT_ENTITY);
        when(driverMapper.driverToDriverEntity(any(Driver.class))).thenReturn(DRIVER_ENTITY);
        when(addressMapper.addressToAddressEntity(any(Address.class))).thenReturn(ADDRESS_ENTITY);
        when(couponMapper.couponToCouponEntity(any(Coupon.class))).thenReturn(COUPON_ENTITY);

        OrderEntity actual = orderMapper.orderToOrderEntity(ORDER);

        assertThat(actual.getOrderStatus(), is(ORDER_ENTITY.getOrderStatus()));
        assertThat(actual.getClient(), is(ORDER_ENTITY.getClient()));
        assertThat(actual.getDriver(), is(ORDER_ENTITY.getDriver()));
        assertThat(actual.getAddressArrive(), is(ORDER_ENTITY.getAddressArrive()));
        assertThat(actual.getAddressDeparture(), is(ORDER_ENTITY.getAddressDeparture()));
        assertThat(actual.getCoupon(), is(ORDER_ENTITY.getCoupon()));
        assertThat(actual.getCost(), is(ORDER_ENTITY.getCost()));
        assertThat(actual.getCostWithDiscount(), is(ORDER_ENTITY.getCostWithDiscount()));
    }

    @Test
    public void mapOrderToOrderEntityShouldReturnNull() {
        DriverEntity actual = driverMapper.driverToDriverEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapOrderEntityToOrderShouldReturnNull() {
        Driver actual = driverMapper.driverEntityToDriver(null);

        assertThat(actual, is(nullValue()));
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
