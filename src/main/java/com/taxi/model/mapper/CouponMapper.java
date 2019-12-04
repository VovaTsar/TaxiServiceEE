package com.taxi.model.mapper;

import com.taxi.model.entity.CouponEntity;
import com.taxi.model.domain.Coupon;

public class CouponMapper {

    public Coupon couponEntityToCoupon(CouponEntity couponEntity) {
        if (couponEntity == null) {
            return null;
        }

        Coupon coupon = new Coupon();

        coupon.setIdCoupon(couponEntity.getIdCoupon());
        coupon.setCouponName(couponEntity.getCouponName());
        coupon.setDiscount(couponEntity.getDiscount());

        return coupon;
    }

    public CouponEntity couponToCouponEntity(Coupon coupon) {
        if (coupon == null) {
            return null;
        }

        CouponEntity couponEntity = new CouponEntity();

        couponEntity.setIdCoupon(coupon.getIdCoupon());
        couponEntity.setCouponName(coupon.getCouponName());
        couponEntity.setDiscount(coupon.getDiscount());

        return couponEntity;
    }
}
