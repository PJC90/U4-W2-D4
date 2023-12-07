package Pierpaolo.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order {
    private Long id;
    private String status;
    private LocalDate overDate;
    private LocalDate deliveryDate;
    private List<Product> products;
    private Customer customer;

    /*   public Order(String status, LocalDate overDate, LocalDate deliveryDate, List<Product> products, Customer customer) {
           this.status = status;       //*****Prova1*****
           this.overDate = overDate;
           this.deliveryDate = deliveryDate;
           this.products = products;
           this.customer = customer;
           Random rm = new Random();
           this.id =  rm.nextLong(1,100000);
       }*/
    public Order(Customer customer) {
        this.status = "just created";
        this.overDate = LocalDate.now();
        this.deliveryDate = LocalDate.now().plusWeeks(1);
        this.products = new ArrayList<>();
        this.customer = customer;
        Random rm = new Random();
        this.id =  rm.nextLong(1,100000);
    }
    public void addProduct(Product p){      //METODO che aggiunge un prodotto alla lista
        products.add(p);
    }
    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getOverDate() {
        return overDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }
}
