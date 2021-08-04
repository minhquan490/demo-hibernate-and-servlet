package dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.ProductDao;
import model.Product;
import utils.HibernateUtil;
import utils.Log;

public class ProductDaoImpl implements ProductDao{

    @Override
    public void save(Product product) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            Log.getLog("ProductDaoImpl", e.getMessage(), e);
        }
    }

    @Override
    public void edit(Product product) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(product);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            Log.getLog("ProductDaoImpl", e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(String code) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "delete Product P where P.code = :code";
            Query query = session.createQuery(hql);
            query.setParameter("code", code).executeUpdate();
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
			Log.getLog("ProductDaoImpl", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Product get(long idProduct) {
        Transaction transaction = null;
        Product product = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Product P WHERE P.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", idProduct);
            product = (Product) query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            Log.getLog("ProductDaoImpl", e.getMessage(), e);
        }
        return product;
    }

    @Override
    public Product get(String name) {
        Transaction transaction = null;
        Product product = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Product P WHERE P.name = :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            product = (Product) query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            Log.getLog("ProductDaoImpl", e.getMessage(), e);
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        try(Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        } catch (Exception e) {
            Log.getLog("ProductDaoImpl", e.getMessage(), e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> searchByName(String name) {
        Transaction transaction = null;
        List<Product> products = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Product P WHERE P.name LIKE :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", "%" + name + "%");
            products = query.getResultList();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            Log.getLog("ProductDaoImpl", e.getMessage(), e);
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
            session.close();
            if (product != null) {
                return true;
            }
        } catch (Exception e) {
            Log.getLog("ProductDaoImpl", e.getMessage(), e);
        }
        return false;
    }

    @Override
    public Product getCode(String code) {
        Transaction transaction = null;
        Product product = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Product P WHERE P.code = :code";
            Query query = session.createQuery(hql);
            query.setParameter("code", code);
            product = (Product) query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            Log.getLog("ProductDaoImpl", e.getMessage(), e);
        }
        return product;
    }
}