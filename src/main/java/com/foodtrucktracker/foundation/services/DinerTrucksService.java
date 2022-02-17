package com.foodtrucktracker.foundation.services;

import com.foodtrucktracker.foundation.models.DinerTruckId;
import com.foodtrucktracker.foundation.models.DinerTrucks;
import com.foodtrucktracker.foundation.models.User;

public interface DinerTrucksService {
    DinerTrucks findDinerTrucksById(DinerTruckId id);
    User save(DinerTrucks dinerTrucks);
    User delete(DinerTruckId id);
}
