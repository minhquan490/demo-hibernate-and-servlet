package service;

import java.sql.SQLException;
import java.util.List;

import model.Cart;
import model.CartItem;

public interface CartService {

    void add(Cart cart) throws SQLException;

    void edit(Cart cart) throws SQLException;

    boolean delete(long id) throws SQLException;

    Cart get(long id);

    List<CartItem> getListCartItems(Cart cart);
}