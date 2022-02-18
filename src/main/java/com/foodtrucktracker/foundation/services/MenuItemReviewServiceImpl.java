package com.foodtrucktracker.foundation.services;

import com.foodtrucktracker.foundation.models.MenuItem;
import com.foodtrucktracker.foundation.models.MenuItemReview;
import com.foodtrucktracker.foundation.repository.MenuItemReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "menuItemReviewService")
public class MenuItemReviewServiceImpl implements MenuItemReviewService{

    @Autowired
    private MenuItemReviewRepository menuItemReviewRepository;

    @Autowired
    private MenuItemService menuItemService;

    @Override
    public MenuItem save(MenuItemReview menuItemReview) {
        menuItemReview = menuItemReviewRepository.saveAndFlush(menuItemReview);
        return menuItemService.updateReviewAvg(menuItemReview.getMenuItem().getMenuId());
    }
}
