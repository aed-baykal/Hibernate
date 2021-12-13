package ru.gb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.gb.model.Product;

import java.util.List;

public class ProductDao {

    SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    Product findById(Long id) {
        Product product;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            product = session.find(Product.class, id);
            session.getTransaction().commit();
        }
        return product;
    }

    List<Product> findAll(){
        List<Product> products;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            products = session.createQuery("select p from Product p", Product.class).getResultList();
            session.getTransaction().commit();
        }
        return products;
    }

    Product saveOrUpdate(Product product){
        Product productPersistent;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            String title = product.getTitle();
            session.saveOrUpdate(product);
            productPersistent =
                    session.createQuery("select p from Product p where p.title = :title", Product.class)
                            .setParameter("title", title).getSingleResult();
            session.getTransaction().commit();
        }
        return productPersistent;
    }

    void deleteById(Long id){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

}
