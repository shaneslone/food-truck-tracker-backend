package com.foodtrucktracker.foundation.repository;

import com.foodtrucktracker.foundation.models.MenuItemReview;
import com.foodtrucktracker.foundation.models.MenuItemReviewId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemReviewRepository extends JpaRepository<MenuItemReview, MenuItemReviewId> {
}
