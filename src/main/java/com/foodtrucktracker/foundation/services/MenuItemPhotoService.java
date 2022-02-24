package com.foodtrucktracker.foundation.services;

import com.foodtrucktracker.foundation.models.MenuItemPhoto;

public interface MenuItemPhotoService {
    MenuItemPhoto save(long menuItemId, String url);
}
