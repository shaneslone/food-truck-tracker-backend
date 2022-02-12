package com.foodtrucktracker.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Table(name = "menuitemreviews")
@IdClass(MenuItemReviewId.class)
public class MenuItemReview extends Auditable implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = {"username", "truckReviews", "ownedTrucks", "roles", "email", "currentLocation", "favoriteTrucks", "menuItemReviews"}, allowSetters = true)
    private User diner;

    @Id
    @ManyToOne
    @JoinColumn(name = "menuId")
    @JsonIgnoreProperties(value = {"itemName", "itemDescription", "itemPrice", "itemPhotos", "customerRatings", "customerRatingsAvg", "truck"}, allowSetters = true)
    private MenuItem menuItem;

    @Min(value = 0)
    @Max(value = 5)
    private double score;

    public MenuItemReview() {
    }

    public MenuItemReview(User diner, MenuItem menuItem, double score) {
        this.diner = diner;
        this.menuItem = menuItem;
        this.score = score;
    }

    public User getDiner() {
        return diner;
    }

    public void setDiner(User diner) {
        this.diner = diner;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
