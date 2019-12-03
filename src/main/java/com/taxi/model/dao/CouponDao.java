package com.taxi.model.dao;

import com.taxi.model.domain.CouponEntity;

public interface CouponDao extends Dao<Integer, CouponEntity> {

    CouponEntity findByCouponName(String couponName);

}
