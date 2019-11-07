package my.project.model.service;

import my.project.model.domain.Address;

import java.util.List;

public interface AddressService {
    boolean createAddress(Address address);

    List<Address> findAll(int currentPage, int recordsPerPage);

    int getNumberOfRows();
}
