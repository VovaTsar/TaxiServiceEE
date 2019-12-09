package com.taxi.controller.command.account.client;

import com.taxi.model.domain.*;
import com.taxi.model.domain.enums.DriverStatus;
import com.taxi.model.domain.enums.Role;
import com.taxi.model.entity.AddressEntity;
import com.taxi.model.entity.CarEntity;
import com.taxi.model.entity.CouponEntity;
import com.taxi.model.entity.DriverEntity;
import com.taxi.model.service.AddressService;
import com.taxi.model.service.CouponService;
import com.taxi.model.service.DriverService;
import com.taxi.model.service.OrderService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EnterOrderCommandTest {
    private static final Car CAR = getCar();

    private static final CarEntity CAR_ENTITY = getCarEntity();

    private static final DriverEntity DRIVER_ENTITY = getDriverEntity();

    private static final Driver DRIVER = getDriver();

    private static final AddressEntity ADDRESS_ENTITY = getAddressEntity();

    private static final Address ADDRESS = getAddress();

    private static final CouponEntity COUPON_ENTITY = getCouponEntity();

    private static final Coupon COUPON = getCoupon();


    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ClientOrderCommand clientOrder;

    @Mock
    private OrderService orderService;

    @Mock
    private DriverService driverService;

    @Mock
    private AddressService addressService;

    @Mock
    private CouponService couponService;

    @InjectMocks
    private EnterOrderCommand enterOrderCommand;

    @After
    public void resetMock() {
        reset(request, response, clientOrder,
                orderService, driverService, addressService, couponService);
    }

    @Test
    public void executeShouldReturnEnterOrderCommandPage() {
        when(request.getParameter(anyString())).thenReturn("Polytech");
        when(request.getParameter(anyString())).thenReturn("Khreschatyk");
        when(request.getParameter(anyString())).thenReturn("wagon");
        when(request.getParameter(anyString())).thenReturn("AAA");

        when(driverService.getDriverByCarTypeAndDriverStatus(any(),anyString())).thenReturn(DRIVER);
        when(addressService.getAddressByAddressString(anyString())).thenReturn(ADDRESS);
        when(couponService.getCouponByName(anyString())).thenReturn(COUPON);

       // verify(orderService).createOrderInDB(any(Order.class));

    }

    private static Car getCar() {
        return new Car(1, "AA777AA", "Audi", "White", "minivan");

    }

    private static CarEntity getCarEntity() {
        return new CarEntity(1, "AA777AA", "Audi", "White", "minivan");

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

    private static AddressEntity getAddressEntity() {
        return new AddressEntity(1,"Polytech","2B");
    }

    private static Address getAddress() {
        return new Address(1,"Polytech","2B");
    }

    private static CouponEntity getCouponEntity() {
        return new CouponEntity(1,"AAA",20);
    }

    private static Coupon getCoupon() {
        return new Coupon(1,"AAA",20);
    }


}