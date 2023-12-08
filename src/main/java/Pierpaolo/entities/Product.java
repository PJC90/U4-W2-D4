package Pierpaolo.entities;

import java.util.Random;

public class Product {
    private Long id;
    private String name;
    private String category;
    private Double price;

    public Product( String name, String category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
        Random rm = new Random();
        this.id = rm.nextLong(1,10000);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }
}
