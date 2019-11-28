package com.taxi.service.impl;

import com.taxi.model.exception.InputDataUnCorrectRuntimeException;
import com.taxi.model.dao.CarDao;
import com.taxi.model.entity.Car;
import com.taxi.service.CarService;
import org.apache.log4j.Logger;

import java.util.List;

public class CarServiceImpl implements CarService {
    private static final Logger LOG = Logger.getLogger(CarServiceImpl.class);
    private CarDao dao;

    public CarServiceImpl(CarDao dao) {
        this.dao = dao;
    }



    public Car getCarById(Integer id){
        if (id<0){
            LOG.error("created CarDaoImpl getCarById");
            throw new InputDataUnCorrectRuntimeException("Car Id must be positive");
        }
            LOG.debug("created CarDaoImplgetCarById");
            return dao.findById(id);
    }

    public List<Car> getAllCars(){
            LOG.debug("created CarDaoImpl getAllCars");
            return dao.findAll();

    }

    public boolean findCarByIdAndCarType(Integer idCar, String type){
        if (idCar<0||type.isEmpty()){
            LOG.error("created CarDaoImpl findCarByIdAndCarType");
            throw new InputDataUnCorrectRuntimeException("Car Id must be positive and type must be not null");
        }
            LOG.debug("created CarDaoImpl findCarByIdAndCarType");
            return dao.isSameCarType(idCar, type);

    }

}
