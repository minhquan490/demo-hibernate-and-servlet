package service.impl;

import java.sql.SQLException;

import dao.CartDao;
import dao.impl.CartDaoImpl;
import model.Cart;
import service.CartService;

public class CartServiceImpl implements CartService {

    CartDao cartDao = new CartDaoImpl();

    @Override
    public void add(Cart cart) throws SQLException {
        cartDao.add(cart);
    }

    @Override
    public void edit(Cart newCart) throws SQLException {
        Cart oldCart = new Cart();
        oldCart.setId(newCart.getId());
        oldCart.setUser(newCart.getUser());
        oldCart.setCartItems(newCart.getCartItems());
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
}