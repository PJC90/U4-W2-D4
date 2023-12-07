package Pierpaolo;

import Pierpaolo.entities.Customer;
import Pierpaolo.entities.Order;
import Pierpaolo.entities.Product;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Application {

    public static void main(String[] args) {

        Faker faker = new Faker(Locale.ITALY);
        System.out.println(faker.name().fullName());

        Product iPhone = new Product("iPhone","Smartphone", 1510.5);
        Product Samsung = new Product("Samsung","Smartphone", 1499.99);
        Product felpa = new Product("Felpa","Boys", 50.2);
        Product biberon = new Product("Biberon", "Baby", 21.5);
        Product culla = new Product("Culla", "Baby", 81.5);
        Product java = new Product("Il nuovo Java","Book",12.5);
        Product seneca = new Product("Seneca", "Book",120.4);
        Product harari = new Product("Homo-Deus", "Book",19.7);
        List<Product> magazzino = new ArrayList<>(Arrays.asList(iPhone,Samsung, felpa, biberon,culla,java,seneca,harari));

        Customer Aldo = new Customer("Aldo Baglio",1);
        Customer Giacomo = new Customer("Giacomo Poretti",2);
        Customer Giovanni = new Customer("Giovanni Storti",3);
        Customer Filippo = new Customer("Filippo Inzaghi",2);

        Order aldoOrder = new Order(Aldo);
        Order giacomoOrder = new Order(Giacomo);
        Order giovanniOrder = new Order(Giovanni);
        Order filippoOrder = new Order(Filippo);
        Order filippoOrder2 = new Order(Filippo);

        aldoOrder.addProduct(magazzino.get(0));
        aldoOrder.addProduct(magazzino.get(1));
        giacomoOrder.addProduct(magazzino.get(2));
        giacomoOrder.addProduct(magazzino.get(3));
        giovanniOrder.addProduct(magazzino.get(4));
        giovanniOrder.addProduct(magazzino.get(5));
        filippoOrder.addProduct(magazzino.get(6));
        filippoOrder2.addProduct(magazzino.get(7));
//
        List<Order> orderList = new ArrayList<>(Arrays.asList(aldoOrder, giovanniOrder, giovanniOrder, filippoOrder2, filippoOrder));
//        List<Order> orderList = Arrays.asList(aldoOrder, giovanniOrder, giovanniOrder, filippoOrder2, filippoOrder); è UGUALE
        magazzino.forEach(product -> System.out.println("Prodotto: "+ product.getName() + ", Categoria: " + product.getCategory()+ ", Prezzo: " + product.getPrice()));
        orderList.forEach(order -> System.out.println("Ordine di " + order.getCustomer().getName() + ":"
                + order.getProducts().stream()
                .map(product -> "Prodotto: " + product.getName() + ", Category:" + product.getCategory() + ", Prezzo: " + product.getPrice())
                .toList()
        ));
    }
}
