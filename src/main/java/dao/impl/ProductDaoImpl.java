package dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.ProductDao;
import model.Product;
import utils.HibernateUtil;

public class ProductDaoImpl implements ProductDao{

    @Override
    public void save(Product product) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Product product) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(product);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(long id) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.remove(product);
                return true;
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product get(long idProduct) {
        Transaction transaction = null;
        Product product = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Product P WHERE P.product_id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", idProduct);
            product = (Product) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Product get(String name) {
        Transaction transaction = null;
        Product product = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Product P WHERE P.name_product = :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            product = (Product) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        try(Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        }
    }

    @Override
    public List<Product> searchByName(String name) {
        Transaction transaction = null;
        List<Product> products = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Product P WHERE P.name_product LIKE :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", "%" + name + "%");
            products = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean checkProductExist(String name) {
        Transaction transaction = null;
        Product product = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Product P WHERE P.name = :name";
            Query query = session.createQuery(hql);
            product = (Product) query.getSingleResult();
            transaction.commit();
            if (product != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}