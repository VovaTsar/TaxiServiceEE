package com.taxi.model.myUtils;


public class TimeWaitTaxiUtil {

    private TimeWaitTaxiUtil() {
    }

    public static int getTimeWait() {
        return (int) (Math.random() * 10);
    }
}
