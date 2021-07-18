package service;

import java.util.List;

import model.Cart;
import model.CartItem;

public interface CartItemService {
    
    void save(CartItem cartItem);

    void edit(CartItem cartItem);

    boolean delete(long id);

    CartItem get(long id);

    List<CartItem> getListCartItemsOfCart(Cart cart);
}