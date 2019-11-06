package my.project.model.entity;

public class CouponEntity {
    private final Integer id;
    private final double discountPercent;
    private final UserEntity userEntity;
    private final OrderEntity orderEntity;

    private CouponEntity(Builder builder) {
        this.id = builder.id;
        this.discountPercent = builder.discountPercent;
        this.userEntity = builder.userEntity;
        this.orderEntity = builder.orderEntity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }


    public static final class Builder {
        private Integer id;
        private double discountPercent;
        private UserEntity userEntity;
        private OrderEntity orderEntity;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withDiscountPercent(double discountPercent) {
            this.discountPercent = discountPercent;
            return this;
        }

        public Builder withUser(UserEntity userEntity) {
            this.userEntity = userEntity;
            return this;
        }

        public Builder withOrder(OrderEntity orderEntity) {
            this.orderEntity = orderEntity;
            return this;
        }

        public CouponEntity build() {
            return new CouponEntity(this);
        }
    }
}
