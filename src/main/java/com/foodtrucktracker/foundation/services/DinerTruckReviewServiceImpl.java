package com.foodtrucktracker.foundation.services;

import com.foodtrucktracker.foundation.models.DinerTruckReview;
import com.foodtrucktracker.foundation.models.Truck;
import com.foodtrucktracker.foundation.repository.DinerTruckReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "dinerTruckReviewService")
public class DinerTruckReviewServiceImpl implements DinerTruckReviewService{

    @Autowired
    private TruckService truckService;

    @Autowired
    private DinerTruckReviewRepository dinerTruckReviewRepository;

    @Override
    public Truck save(DinerTruckReview dinerTruckReview) {
        dinerTruckReview = dinerTruckReviewRepository.saveAndFlush(dinerTruckReview);
        return truckService.updateRatingAvg(dinerTruckReview.getTruck().getTruckId());
    }
}
