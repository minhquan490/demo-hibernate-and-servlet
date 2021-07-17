package dao.impl;

import java.sql.SQLException;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.CartDao;
import model.Cart;
import utils.HibernateUtil;
import utils.Log;

public class CartDaoImpl implements CartDao {

    @Override
    public void add(Cart cart) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(cart);
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("CartDaoImpl", e.getMessage(), e);
        }
    }

    @Override
    public void edit(Cart cart) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(cart);
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("CartDaoImpl", e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(long id) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "delete Cart C where C.cart_id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
			Log.getLog("CartDaoImpl", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Cart get(long id) {
        Transaction transaction = null;
        Cart cart = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Cart C WHERE C.cart_id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            cart = (Cart) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
			Log.getLog("UserDaoImpl", e.getMessage(), e);
        }
        return cart;
    }
}