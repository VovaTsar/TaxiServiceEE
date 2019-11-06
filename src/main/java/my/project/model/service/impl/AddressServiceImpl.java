package my.project.model.service.impl;

import my.project.model.exception.InvalidEntityCreation;
import org.apache.log4j.Logger;
import my.project.model.dao.AddressDao;
import my.project.model.domain.Address;
import my.project.model.entity.AddressEntity;
import my.project.model.service.AddressService;
import my.project.model.service.mapper.AddressMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AddressServiceImpl implements AddressService {
    private static final Logger LOGGER = Logger.getLogger(AddressServiceImpl.class);

    private final AddressDao addressDao;
    private final AddressMapper mapper;

    public AddressServiceImpl(AddressDao addressDao, AddressMapper mapper) {
        this.addressDao = addressDao;
        this.mapper = mapper;
    }

    @Override
    public boolean createAddress(Address address) {
        if (Objects.isNull(address) ) {
            LOGGER.warn("AddressEntity is not valid");
            throw new InvalidEntityCreation("AddressEntity is not valid");
        }

        return addressDao.save(mapper.mapAddressToAddressEntity(address));
    }

    @Override
    public List<Address> findAllAddresses() {
        List<AddressEntity> result = addressDao.findAll();

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapAddressEntityToAddress)
                .collect(Collectors.toList());
    }
}
