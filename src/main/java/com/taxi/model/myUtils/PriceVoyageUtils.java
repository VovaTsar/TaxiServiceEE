package com.taxi.model.myUtils;

import com.taxi.model.entity.Address;
import com.taxi.model.entity.Coupon;

import java.util.Objects;


public class PriceVoyageUtils {

    private PriceVoyageUtils() {
    }

    public static int getPriceDependDistance(Address addressArrive, Address addressDeparture) {
        double arriveStreetPrice = getStreetPrice(addressArrive.getStreet());
        double departureStreetPrice = getStreetPrice(addressDeparture.getStreet());
        double arriveHouseNumberPrice = getHouseNumberPrice(addressArrive.getHouseNumber());
        double departureHouseNumberPrice = getHouseNumberPrice(addressDeparture.getHouseNumber());
        return (int) (50.0 + arriveStreetPrice + departureStreetPrice +
                arriveHouseNumberPrice + departureHouseNumberPrice);
    }

    public static int getPriceWithCoupon(int price, Coupon coupon) {
        return calculatePriceWithCoupon(price, coupon);

    }


    private static double getHouseNumberPrice(String houseNumber) {
        if (houseNumber.contains("/")) {
            houseNumber = houseNumber.replace("/", ".");
        }
        return new Double(houseNumber);
    }

    private static double getStreetPrice(String street) {
        return (double) street.charAt(0) / 10.0;
    }


    private static int calculatePriceWithCoupon(int price, Coupon coupon) {
        if (Objects.isNull(coupon)) {
            return price;
        } else {
            double discountPercents = coupon.getDiscount();
            int priceWithCoupon = (int) (price - price * (discountPercents / 100));
            return loyaltyProgram(priceWithCoupon);
        }
    }

    private static int loyaltyProgram(double price) {
        if (price > 200) {
            price = price - price * 0.2;
        }
        return (int) price;
    }
}
