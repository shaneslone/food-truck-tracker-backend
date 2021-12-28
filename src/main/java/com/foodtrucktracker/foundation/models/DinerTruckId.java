package com.foodtrucktracker.foundation.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DinerTruckId implements Serializable {
    private long diner;
    private long truck;

    public DinerTruckId() {
    }

    public long getDiner() {
        return diner;
    }

    public void setDiner(long diner) {
        this.diner = diner;
    }

    public long getTruck() {
        return truck;
    }

    public void setTruck(long truck) {
        this.truck = truck;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        DinerTruckId that = (DinerTruckId) o;
        return diner == that.diner &&
                truck == that.truck;
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
