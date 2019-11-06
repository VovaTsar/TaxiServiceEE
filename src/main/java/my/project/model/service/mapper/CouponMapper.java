package my.project.model.service.mapper;

import my.project.model.domain.Coupon;
import my.project.model.domain.Order;
import my.project.model.domain.User;
import my.project.model.entity.CouponEntity;
import my.project.model.entity.OrderEntity;
import my.project.model.entity.UserEntity;

public class CouponMapper {
    public CouponEntity mapCouponToCouponEntity(Coupon domain) {
        return CouponEntity.builder()
                .withDiscountPercent(domain.getDiscountPercent())
                .withUser(UserEntity.builder()
                        .withId(domain.getId())
                        .build())
                .withOrder(OrderEntity.builder()
                        .withId(domain.getId())
                        .build())
                .build();
    }

    public Coupon mapCouponEntityToCoupon(CouponEntity entity) {
        return Coupon.builder()
                .withId(entity.getId())
                .withDiscountPercent(entity.getDiscountPercent())
                .withUser(User.builder()
                        .withId(entity.getUserEntity().getId())
                        .build())
                .withOrder(Order.builder()
                        .withId(entity.getOrderEntity().getId())
                        .build())
                .build();
    }
}
