package com.taxi.model.myUtils;

import com.taxi.model.domain.Driver;
import com.taxi.model.exception.InvalidDataRuntimeException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class CookiesUtils {

    public static final String DRIVER_NAME = "driverName";
    public static final String DRIVER_PHONE = "phoneNumber";
    public static final String PRICE_VOYAGE = "priceVoyage";
    public static final String TIME_WAIT = "timeWait";
    public static final String ENCODING = "UTF-8";

    private CookiesUtils() {
    }

    public static String readCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (key.equals(c.getName())) {
                try {
                    return URLDecoder.decode(c.getValue(), ENCODING);
                } catch (UnsupportedEncodingException e) {
                    throw new InvalidDataRuntimeException();
                }
            }
        }
        return null;
    }

    public static void addCookies(HttpServletResponse response,
                                  Driver driver, int price, int timeWait) {
        String safeName = null;
        try {
            safeName = URLEncoder.encode(driver.getName(), ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new InvalidDataRuntimeException();
        }
        String phoneDriver = driver.getPhoneNumber();
        String priceStr = price + "";
        String timeWaitStr = timeWait + "";

        Cookie driverNameCookie = new Cookie(CookiesUtils.DRIVER_NAME, safeName);
        Cookie phoneNumberCookie = new Cookie(CookiesUtils.DRIVER_PHONE, phoneDriver);
        Cookie priceCookie = new Cookie(CookiesUtils.PRICE_VOYAGE, priceStr);
        Cookie timeWaitCookie = new Cookie(CookiesUtils.TIME_WAIT, timeWaitStr);

        driverNameCookie.setMaxAge(-1);
        phoneNumberCookie.setMaxAge(-1);
        priceCookie.setMaxAge(-1);
        timeWaitCookie.setMaxAge(-1);

        response.addCookie(driverNameCookie);
        response.addCookie(phoneNumberCookie);
        response.addCookie(priceCookie);
        response.addCookie(timeWaitCookie);
    }
}
