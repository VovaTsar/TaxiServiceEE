package com.taxi.service.impl;

import com.taxi.model.dao.CouponDao;
import com.taxi.model.entity.Coupon;
import com.taxi.service.CouponService;
import org.apache.log4j.Logger;

public class CouponServiceImpl implements CouponService {
    private static final Logger LOG = Logger.getLogger(CarServiceImpl.class);
    private CouponDao couponDao;

    public CouponServiceImpl(CouponDao couponDao) {
        this.couponDao = couponDao;
    }


    public Coupon getCouponByName(String couponName){
        if (couponName.isEmpty()) {
            return null;
        }
        LOG.debug("created CouponDaoImpl");
            return couponDao.findByCouponName(couponName);
    }
}
