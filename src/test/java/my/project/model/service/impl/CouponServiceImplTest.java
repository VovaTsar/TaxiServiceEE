package my.project.model.service.impl;

import my.project.model.dao.CouponDao;
import my.project.model.domain.Coupon;
import my.project.model.entity.CouponEntity;
import my.project.model.exception.InvalidEntityCreation;
import my.project.model.service.mapper.CouponMapper;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CouponServiceImplTest {
    private static final Coupon coupon = Coupon.builder().withId(1).build();
    private static final List<CouponEntity> entities = Arrays.asList(
            CouponEntity.builder().withId(1).build(),
            CouponEntity.builder().withId(2).build());
    private static final List<Coupon> coupons = Arrays.asList(coupon, coupon);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private CouponDao couponDao;

    @Mock
    private CouponMapper mapper;

    @InjectMocks
    private CouponServiceImpl service;

    @After
    public void resetMock() {
        reset(couponDao);
        reset(mapper);
    }

    @Test
    public void shouldCreateCoupon() {
        when(mapper.mapCouponToCouponEntity(any(Coupon.class))).thenReturn(entities.get(1));
        when(couponDao.save(any(CouponEntity.class))).thenReturn(true);

        assertTrue(service.createCoupon(coupon));
    }



    @Test
    public void shouldThrowInvalidEntityCreationWithNullCoupon() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("Coupon is not valid");

        service.createCoupon(null);
    }

    @Test
    public void shouldShowAllCoupons() {
        when(couponDao.findAll()).thenReturn(entities);
        when(mapper.mapCouponEntityToCoupon(any(CouponEntity.class))).thenReturn(coupon);

        List<Coupon> actual = service.findAllCoupons();

        assertEquals(coupons, actual);
    }

    @Test
    public void shouldReturnEmptyList() {
        when(couponDao.findAll()).thenReturn(Collections.emptyList());

        List<Coupon> actual = service.findAllCoupons();

        assertEquals(Collections.emptyList(), actual);
    }
}