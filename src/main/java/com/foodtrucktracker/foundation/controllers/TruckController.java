package com.foodtrucktracker.foundation.controllers;

import com.foodtrucktracker.foundation.models.Truck;
import com.foodtrucktracker.foundation.services.TruckService;
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
@RequestMapping("/trucks")
public class TruckController {

    @Autowired
    private TruckService truckService;

    @GetMapping(value = "/trucks", produces = "application/json")
    public ResponseEntity<?> listAllTrucks(){
        List<Truck> trucks = truckService.findAll();
        return new ResponseEntity<>(trucks, HttpStatus.OK);
    }

    @GetMapping(value = "/truck/{truckId}", produces = "application/json")
    public ResponseEntity<?> getTruckById(@PathVariable long truckId){
        Truck truck = truckService.findTruckById(truckId);
        return new ResponseEntity<>(truck, HttpStatus.OK);
    }

    @PostMapping(value = "/truck", consumes = "application/json")
    public ResponseEntity<?> addTruck(@Valid @RequestBody Truck newTruck){
        newTruck.setTruckId(0);
        newTruck = truckService.save(newTruck);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newTruckURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{truckId}")
                .buildAndExpand(newTruck.getTruckId())
                .toUri();
        responseHeaders.setLocation(newTruckURI);
        return new ResponseEntity<>(newTruck, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/truck/{truckId}", consumes = "application/json")
    public ResponseEntity<?> updateTruck(@Valid @RequestBody Truck updateTruck, @PathVariable long truckId){
        updateTruck.setTruckId(truckId);
        updateTruck = truckService.save(updateTruck);

        return new ResponseEntity<>(updateTruck, HttpStatus.OK);
    }

    @DeleteMapping(value = "/truck/{truckId}")
    public ResponseEntity<?> deleteUserById(@PathVariable long truckId){
        truckService.delete(truckId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
