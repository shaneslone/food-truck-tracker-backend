package com.foodtrucktracker.foundation.repository;

import com.foodtrucktracker.foundation.models.DinerTruckId;
import com.foodtrucktracker.foundation.models.DinerTruckReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DinerTruckReviewRepository extends JpaRepository<DinerTruckReview, DinerTruckId> {
}
