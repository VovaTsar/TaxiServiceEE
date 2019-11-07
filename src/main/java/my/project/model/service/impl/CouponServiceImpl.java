package my.project.model.service.impl;

import my.project.model.exception.InvalidEntityCreation;
import org.apache.log4j.Logger;
import my.project.model.domain.Coupon;
import my.project.model.entity.CouponEntity;
import my.project.model.dao.CouponDao;
import my.project.model.service.CouponService;
import my.project.model.service.mapper.CouponMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CouponServiceImpl implements CouponService {
    private static final Logger LOGGER = Logger.getLogger(CouponServiceImpl.class);

    private final CouponDao couponDao;
    private final CouponMapper mapper;

    public CouponServiceImpl(CouponDao couponDao, CouponMapper mapper) {
        this.couponDao = couponDao;
        this.mapper = mapper;
    }

    @Override
    public boolean createCoupon(Coupon coupon) {
        if (Objects.isNull(coupon) ) {
            LOGGER.warn("Coupon is not valid");
            throw new InvalidEntityCreation("Coupon is not valid");
        }

        return couponDao.save(mapper.mapCouponToCouponEntity(coupon));
    }

    @Override
    public List<Coupon> findAll(int currentPage, int recordsPerPage) {
        List<CouponEntity> result = couponDao.findAll(currentPage,recordsPerPage);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapCouponEntityToCoupon)
                .collect(Collectors.toList());
    }

    @Override
    public int getNumberOfRows() {
        return couponDao.getNumberOfRows();
    }
}
