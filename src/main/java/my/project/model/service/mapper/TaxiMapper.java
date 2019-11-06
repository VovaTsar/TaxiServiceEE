package my.project.model.service.mapper;

import my.project.model.domain.Address;
import my.project.model.domain.Taxi;
import my.project.model.domain.User;
import my.project.model.entity.AddressEntity;
import my.project.model.entity.TaxiEntity;
import my.project.model.entity.UserEntity;

public class TaxiMapper {
    public TaxiEntity mapTaxiToTaxiEntity(Taxi domain) {
        return TaxiEntity.builder()
                .withLocation(AddressEntity.builder()
                        .withId(domain.getId())
                        .build())
                .withCarNumber(domain.getCarNumber())
                .withCarType(domain.getCarType())
                .withDriver(UserEntity.builder()
                        .withId(domain.getId())
                        .build())
                .withBusy(domain.isBusy())
                .build();
    }

    public Taxi mapTaxiEntityToTaxi(TaxiEntity entity) {
        return Taxi.builder()
                .withId(entity.getId())
                .withLocation(Address.builder()
                        .withId(entity.getId())
                        .build())
                .withCarNumber(entity.getCarNumber())
                .withCarType(entity.getCarType())
                .withDriver(User.builder()
                        .withId(entity.getId())
                        .build())
                .withBusy(entity.isBusy())
                .build();
    }
}
