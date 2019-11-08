package my.project.model.service.mapper;


import my.project.model.domain.Address;
import my.project.model.entity.AddressEntity;

public class AddressMapper {
    public AddressEntity mapAddressToAddressEntity(Address domain) {
        return AddressEntity.builder()
                .withStreet(domain.getStreet())
                .withHouse(domain.getHouse())
                .withCoordinateX(domain.getX())
                .withCoordinateY(domain.getY())
                .build();
    }

    public Address mapAddressEntityToAddress(AddressEntity entity) {
        return Address.builder()
               .withId(entity.getId())
                .withStreet(entity.getStreet())
                .withHouse(entity.getHouse())
                .withX(entity.getCoordinateX())
                .withY(entity.getCoordinateY())
                .build();
    }
}
