package com.app.entities;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by User on 09.08.2017.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Embedded
    private Manufacturer manufacturer;

    private BigDecimal cost;

    @Future
    private Timestamp finalStorageDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public Timestamp getFinalStorageDate() {
        return finalStorageDate;
    }

    public void setFinalStorageDate(Timestamp finalStorageDate) {
        this.finalStorageDate = finalStorageDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer=" + manufacturer +
                ", cost=" + cost +
                ", finalStorageDate=" + finalStorageDate +
                '}';
    }
}
