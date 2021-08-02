package service;

import model.CartItem;

public interface CartItemService {

    void save(CartItem cartItem);

    void edit(CartItem cartItem);

    boolean delete(long id);

    CartItem get(long id);
}