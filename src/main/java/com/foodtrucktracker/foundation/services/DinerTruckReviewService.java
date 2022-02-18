package com.foodtrucktracker.foundation.services;

import com.foodtrucktracker.foundation.models.DinerTruckReview;
import com.foodtrucktracker.foundation.models.Truck;

public interface DinerTruckReviewService {
    Truck save(DinerTruckReview dinerTruckReview);
}
