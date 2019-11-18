package com.mytaxi.model.entity;

public class Coupon {
    private  final Integer idCoupon;
    private final String couponName;
    private final int discount;

    private Coupon(Builder builder) {
       this. idCoupon = builder.idCoupon;
        this.  couponName = builder.couponName;
        this.  discount = builder.discount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getIdCoupon() {
        return idCoupon;
    }

    public String getCouponName() {
        return couponName;
    }

    public int getDiscount() {
        return discount;
    }

    public static final class Builder {
        private Integer idCoupon;
        private String couponName;
        private int discount;

        private Builder() {
        }

        public Builder withIdCoupon(Integer idCoupon) {
            this.idCoupon = idCoupon;
            return this;
        }

        public Builder withCouponName(String couponName) {
            this.couponName = couponName;
            return this;
        }

        public Builder withDiscount(int discount) {
            this.discount = discount;
            return this;
        }

        public Coupon build() {
            return new Coupon(this);
        }
    }
}
