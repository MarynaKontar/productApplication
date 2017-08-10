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

    public Manufacturer() {
    }

    public Manufacturer(String farm, String s, String s1) {
    }
}
