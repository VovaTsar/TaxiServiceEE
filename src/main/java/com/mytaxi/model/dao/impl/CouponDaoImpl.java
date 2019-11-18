package com.mytaxi.model.dao.impl;

import com.mytaxi.model.customExceptions.DatabaseRuntimeException;
import com.mytaxi.model.dao.AbstractDao;
import com.mytaxi.model.dao.CouponDao;
import com.mytaxi.model.dao.connection.PoolConnection;
import com.mytaxi.model.entity.Coupon;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CouponDaoImpl extends AbstractDao<Coupon> implements CouponDao {
    private static final String SAVE_QUERY ="";
    private static final String UPDATE_QUERY = "";
    private static final String FIND_BY_ID = "SELECT * FROM  taxi_database.coupon WHERE id_coupon  =(?);";

    private static final String FIND_BY_COUPON = "SELECT * FROM  taxi_database.coupon WHERE  coupon_name  =(?);";

    private static final String FIND_ALL = "SELECT * FROM  taxi_database.coupon ;";

    private static final Logger LOG = Logger.getLogger(CouponDaoImpl.class);

    public CouponDaoImpl( PoolConnection connector) {
        super(SAVE_QUERY, FIND_BY_ID, FIND_ALL, UPDATE_QUERY, connector);
    }

    @Override
    public Coupon readByCouponName(String couponName) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_COUPON)) {
            statement.setString(1, couponName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(" getElementByStringParam error",e);
            throw new DatabaseRuntimeException("getElementByStringParam error", e);
        }
        return null;
    }

    @Override
    protected void updateStatementMapper(Coupon entity, PreparedStatement preparedStatement) throws SQLException {

    }

    @Override
    protected void createStatementMapper(Coupon entity, PreparedStatement preparedStatement) throws SQLException {

    }

    @Override
    protected Coupon mapResultSetToEntity(ResultSet entity) throws SQLException {
        return Coupon.builder()
                .withIdCoupon(entity.getInt("id_coupon"))
                .withCouponName(entity.getString("coupon_name"))
                .withDiscount(entity.getInt("discount"))
                .build();
    }
}
