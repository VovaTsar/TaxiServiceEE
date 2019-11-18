package com.robosh.model.dao;

import com.robosh.model.entity.Coupon;

public interface CouponDao extends CrudDao<Integer,Coupon> {
    Coupon readByCouponName(String couponName);
}
