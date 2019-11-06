package my.project.model.domain;


public class Coupon {
    private final Integer id;
    private final double discountPercent;
    private final User user;
    private final Order order;

    private Coupon(Builder builder) {
        id = builder.id;
        discountPercent = builder.discountPercent;
        user = builder.user;
        order = builder.order;
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

    public User getUser() {
        return user;
    }

    public Order getOrder() {
        return order;
    }

    public static final class Builder {
        private Integer id;
        private double discountPercent;
        private User user;
        private Order order;

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

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withOrder(Order order) {
            this.order = order;
            return this;
        }

        public Coupon build() {
            return new Coupon(this);
        }
    }
}
