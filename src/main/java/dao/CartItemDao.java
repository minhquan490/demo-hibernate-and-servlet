package dao;

import model.CartItem;

public interface CartItemDao {
    
    void save(CartItem cartItem);

    void edit(CartItem cartItem);

    boolean delete(long id);

    CartItem get(long id);
}