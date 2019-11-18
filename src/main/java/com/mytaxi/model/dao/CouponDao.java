package com.mytaxi.model.dao;

import com.mytaxi.model.entity.Coupon;

public interface CouponDao extends CrudDao<Integer,Coupon> {
    Coupon readByCouponName(String couponName);
}
