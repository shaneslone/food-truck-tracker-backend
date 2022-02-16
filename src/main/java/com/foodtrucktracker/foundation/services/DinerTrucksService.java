package com.foodtrucktracker.foundation.services;

import com.foodtrucktracker.foundation.models.DinerTruckId;
import com.foodtrucktracker.foundation.models.DinerTrucks;

public interface DinerTrucksService {
    DinerTrucks findDinerTrucksById(DinerTruckId id);
    DinerTrucks save(DinerTrucks dinerTrucks);
    void delete(DinerTruckId id);
}
