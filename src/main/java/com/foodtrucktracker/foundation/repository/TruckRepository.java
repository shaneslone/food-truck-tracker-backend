package com.foodtrucktracker.foundation.repository;

import com.foodtrucktracker.foundation.models.Truck;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TruckRepository extends CrudRepository<Truck, Long> {
    List<Truck> findByCuisineTypeContainingIgnoreCase(String cuisineType);
    List<Truck> findByCustomerRatingsAvgGreaterThan(double score);
}
