package com.foodtrucktracker.foundation.repository;

import com.foodtrucktracker.foundation.models.DinerTruckId;
import com.foodtrucktracker.foundation.models.DinerTrucks;
import org.springframework.data.repository.CrudRepository;

public interface DinerTrucksRepository extends CrudRepository<DinerTrucks, DinerTruckId> {
}
