package com.foodtrucktracker.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trucks")
public class Truck
    extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long truckId;

    @Column(nullable = false)
    private String name;

    private String imageOfTruck;

    @Column(nullable = false)
    private String cuisineType;

    @Column(nullable = false)
    private String currentLocation;

    @Column(nullable = false)
    private Date departureTime;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = {"roles", "currentLocation", "ownedTrucks", "favoriteTrucks", "truckReviews", "menuItemReviews"})
    private User operator;

    @OneToMany(mappedBy = "truck",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "truck", allowSetters = true)
    private Set<DinerTrucks> dinerFavorites = new HashSet<>();

    @OneToMany(mappedBy = "truck",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "truck",
    allowSetters = true)
    private Set<MenuItem> menu = new HashSet<>();

    @OneToMany(mappedBy = "truck", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "truck", allowSetters = true)
    private Set<DinerTruckReview> reviews = new HashSet<>();

    private double customerRatingsAvg;

    public Truck() {
    }

    public Truck(String name, String imageOfTruck, String cuisineType, String currentLocation, Date departureTime, User operator) {
        this.name = name;
        this.imageOfTruck = imageOfTruck;
        this.cuisineType = cuisineType;
        this.currentLocation = currentLocation;
        this.departureTime = departureTime;
        this.operator = operator;
    }

    public long getTruckId() {
        return truckId;
    }

    public void setTruckId(long truckId) {
        this.truckId = truckId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageOfTruck() {
        return imageOfTruck;
    }

    public void setImageOfTruck(String imageOfTruck) {
        this.imageOfTruck = imageOfTruck;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
    }

    public Set<DinerTrucks> getDinerFavorites() {
        return dinerFavorites;
    }

    public void setDinerFavorites(Set<DinerTrucks> dinerFavorites) {
        this.dinerFavorites = dinerFavorites;
    }

    public Set<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(Set<MenuItem> menu) {
        this.menu = menu;
    }

    public Set<DinerTruckReview> getReviews() {
        return reviews;
    }

    public void setReviews(Set<DinerTruckReview> reviews) {
        this.reviews = reviews;
    }

    public double getCustomerRatingsAvg() {
        return customerRatingsAvg;
    }

    public void setCustomerRatingsAvg(double customerRatingsAvg) {
        this.customerRatingsAvg = customerRatingsAvg;
    }
}
