package com.foodtrucktracker.foundation.controllers;

import com.foodtrucktracker.foundation.models.MenuItem;
import com.foodtrucktracker.foundation.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "/menuitem", consumes = "application/json")
    public ResponseEntity<?> addMenuItem(@Valid @RequestBody MenuItem newMenuItem){
        newMenuItem.setMenuId(0);
        newMenuItem = menuItemService.save(newMenuItem);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newMenuItemURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{menuItemId}")
                .buildAndExpand(newMenuItem.getMenuId())
                .toUri();
        responseHeaders.setLocation(newMenuItemURI);
        return new ResponseEntity<>(newMenuItem, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/menuitem/{menuItemId}", consumes = "application/json")
    public ResponseEntity<?> updateMenuItem(@Valid @RequestBody MenuItem updateMenuItem, @PathVariable long menuItemId){
        updateMenuItem.setMenuId(menuItemId);
        updateMenuItem = menuItemService.save(updateMenuItem);

        return new ResponseEntity<>(updateMenuItem, HttpStatus.OK);
    }

    @DeleteMapping(value = "/menuitem/{menuItemId}")
    public ResponseEntity<?> deleteMenuItemById(@PathVariable long menuItemId){
        menuItemService.delete(menuItemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
