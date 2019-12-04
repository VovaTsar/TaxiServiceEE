package com.taxi.model.mapper;

import com.taxi.model.domain.Coupon;
import com.taxi.model.entity.CouponEntity;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CouponMapperTest {
    private static final CouponEntity COUPON_ENTITY = getCouponEntity();

    private static final Coupon COUPON = getCoupon();

    private final CouponMapper couponMapper = new CouponMapper();


    @Test
    public void shouldMapCouponEntityToCoupon() {
        Coupon actual = couponMapper.couponEntityToCoupon(COUPON_ENTITY);

        assertThat(actual.getIdCoupon(), is(COUPON.getIdCoupon()));
        assertThat(actual.getCouponName(), is(COUPON.getCouponName()));
        assertThat(actual.getDiscount(), is(COUPON.getDiscount()));

    }

    @Test
    public void shouldMapCouponToCouponEntity() {
        CouponEntity actual = couponMapper.couponToCouponEntity(COUPON);

        assertThat(actual.getIdCoupon(), is(COUPON_ENTITY.getIdCoupon()));
        assertThat(actual.getCouponName(), is(COUPON_ENTITY.getCouponName()));
        assertThat(actual.getDiscount(), is(COUPON_ENTITY.getDiscount()));

    }

    @Test
    public void mapCarToCarEntityShouldReturnNull() {
        CouponEntity actual = couponMapper.couponToCouponEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapCarEntityToCarShouldReturnNull() {
        Coupon actual = couponMapper.couponEntityToCoupon(null);
        assertThat(actual, is(nullValue()));
    }

    private static CouponEntity getCouponEntity() {
        return new CouponEntity(1,"AAA",20);
    }

    private static Coupon getCoupon() {
        return new Coupon(1,"AAA",20);
    }

}