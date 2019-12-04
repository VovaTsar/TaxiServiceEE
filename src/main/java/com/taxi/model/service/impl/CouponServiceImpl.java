package com.taxi.model.service.impl;

import com.taxi.model.dao.CouponDao;
import com.taxi.model.domain.Coupon;
import com.taxi.model.mapper.CouponMapper;
import com.taxi.model.service.CouponService;

public class CouponServiceImpl implements CouponService {

    private CouponDao couponDao;
    private CouponMapper couponMapper;

    public CouponServiceImpl(CouponDao couponDao, CouponMapper couponMapper) {
        this.couponDao = couponDao;
        this.couponMapper = couponMapper;
    }

    public Coupon getCouponByName(String couponName) {
        if (couponName.isEmpty()) {
            return null;
        }

        return couponMapper.couponEntityToCoupon(couponDao.findByCouponName(couponName));

    }
}
