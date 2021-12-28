package com.foodtrucktracker.foundation.repository;

import com.foodtrucktracker.foundation.models.MenuItem;
import org.springframework.data.repository.CrudRepository;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
}
