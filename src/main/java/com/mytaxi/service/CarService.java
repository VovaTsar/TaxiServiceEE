package com.mytaxi.service;

import com.mytaxi.model.dao.CarDao;
import com.mytaxi.model.entity.Car;
import org.apache.log4j.Logger;

import java.util.List;

public class CarService {
    private static final Logger LOG = Logger.getLogger(CarService.class);
    private CarDao dao;

    public CarService(CarDao dao) {
        this.dao = dao;
    }



    public Car getCarById(Integer id){
            LOG.debug("created CarDaoImpl");
            return dao.findById(id);
    }

    public List<Car> getAllCars(){
            LOG.debug("created CarDaoImpl");
            return dao.findAll();

    }

    public boolean findCarByIdAndCarType(Integer id_car, String type){
            LOG.debug("created CarDaoImpl");
            return dao.isSameCarType(id_car, type);

    }

}
