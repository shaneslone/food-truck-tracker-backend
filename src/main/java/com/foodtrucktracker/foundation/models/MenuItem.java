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
    private double itemPrice;

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

    public MenuItem() {
    }

    public MenuItem(Truck truck, String itemName, String itemDescription, double itemPrice) {
        this.truck = truck;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public List<MenuItemPhoto> getItemPhotos() {
        return itemPhotos;
    }

    public void setItemPhotos(List<MenuItemPhoto> itemPhotos) {
        this.itemPhotos = itemPhotos;
    }

    public List<MenuItemReview> getCustomerRatings() {
        return customerRatings;
    }

    public void setCustomerRatings(List<MenuItemReview> customerRatings) {
        this.customerRatings = customerRatings;
    }

    public int getCustomerRatingsAvg() {
        return customerRatingsAvg;
    }

    public void setCustomerRatingsAvg(int customerRatingsAvg) {
        this.customerRatingsAvg = customerRatingsAvg;
    }
}
