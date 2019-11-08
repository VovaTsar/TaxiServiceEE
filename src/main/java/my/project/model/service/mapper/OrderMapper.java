package my.project.model.service.mapper;

import my.project.model.domain.Address;
import my.project.model.domain.Order;
import my.project.model.domain.Taxi;
import my.project.model.domain.User;
import my.project.model.entity.AddressEntity;
import my.project.model.entity.OrderEntity;
import my.project.model.entity.TaxiEntity;
import my.project.model.entity.UserEntity;


public class OrderMapper {
    public OrderEntity mapOrderToOrderEntity(Order domain) {
        return OrderEntity.builder()
                .withCost(domain.getCost())
                .withStartPoint(AddressEntity.builder()
                        .withId(domain.getId())
                        .build())
                .withEndPoint(AddressEntity.builder()
                        .withId(domain.getId())
                        .build())
                .withStatus(domain.getStatus())
                .withUser(UserEntity.builder()
                        .withId(domain.getId())
                        .build())
                .withTaxi(TaxiEntity.builder()
                        .withId(domain.getId())
                        .build())
                .build();
    }


    public Order mapOrderEntityToOrder(OrderEntity entity) {
        return Order.builder()
                .withId(entity.getId())
                .withCost(entity.getCost())
                .withStartPoint(Address.builder()
                        .withId(entity.getId())
                        .build())
                .withEndPoint(Address.builder()
                        .withId(entity.getId())
                        .build())
                .withStatus(entity.getStatus())
                .withUser(User.builder()
                        .withId(entity.getId())
                        .build())
                .withTaxi(Taxi.builder()
                        .withId(entity.getId())
                        .build())
                .build();
    }
}
