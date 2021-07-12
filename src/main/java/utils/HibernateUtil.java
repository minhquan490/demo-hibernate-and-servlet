package utils;

import org.hibernate.*;
import org.hibernate.cfg.*;

import model.Cart;
import model.CartItem;
import model.Product;
import model.Transaction;
import model.User;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().addPackage("model").addAnnotatedClass(User.class)
                        /*.addAnnotatedClass(Product.class).addAnnotatedClass(Cart.class)
                        .addAnnotatedClass(CartItem.class).addAnnotatedClass(Transaction.class)*/.configure()
                        .buildSessionFactory();
                return sessionFactory;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}