package my.project.model.service;

import my.project.model.domain.Coupon;

import java.util.List;

public interface CouponService {
    boolean createCoupon(Coupon coupon);

    List<Coupon> findAllCoupons();
}
