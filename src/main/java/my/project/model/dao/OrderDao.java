package my.project.model.dao;

import my.project.model.entity.OrderEntity;
import my.project.model.entity.TaxiEntity;
import my.project.model.entity.CarType;

import java.util.Optional;

public interface OrderDao extends CrudDao<Integer, OrderEntity> {

    Optional<TaxiEntity> findByCarTypeAndBusy(CarType carType) ;
    void updateOrderStatus(OrderEntity story);


}
