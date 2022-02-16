package com.foodtrucktracker.foundation.services;

import com.foodtrucktracker.foundation.exceptions.ResourceNotFoundException;
import com.foodtrucktracker.foundation.models.DinerTruckId;
import com.foodtrucktracker.foundation.models.DinerTrucks;
import com.foodtrucktracker.foundation.models.User;
import com.foodtrucktracker.foundation.repository.DinerTrucksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "dinerTrucksService")
public class DinerTrucksServiceImpl implements DinerTrucksService{

    @Autowired
    private DinerTrucksRepository dinerTrucksRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private HelperFunctions helperFunctions;

    @Override
    public DinerTrucks findDinerTrucksById(DinerTruckId id) {
        return dinerTrucksRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Diner favorite not found!"));
    }

    @Override
    public DinerTrucks save(DinerTrucks dinerTrucks) {
        User user = userService.findUserById(dinerTrucks.getDiner().getUserid());
        helperFunctions.isAuthorizedToMakeChange(user.getUsername());
        truckService.findTruckById(dinerTrucks.getTruck().getTruckId());
        return dinerTrucksRepository.save(dinerTrucks);
    }

    @Override
    public void delete(DinerTruckId id) {
        DinerTrucks dinerTrucks = findDinerTrucksById(id);
        helperFunctions.isAuthorizedToMakeChange(dinerTrucks.getDiner().getUsername());
        dinerTrucksRepository.deleteById(id);
    }
}
