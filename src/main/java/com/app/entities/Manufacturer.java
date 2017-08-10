package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.List;

/**
 * Created by User on 09.08.2017.
 */
@Embeddable
public class Manufacturer {

    @Column(name = "manufacturer_name")
    private String name;
    @Column(name = "manufacturer_adress")
    private String adress;
    @Column(name = "manufacturer_phoneNumber")
    private String phoneNumber;
//    private List<String> phoneNumber;

    public Manufacturer() {
    }

    public Manufacturer(String name, String adress, String phoneNumber) {
        this.name = name;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
