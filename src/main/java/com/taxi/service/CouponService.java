package com.taxi.service;

import com.taxi.model.entity.Coupon;

public interface CouponService {
    Coupon getCouponByName(String couponName);
}
