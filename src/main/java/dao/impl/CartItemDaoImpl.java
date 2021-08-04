package dao.impl;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.CartItemDao;
import model.CartItem;
import utils.HibernateUtil;
import utils.Log;

public class CartItemDaoImpl implements CartItemDao{

    @Override
    public void save(CartItem cartItem) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(cartItem);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            Log.getLog("CartItemDaoImpl", e.getMessage(), e);
        }
    }

    @Override
    public void edit(CartItem cartItem) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(cartItem);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            Log.getLog("CartItemDaoImpl", e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "delete CartItem CI where CI.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id).executeUpdate();
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            Log.getLog("CartItemDaoImpl", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public CartItem get(long id) {
        Transaction transaction = null;
        CartItem cartItem = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM CartItem CI WHERE CI.id = :id";
            Query query = session.createQuery(hql);
            cartItem = (CartItem) query.setParameter("id", id).getSingleResult();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            Log.getLog("CartItemDaoImpl", e.getMessage(), e);
        }
        return cartItem;
    }
}