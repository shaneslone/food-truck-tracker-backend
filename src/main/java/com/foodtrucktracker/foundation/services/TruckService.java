package com.foodtrucktracker.foundation.services;

import com.foodtrucktracker.foundation.models.Truck;

import java.util.List;

public interface TruckService {
    List<Truck> findAll();
    Truck findTruckById(long id);
    void delete (long id);
    Truck save(Truck truck);
    void deleteAll();
    List<Truck> findByCuisineType(String cuisineType);
}
