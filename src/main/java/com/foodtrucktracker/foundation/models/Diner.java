package com.foodtrucktracker.foundation.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "diners")
public class Diner extends User{
    public Diner(String username, String password, String email) {
        super(username, password, email);
    }
}
