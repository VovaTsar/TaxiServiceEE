package my.project.model.service;

import my.project.model.domain.Taxi;

import java.util.List;

public interface TaxiService {
    boolean createTaxi(Taxi taxi);

    List<Taxi> findAllTaxis();
}
