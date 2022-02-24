package com.foodtrucktracker.foundation.services;

import com.foodtrucktracker.foundation.models.MenuItem;
import com.foodtrucktracker.foundation.models.MenuItemPhoto;
import com.foodtrucktracker.foundation.repository.MenuItemPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "menuItemPhotoService")
public class MenuItemPhotoServiceImpl implements MenuItemPhotoService{

    @Autowired
    private MenuItemPhotoRepository menuItemPhotoRepository;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private HelperFunctions helperFunctions;

    @Override
    public MenuItemPhoto save(long menuItemId, String url) {
        MenuItem menuItem = menuItemService.findMenuItemById(menuItemId);
        helperFunctions.isAuthorizedToMakeChange(menuItem.getTruck().getOperator().getUsername());
        return menuItemPhotoRepository.save(new MenuItemPhoto(menuItem, url));
    }
}
