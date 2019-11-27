package com.taxi.model.dao.impl;

import com.taxi.model.dao.CouponDao;
import com.taxi.model.dao.connection.PoolConnection;
import com.taxi.model.entity.Coupon;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class CouponDaoImpl extends AbstractGenericDao<Coupon> implements CouponDao {
    private static final String READ_BY_ID = "SELECT * FROM  taxi_database.coupon WHERE id_coupon  =(?);";

    private static final String READ_BY_COUPON = "SELECT * FROM  taxi_database.coupon WHERE  coupon_name  =(?);";

    private static final String READ_ALL = "SELECT * FROM  taxi_database.coupon ;";

    private static final Logger LOG = Logger.getLogger(CouponDaoImpl.class);

    public CouponDaoImpl(PoolConnection connection) {
        super(connection);
    }


    @Override
    public Coupon findById(Integer id) {
        return getElementByIntegerParam(id, READ_BY_ID);
    }


    @Override
    public List<Coupon> findAll() {
        return getList(READ_ALL);
    }


    @Override
    public Coupon findByCouponName(String couponName) {
        return getElementByStringParam(couponName, READ_BY_COUPON);
    }


    @Override
    public void create(Coupon entity) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean update(Coupon coupon) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Coupon element) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Coupon element) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Coupon parseToOneElement(ResultSet resultSet) throws SQLException {
        Coupon coupon = new Coupon();

        coupon.setIdCoupon(resultSet.getInt("id_coupon"));
        coupon.setCouponName(resultSet.getString("coupon_name"));
        coupon.setDiscount(resultSet.getInt("discount"));
        return coupon;
    }
}
