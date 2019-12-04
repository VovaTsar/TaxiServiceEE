package com.taxi.model.dao;

import com.taxi.model.entity.CouponEntity;

public interface CouponDao extends Dao<Integer, CouponEntity> {

    CouponEntity findByCouponName(String couponName);

}
