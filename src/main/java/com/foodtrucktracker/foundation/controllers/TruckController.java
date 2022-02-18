package com.foodtrucktracker.foundation.controllers;

import com.foodtrucktracker.foundation.models.*;
import com.foodtrucktracker.foundation.services.DinerTruckReviewService;
import com.foodtrucktracker.foundation.services.DinerTrucksService;
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
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/trucks")
public class TruckController {

    @Autowired
    private TruckService truckService;

    @Autowired
    private UserService userService;

    @Autowired
    private DinerTrucksService dinerTrucksService;

    @Autowired
    private DinerTruckReviewService dinerTruckReviewService;

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

    @PreAuthorize("hasAnyRole('ADMIN', 'DINER')")
    @PostMapping(value = "/truck/{truckid}/rating/{rating}")
    public ResponseEntity<?> addTruckReview(@PathVariable long truckid, @PathVariable double rating, Authentication authentication){
        User user = userService.findByName(authentication.getName());
        Truck truck = truckService.findTruckById(truckid);
        truck = dinerTruckReviewService.save(new DinerTruckReview(user, truck, rating));
        return new ResponseEntity<>(truck, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PutMapping(value = "/truck/{truckid}/location/{location}")
    public ResponseEntity<?> updateLocation(@PathVariable long truckid, @PathVariable String location){
        Truck truck = truckService.findTruckById(truckid);
        truck.setCurrentLocation(location);
        truck = truckService.save(truck);
        return new ResponseEntity<>(truck, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PutMapping(value = "/truck/{truckid}/departuretime/{departuretime}")
    public ResponseEntity<?> updateDepartureTime(@PathVariable long truckid, @PathVariable long departuretime){
        Truck truck = truckService.findTruckById(truckid);
        truck.setDepartureTime(new Date(departuretime));
        truck = truckService.save(truck);
        return new ResponseEntity<>(truck, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PostMapping(value = "/truck", consumes = "application/json")
    public ResponseEntity<?> addTruck(@Valid @RequestBody Truck newTruck, Authentication authentication){
        User currentUser = userService.findByName(authentication.getName());
        newTruck.setTruckId(0);
        newTruck.setOperator(currentUser);
        newTruck = truckService.save(newTruck);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newTruckURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{truckId}")
                .buildAndExpand(newTruck.getTruckId())
                .toUri();
        responseHeaders.setLocation(newTruckURI);
        return new ResponseEntity<>(newTruck, responseHeaders, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PutMapping(value = "/truck/{truckId}", consumes = "application/json")
    public ResponseEntity<?> updateTruck(@Valid @RequestBody Truck updateTruck, @PathVariable long truckId){
        updateTruck.setTruckId(truckId);
        updateTruck = truckService.save(updateTruck);

        return new ResponseEntity<>(updateTruck, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @DeleteMapping(value = "/truck/{truckId}")
    public ResponseEntity<?> deleteUserById(@PathVariable long truckId){
        truckService.delete(truckId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/trucks/cuisinetype/{cuisinetype}")
    public ResponseEntity<?>listTrucksByCuisineType(@PathVariable String cuisinetype){
        List<Truck> trucks = truckService.findByCuisineType(cuisinetype);
        return new ResponseEntity<>(trucks, HttpStatus.OK);
    }

    @GetMapping(value ="/trucks/rating/{score}")
    public ResponseEntity<?>listTrucksByRating(@PathVariable double score){
        List<Truck> trucks = truckService.findByCustomerRatingAvg(score);
        return new ResponseEntity<>(trucks, HttpStatus.OK);
    }
}
