package dao.impl;

import java.sql.SQLException;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.CartDao;
import model.Cart;
import model.User;
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
            String hql = "delete Cart C where C.id = :id";
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
    public Cart get(User user) {
        Transaction transaction = null;
        Cart cart = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Cart C WHERE C.user = :user";
            Query query = session.createQuery(hql);
            query.setParameter("user", user);
            cart = (Cart) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
			Log.getLog("UserDaoImpl", e.getMessage(), e);
        }
        return cart;
    }

    @Override
    public Cart get(long id) {
        Transaction transaction = null;
        Cart cart = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Cart C WHERE C.id = :id";
            Query query = session.createQuery(hql);
            cart = (Cart) query.setParameter("id", id).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("UserDaoImpl", e.getMessage(), e);
        }
        return cart;
    }
}