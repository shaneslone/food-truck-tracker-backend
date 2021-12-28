package com.foodtrucktracker.foundation.services;

import com.foodtrucktracker.foundation.models.MenuItem;

import java.util.List;

public interface MenuItemService {
    List<MenuItem> findAll();
    MenuItem findMenuItemById(long id);
    void delete(long id);
    MenuItem save (MenuItem menuItem);
    void deleteAll();
}
