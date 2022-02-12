package com.foodtrucktracker.foundation.controllers;

import com.foodtrucktracker.foundation.models.MenuItem;
import com.foodtrucktracker.foundation.models.MenuItemReview;
import com.foodtrucktracker.foundation.models.Truck;
import com.foodtrucktracker.foundation.models.User;
import com.foodtrucktracker.foundation.services.MenuItemService;
import com.foodtrucktracker.foundation.services.TruckService;
import com.foodtrucktracker.foundation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/menuitems")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/menuitems", produces = "application/json")
    public ResponseEntity<?> listAllMenuItems(){
        List<MenuItem> menuItems = menuItemService.findAll();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @GetMapping(value = "/menuitem/{menuItemId}", produces = "application/json")
    public ResponseEntity<?> getMenuItemById(@PathVariable long menuItemId){
        MenuItem menuItem = menuItemService.findMenuItemById(menuItemId);
        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PostMapping(value = "/menuitem/truck/{truckId}", consumes = "application/json")
    public ResponseEntity<?> addMenuItem(@Valid @RequestBody MenuItem newMenuItem, @PathVariable long truckId){
        Truck truck = truckService.findTruckById(truckId);
        newMenuItem.setMenuId(0);
        newMenuItem.setTruck(truck);
        newMenuItem = menuItemService.save(newMenuItem);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newMenuItemURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{menuItemId}")
                .buildAndExpand(newMenuItem.getMenuId())
                .toUri();
        responseHeaders.setLocation(newMenuItemURI);
        return new ResponseEntity<>(newMenuItem, responseHeaders, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PutMapping(value = "/menuitem/{menuItemId}", consumes = "application/json")
    public ResponseEntity<?> updateMenuItem(@Valid @RequestBody MenuItem updateMenuItem, @PathVariable long menuItemId){
        updateMenuItem.setMenuId(menuItemId);
        updateMenuItem = menuItemService.save(updateMenuItem);

        return new ResponseEntity<>(updateMenuItem, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @DeleteMapping(value = "/menuitem/{menuItemId}")
    public ResponseEntity<?> deleteMenuItemById(@PathVariable long menuItemId){
        menuItemService.delete(menuItemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/menuitem/{menuItemId}/rating/{rating}")
    public ResponseEntity<?> addCustomerRating(@PathVariable long menuItemId, @PathVariable double rating, Authentication authentication){
        User user = userService.findByName(authentication.getName());
        MenuItem menuItem = menuItemService.findMenuItemById(menuItemId);
        menuItem.getCustomerRatings().add(new MenuItemReview(user, menuItem, rating));
        menuItem= menuItemService.save(menuItem);
        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }
}
