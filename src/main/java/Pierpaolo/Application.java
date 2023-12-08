package Pierpaolo;

import Pierpaolo.entities.Customer;
import Pierpaolo.entities.Order;
import Pierpaolo.entities.Product;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        Faker faker = new Faker(Locale.ITALY);
        System.out.println(faker.name().fullName());

        Product iPhone = new Product("iPhone", "Smartphone", 1510.5);
        Product Samsung = new Product("Samsung", "Smartphone", 1499.99);
        Product felpa = new Product("Felpa", "Boys", 50.2);
        Product biberon = new Product("Biberon", "Baby", 21.5);
        Product culla = new Product("Culla", "Baby", 81.5);
        Product java = new Product("Il nuovo Java", "Book", 12.5);
        Product seneca = new Product("Seneca", "Book", 120.4);
        Product harari = new Product("Homo-Deus", "Book", 19.7);
        List<Product> magazzino = new ArrayList<>(Arrays.asList(iPhone, Samsung, felpa, biberon, culla, java, seneca, harari));

        Customer Aldo = new Customer("Aldo Baglio", 1);
        Customer Giacomo = new Customer("Giacomo Poretti", 2);
        Customer Giovanni = new Customer("Giovanni Storti", 3);
        Customer Filippo = new Customer("Filippo Inzaghi", 2);

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
        List<Order> orderList = new ArrayList<>(Arrays.asList(aldoOrder, giacomoOrder, giovanniOrder, filippoOrder2, filippoOrder));
//        List<Order> orderList = Arrays.asList(aldoOrder, giovanniOrder, giovanniOrder, filippoOrder2, filippoOrder); è UGUALE
        magazzino.forEach(product -> System.out.println("Prodotto: " + product.getName() + ", Categoria: " + product.getCategory() + ", Prezzo: " + product.getPrice()));
        orderList.forEach(order -> System.out.println("Ordine di " + order.getCustomer().getName() + ":"
                + order.getProducts().stream()
                .map(product -> "Prodotto: " + product.getName() + ", Category:" + product.getCategory() + ", Prezzo: " + product.getPrice())
                .toList()
        ));
        System.out.println();
        System.out.println("-----> 1                                                   ********************    Map k=CLIENTE v=Lista ORDINI    *******************");
        Map<Customer, List<Order>> orderByCustomer = orderList.stream().collect(Collectors.groupingBy(order -> order.getCustomer()));
        orderByCustomer.forEach((customer, orders) -> {
            System.out.println("Cliente: " + customer.getName() + "  ha fatto " + orders.size() + " ordini");
            System.out.println(orders);
        });
        System.out.println();
        System.out.println("-----> 2                                                   ********************    Map k=CLIENTE v=Importo totale dei suoi acquisti    *******************");
        Map<Object, Double> orderByCustomerTotal = orderList.stream().collect(Collectors.groupingBy(order -> order.getCustomer(),
                Collectors.summingDouble(order -> order.getProducts().stream().mapToDouble(Product -> Product.getPrice()).sum())));
        orderByCustomerTotal.forEach((customer, total) -> System.out.println(customer + " Totale: " + total));
        System.out.println();
        System.out.println("-----> 3                                                   ********************    Dato un elenco di prodotti trova i prodotti + costosi    *******************");
        List<Product> orderMoreExpensive = magazzino.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed()).limit(3).toList();
        orderMoreExpensive.forEach(System.out::println);
        System.out.println();
        System.out.println("-----> 4                                                   ********************    Dato un elenco di ordini calcola la media degli importi    *******************");
        double orderAverage = orderList.stream().mapToDouble(order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum()).average().getAsDouble();
        System.out.println("Media: " + orderAverage);
        System.out.println(orderList);
        System.out.println();
        System.out.println("-----> 5                                      ******   Dato un elenco di prodotti, raggruppa i prodotti x categoria e calcola la somma    *******************");
        Map<String, Double> categoriaEtotale = magazzino.stream().collect(Collectors.groupingBy(product -> product.getCategory(),
                Collectors.summingDouble(Product::getPrice)));
//        System.out.println(categoriaEsomma);
        categoriaEtotale.forEach((cat, somma) -> System.out.println("Categoria: " + cat + ", Totale: " + somma));
        System.out.println();
        System.out.println("------>6                      salvare su disco un file contenente la lista dei prodotti   **********************************");
        File file = new File("src/output.txt");
        try {
            FileUtils.writeStringToFile(file, magazzino + System.lineSeparator(), StandardCharsets.UTF_8, true);
            System.out.println("we wagliòòòòòòò");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Chiamata al metodo per salvare la lista di prodotti su disco
        try {
            salvaProdottiSuDisco(magazzino, "listaProdotti.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("------>7                      leggere su disco il file e riempi un Arraylist con il contenuto del file   **********************************");
        try {
            List<Product> prodottiLetti = leggiProdottiDaDisco("listaProdotti.txt");
            // Fai qualcosa con la lista di prodotti letti, ad esempio stampa a console
            for (Product prodotto : prodottiLetti) {
                System.out.println(prodotto.getName() + " - " + prodotto.getCategory() + " - " + prodotto.getPrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void salvaProdottiSuDisco(List<Product> prodotti, String nomeFile) throws IOException {
        // Creazione di una stringa che rappresenta la storicizzazione dei dati
        StringBuilder storicizzazione = new StringBuilder();

        for (Product prodotto : prodotti) {
            // Aggiunta delle informazioni del prodotto al formato storicizzato
            storicizzazione.append(prodotto.getName()).append("@")
                    .append(prodotto.getCategory()).append("@")
                    .append(prodotto.getPrice()).append("-----");
        }

        // Rimuovere l'ultimo carattere "-----" dal formato storicizzato
        if (storicizzazione.length() >= 5) {
            storicizzazione.delete(storicizzazione.length() - 5, storicizzazione.length());
        }

        // Utilizzo di FileUtils per scrivere la stringa su un file
        FileUtils.writeStringToFile(new File("src/" + nomeFile), storicizzazione.toString(), "UTF-8");
    }

    // Metodo per leggere il contenuto del file e riempire un ArrayList di Product
    public static List<Product> leggiProdottiDaDisco(String nomeFile) throws IOException {
        // Leggere il contenuto del file utilizzando FileUtils
        String storicizzazione = FileUtils.readFileToString(new File("src/" + nomeFile), "UTF-8");

        // Creare un ArrayList di Product per immagazzinare i prodotti letti
        List<Product> prodottiLetti = new ArrayList<>();

        // Dividere la stringa in prodotti utilizzando il separatore "-----"
        String[] prodottiArray = storicizzazione.split("-----");

        // Iterare attraverso gli elementi e creare oggetti Product
        for (String prodottoStringa : prodottiArray) {
            String[] attributi = prodottoStringa.split("@");
            if (attributi.length == 3) {
                String nome = attributi[0];
                String categoria = attributi[1];
                double prezzo = Double.parseDouble(attributi[2]);
                prodottiLetti.add(new Product(nome, categoria, prezzo));
            }
        }

        return prodottiLetti;
    }

}