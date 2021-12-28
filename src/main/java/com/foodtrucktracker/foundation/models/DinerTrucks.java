package com.foodtrucktracker.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dinertrucks")
@IdClass(DinerTruckId.class)
public class DinerTrucks extends Auditable implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "favoriteTrucks", allowSetters = true)
    private User diner;

    @Id
    @ManyToOne
    @JoinColumn(name = "truckId")
    @JsonIgnoreProperties(value = "dinerFavorites", allowSetters = true)
    private Truck truck;

    public DinerTrucks() {
    }

    public DinerTrucks(User diner, Truck truck) {
        this.diner = diner;
        this.truck = truck;
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

    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof DinerTrucks))
        {
            return false;
        }
        DinerTrucks that = (DinerTrucks) o;
        return ((diner == null) ? 0 : diner.getUserid()) == ((that.diner == null) ? 0 : that.diner.getUserid()) &&
                ((truck == null) ? 0 : truck.getTruckId()) == ((that.truck == null) ? 0 : that.truck.getTruckId());
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
