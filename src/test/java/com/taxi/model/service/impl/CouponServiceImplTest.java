package com.taxi.model.service.impl;

import com.taxi.model.dao.CouponDao;
import com.taxi.model.domain.Coupon;
import com.taxi.model.entity.CouponEntity;
import com.taxi.model.mapper.CouponMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CouponServiceImplTest {

    private static final CouponEntity COUPON_ENTITY = getCouponEntity();

    private static final Coupon COUPON = getCoupon();

    @Mock
    private CouponDao dao;

    @Mock
    private CouponMapper mapper;

    @After
    public void resetMock() {
        reset(dao, mapper);
    }

    @InjectMocks
    private CouponServiceImpl service;

    @Test
    public void getCouponByNameShouldReturnCoupon() {
        when(dao.findByCouponName(anyString())).thenReturn(COUPON_ENTITY);
        when(mapper.couponEntityToCoupon(any(CouponEntity.class))).thenReturn(COUPON);

        Coupon actual = service.getCouponByName("AAA");

        assertThat(actual, is(COUPON));
    }


    private static CouponEntity getCouponEntity() {
        return new CouponEntity(1, "AAA", 20);
    }

    private static Coupon getCoupon() {
        return new Coupon(1, "AAA", 20);
    }

}
