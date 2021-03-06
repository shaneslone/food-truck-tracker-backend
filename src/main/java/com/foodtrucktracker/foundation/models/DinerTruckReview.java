package com.foodtrucktracker.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Table(name = "dinertruckreviews")
@IdClass(DinerTruckId.class)
public class DinerTruckReview extends Auditable implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = {"username", "truckReviews", "ownedTrucks", "roles", "email", "currentLocation", "favoriteTrucks", "menuItemReviews"}, allowSetters = true)
    private User diner;

    @Id
    @ManyToOne
    @JoinColumn(name = "truckId")
    @JsonIgnoreProperties(value = {"name", "reviews", "operator", "imageOfTruck", "cuisineType", "currentLocation", "departureTime", "dinerFavorites", "menu", "customerRatingsAvg"}, allowSetters = true)
    private Truck truck;

    @Min(value = 0)
    @Max(value = 5)
    private double score;

    public DinerTruckReview() {
    }

    public DinerTruckReview(User diner, Truck truck, double score) {
        this.diner = diner;
        this.truck = truck;
        this.score = score;
    }

    public User getDiner() {
        return diner;
    }

    public void setDiner(User diner) {
        this.diner = diner;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "DinerTruckReview{" +
                "score=" + score +
                '}';
    }
}
