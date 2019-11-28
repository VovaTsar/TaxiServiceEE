package com.taxi.model.dao;

import com.taxi.model.entity.Coupon;

public interface CouponDao extends Dao<Integer, Coupon> {

    Coupon findByCouponName(String couponName);

}
