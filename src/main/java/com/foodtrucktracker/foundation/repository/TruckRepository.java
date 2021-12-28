package com.foodtrucktracker.foundation.repository;

import com.foodtrucktracker.foundation.models.Truck;
import org.springframework.data.repository.CrudRepository;

public interface TruckRepository extends CrudRepository<Truck, Long> {
}
