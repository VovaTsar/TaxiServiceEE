package my.project.model.service.impl;

import my.project.model.domain.Taxi;
import my.project.model.exception.EntityCreationRuntimeException;
import my.project.model.service.mapper.TaxiMapper;
import org.apache.log4j.Logger;
import my.project.model.entity.TaxiEntity;
import my.project.model.dao.TaxiDao;
import my.project.model.service.TaxiService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaxiServiceImpl implements TaxiService {
    private static final Logger LOGGER = Logger.getLogger(TaxiServiceImpl.class);

    private final TaxiDao taxiDao;
    private final TaxiMapper mapper;

    public TaxiServiceImpl(TaxiDao taxiDao, TaxiMapper mapper) {
        this.taxiDao = taxiDao;
        this.mapper = mapper;
    }

    @Override
    public boolean createTaxi(Taxi taxi) {
        if (Objects.isNull(taxi) ) {
            LOGGER.warn("TaxiEntity is not valid");
            throw new EntityCreationRuntimeException("TaxiEntity is not valid");
        }

        return taxiDao.save(mapper.mapTaxiToTaxiEntity(taxi));
    }

    @Override
    public List<Taxi> findAll(int currentPage, int recordsPerPage) {
        List<TaxiEntity> result = taxiDao.findAll(currentPage,recordsPerPage);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapTaxiEntityToTaxi)
                .collect(Collectors.toList());
    }

    @Override
    public int getNumberOfRows() {
        return taxiDao.getNumberOfRows();
    }
}
