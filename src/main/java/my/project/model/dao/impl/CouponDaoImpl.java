package my.project.model.dao.impl;

import my.project.model.dao.AbstractDao;
import my.project.model.dao.CouponDao;
import my.project.model.dao.connector.ConnectionPool;
import my.project.model.entity.CouponEntity;
import my.project.model.entity.OrderEntity;
import my.project.model.entity.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CouponDaoImpl extends AbstractDao<CouponEntity> implements CouponDao {
    private static final String INSERT_COUPON = "INSERT INTO tservice.coupons(coupon_discount, user_id, order_id) VALUES(?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM tservice.coupons WHERE coupon_id = ?";
    private static final String FIND_ALL_COUPONS = "SELECT * FROM tservice.coupons LIMIT ?, ?";
    private static final String COUNT = "SELECT * FROM tservice.coupons";
    private static final String UPDATE_COUPON = "UPDATE tservice.coupons SET coupon_discount = ?, user_id = ? , order_id = ?  WHERE coupon_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM tservice.coupons WHERE coupon_id = ?";

    public CouponDaoImpl(ConnectionPool connector) {
        super(connector);
    }

    @Override
    public boolean save(CouponEntity backlog) {
        return save(backlog, INSERT_COUPON);
    }

    @Override
    public Optional<CouponEntity> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<CouponEntity> findAll(int currentPage, int recordsPerPage) {
        return findAll(FIND_ALL_COUPONS, currentPage, recordsPerPage);
    }

    @Override
    public void update(CouponEntity backlog) {
        update(backlog, UPDATE_COUPON);
    }

    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    public int getNumberOfRows() {
        return getNumberOfRows(COUNT);
    }

    @Override
    protected void updateStatementMapper(CouponEntity coupon, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(coupon, preparedStatement);
        preparedStatement.setInt(4, coupon.getId());
    }

    @Override
    protected void createStatementMapper(CouponEntity coupon, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setDouble(1, coupon.getDiscountPercent());
        preparedStatement.setInt(2, coupon.getUserEntity().getId());
        preparedStatement.setInt(3, coupon.getOrderEntity().getId());
    }

    @Override
    protected Optional<CouponEntity> mapResultSetToEntity(ResultSet coupon) throws SQLException {
        UserEntity userEntity = UserEntity.builder()
                .withId(coupon.getInt(3))
                .build();
        OrderEntity orderEntity = OrderEntity.builder()
                .withId(coupon.getInt(4))
                .build();
        return Optional.of(CouponEntity.builder()
                .withId(coupon.getInt(1))
                .withDiscountPercent(coupon.getDouble(2))
                .withUser(userEntity)
                .withOrder(orderEntity)
                .build());
    }
}
