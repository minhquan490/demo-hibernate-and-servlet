package dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.CartItemDao;
import model.Cart;
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
        } catch (Exception e) {
            Log.getLog("CartItemDaoImpl", e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "delete Cart_Item CI where CI.cart_item_id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id).executeUpdate();
            transaction.commit();
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
            String hql = "FROM Cart_Item CI WHERE CI.cart_item_id = :id";
            Query query = session.createQuery(hql);
            cartItem = (CartItem) query.setParameter("id", id).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("CartItemDaoImpl", e.getMessage(), e);
        }
        return cartItem;
    }

    @Override
    public List<CartItem> getListCartItemsOfCart(Cart cart) {
        List<CartItem> listCartItemsOfCart = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FORM Cart_Item CI WHERE CI.cart_id = :id";
            Query query = session.createQuery(hql);
            listCartItemsOfCart = query.setParameter("id", cart.getId()).getResultList();
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("CartItemDaoImpl", e.getMessage(), e);
        }
        return listCartItemsOfCart;
    }
    
}