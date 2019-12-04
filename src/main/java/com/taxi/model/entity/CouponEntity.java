package com.taxi.model.entity;

public class CouponEntity {
    private Integer idCoupon;
    private String couponName;
    private int discount;

    public Integer getIdCoupon() {
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
