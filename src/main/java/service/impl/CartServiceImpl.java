package service.impl;

import java.sql.SQLException;
import java.util.List;

import dao.CartDao;
import dao.impl.CartDaoImpl;
import model.Cart;
import model.CartItem;
import service.CartService;

public class CartServiceImpl implements CartService {

    CartDao cartDao = new CartDaoImpl();

    @Override
    public void add(Cart cart) throws SQLException {
        cartDao.add(cart);
    }

    @Override
    public void edit(Cart newCart) throws SQLException {
        Cart oldCart = cartDao.get(newCart.getId());
        oldCart.setUser(newCart.getUser());
        oldCart.setCartItems(newCart.getCartItems());
        oldCart.setBuyDate(newCart.getBuyDate());
        cartDao.edit(oldCart);
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return cartDao.delete(id);
    }

    @Override
    public Cart get(long id) {
        return cartDao.get(id);
    }

    @Override
    public List<CartItem> getListCartItems(Cart cart) {
        return cartDao.getListCartItems(cart);
    }
}