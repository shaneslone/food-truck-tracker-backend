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
    private long id;

    @Column(nullable = false)
    private String name;

    private String imageOfTruck;

    @Column(nullable = false)
    private String cuisineType;

    @Column(nullable = false)
    private String currentLocation;

    @Column(nullable = false)
    private Date departureTime;

    @Column(nullable = false)
    private int operatorId;

    @OneToMany(mappedBy = "truck",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "truck",
    allowSetters = true)
    private Set<MenuItem> menu = new HashSet<>();

    @OneToMany(mappedBy = "truck", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "truck", allowSetters = true)
    private List<TruckReview> reviews;


    private int customerRatingsAvg;

    public Truck() {
    }
}
