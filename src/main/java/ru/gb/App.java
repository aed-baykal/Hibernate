package ru.gb;

import ru.gb.model.Product;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ProductDao pd = new ProductDao();
        List<Product> products;

        System.out.println("All Products");
        products = pd.findAll();
        for (Product product : products) {
            System.out.println(product);
        }

        System.out.println("Find By ID = 3");
        Product product = pd.findById(3L);
        System.out.println(product);

        System.out.println("Save Product");
        Product productTransient = new Product("Планшет", 20000);
        Product productPersistent = pd.saveOrUpdate(productTransient);
        System.out.println(productPersistent + " Get Saved Product From DB");

        System.out.println("Delete Product");
        pd.deleteById(productPersistent.getId());
        products = pd.findAll();
        for (Product iter : products) {
            System.out.println(iter);
        }

        pd.sessionFactory.close();
    }
}
