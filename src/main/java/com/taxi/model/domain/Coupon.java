package com.taxi.model.domain;

public class Coupon {
    private Integer idCoupon;
    private String couponName;
    private int discount;

    public Coupon() {
    }

    public Coupon(Integer idCoupon, String couponName, int discount) {
        this.idCoupon = idCoupon;
        this.couponName = couponName;
        this.discount = discount;
    }

    public int getIdCoupon() {
        return idCoupon;
    }

    public void setIdCoupon(Integer idCoupon) {
        this.idCoupon = idCoupon;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }


}
