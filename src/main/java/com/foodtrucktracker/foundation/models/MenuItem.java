package com.foodtrucktracker.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menuItems")
public class MenuItem
    extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long menuId;

    @ManyToOne
    @JoinColumn(name = "truckId")
    @JsonIgnoreProperties(value = "menu", allowSetters = true)
    private Truck truck;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private String itemDescription;

    @Column(nullable = false)
    private int itemPrice;

    @OneToMany(mappedBy = "menuItem",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    @JsonIgnoreProperties(value = "itemPhotos", allowSetters = true)
    private List<MenuItemPhoto> itemPhotos;

    @OneToMany(mappedBy = "menuItem",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    @JsonIgnoreProperties(value = "customerRatings", allowSetters = true)
    private List<MenuItemReview> customerRatings;

    private int customerRatingsAvg;
}
