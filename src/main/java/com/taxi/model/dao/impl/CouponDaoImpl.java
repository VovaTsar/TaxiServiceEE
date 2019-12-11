package com.taxi.model.dao.impl;

import com.taxi.model.dao.CouponDao;
import com.taxi.model.dao.connection.PoolConnection;
import com.taxi.model.entity.CouponEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class CouponDaoImpl extends AbstractGenericDao<CouponEntity> implements CouponDao {

    private static final String READ_BY_ID = "SELECT * FROM  taxi_database.coupon WHERE id_coupon  =(?);";

    private static final String READ_BY_COUPON = "SELECT * FROM  taxi_database.coupon WHERE  coupon_name  =(?);";

    private static final String READ_ALL = "SELECT * FROM  taxi_database.coupon ;";


    public CouponDaoImpl(PoolConnection connection) {
        super(connection);
    }


    @Override
    public CouponEntity findById(Integer id) {
        return getElementByIntegerParam(id, READ_BY_ID);
    }

    @Override
    public List<CouponEntity> findAll() {
        return getList(READ_ALL);
    }

    @Override
    public CouponEntity findByCouponName(String couponName) {
        return getElementByStringParam(couponName, READ_BY_COUPON);
    }

    @Override
    public void create(CouponEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(CouponEntity coupon) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, CouponEntity element) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, CouponEntity element) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected CouponEntity parseToOneElement(ResultSet resultSet) throws SQLException {
        CouponEntity coupon = new CouponEntity();

        coupon.setIdCoupon(resultSet.getInt("id_coupon"));
        coupon.setCouponName(resultSet.getString("coupon_name"));
        coupon.setDiscount(resultSet.getInt("discount"));
        return coupon;
    }
}
