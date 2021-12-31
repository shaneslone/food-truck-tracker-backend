package com.foodtrucktracker.foundation.services;

import com.foodtrucktracker.foundation.exceptions.ResourceNotFoundException;
import com.foodtrucktracker.foundation.models.MenuItem;
import com.foodtrucktracker.foundation.models.MenuItemReview;
import com.foodtrucktracker.foundation.models.Truck;
import com.foodtrucktracker.foundation.models.User;
import com.foodtrucktracker.foundation.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "menuItemService")
public class MenuItemServiceImpl implements MenuItemService{

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private TruckService truckService;

    @Autowired
    private UserService userService;

    @Override
    public List<MenuItem> findAll() {
        List<MenuItem> list = new ArrayList<>();
        menuItemRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public MenuItem findMenuItemById(long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item id " + id + " not found!"));
    }

    @Override
    public void delete(long id) {
        findMenuItemById(id);
        menuItemRepository.deleteById(id);
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        MenuItem newMenuItem = new MenuItem();

        if(menuItem.getMenuId() != 0){
            findMenuItemById(menuItem.getMenuId());
            newMenuItem.setMenuId(menuItem.getMenuId());
        }

        newMenuItem.setItemName(menuItem.getItemName());
        newMenuItem.setItemPrice(menuItem.getItemPrice());
        newMenuItem.setItemDescription(menuItem.getItemDescription());

        Truck truck = truckService.findTruckById(menuItem.getTruck().getTruckId());
        newMenuItem.setTruck(truck);

        double totalScore = 0;

        for(MenuItemReview mir: menuItem.getCustomerRatings()){
            User diner = userService.findUserById(mir.getDiner().getUserid());
            totalScore += mir.getScore();
            newMenuItem.getCustomerRatings().add(new MenuItemReview(diner, newMenuItem, mir.getScore()));
        }

        newMenuItem.setCustomerRatingsAvg(totalScore > 0 ? totalScore / newMenuItem.getCustomerRatings().size() : 0);

        return menuItemRepository.save(newMenuItem);
    }

    @Override
    public void deleteAll() {
        menuItemRepository.deleteAll();
    }
}