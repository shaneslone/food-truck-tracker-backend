package com.foodtrucktracker.foundation.services;

import com.foodtrucktracker.foundation.exceptions.ResourceNotFoundException;
import com.foodtrucktracker.foundation.models.*;
import com.foodtrucktracker.foundation.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "truckService")
public class TruckServiceImpl  implements TruckService{
    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private UserService userService;

//    @Autowired
//    private MenuItemService menuItemService;

    @Override
    public List<Truck> findAll() {
        List<Truck> list = new ArrayList<>();
        truckRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Truck findTruckById(long id) {
        return truckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Truck id " + id + " not found!"));
    }

    @Override
    public void delete(long id) {
        findTruckById(id);
        truckRepository.deleteById(id);
    }

    @Override
    public Truck save(Truck truck) {
        Truck newTruck = new Truck();

        if(truck.getTruckId() != 0){
            findTruckById(truck.getTruckId());
            newTruck.setTruckId(truck.getTruckId());
        }

        newTruck.setName(truck.getName());
        newTruck.setImageOfTruck(truck.getImageOfTruck());
        newTruck.setCuisineType(truck.getCuisineType());
        newTruck.setCurrentLocation(truck.getCurrentLocation());

        User operator = userService.findUserById(truck.getOperator().getUserid());
        newTruck.setOperator(operator);

        newTruck.setDepartureTime(truck.getDepartureTime());

//        for(MenuItem mi : truck.getMenu()){
//            MenuItem menuItem = menuItemService.findMenuItemById(mi.getMenuId());
//            newTruck.getMenu().add(menuItem);
//        }

        for (DinerTrucks dt: truck.getDinerFavorites()){
            User diner = userService.findUserById(dt.getDiner().getUserid());
            newTruck.getDinerFavorites()
                    .add(new DinerTrucks(diner, newTruck));
        }

        for(DinerTruckReview dtr : truck.getReviews()){
            User user = userService.findUserById(dtr.getDiner().getUserid());
            newTruck.getReviews()
                    .add(new DinerTruckReview(user, newTruck, dtr.getScore()));
        }

        return truckRepository.save(newTruck);
    }

    @Override
    public void deleteAll() {
        truckRepository.deleteAll();
    }
}
