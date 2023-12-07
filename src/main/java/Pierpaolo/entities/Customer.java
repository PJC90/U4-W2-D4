package Pierpaolo.entities;

import java.util.Random;

public class Customer {
    private Long id;
    private String name;
    private Integer tier;

    public Customer(String name, Integer tier) {
        this.name = name;
        this.tier = tier;
        Random rm = new Random();
        this.id = rm.nextLong(1,10000);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getTier() {
        return tier;
    }
}
