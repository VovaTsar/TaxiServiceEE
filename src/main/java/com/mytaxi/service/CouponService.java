package com.mytaxi.service;

import com.mytaxi.model.dao.CouponDao;
import com.mytaxi.model.entity.Coupon;
import org.apache.log4j.Logger;

import java.util.List;

public class CouponService {
    private static final Logger LOG = Logger.getLogger(CarService.class);
    private CouponDao dao;

    public CouponService(CouponDao dao) {
        this.dao = dao;
    }

    public Coupon getCouponById(Integer id) {
            LOG.debug("created CouponDaoImpl");
            return dao.findById(id);

    }

    public List<Coupon> getAllCoupons() {
            LOG.debug("created CouponDaoImpl");
            return dao.findAll();
    }

    public Coupon getCouponByName(String couponName){
            LOG.debug("created CouponDaoImpl");
            return dao.readByCouponName(couponName);
    }
}
