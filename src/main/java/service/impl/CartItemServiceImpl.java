package service.impl;

import java.util.List;

import dao.CartItemDao;
import dao.impl.CartItemDaoImpl;
import model.Cart;
import model.CartItem;
import service.CartItemService;

public class CartItemServiceImpl implements CartItemService {

    CartItemDao cartItemDao = new CartItemDaoImpl();

    @Override
    public void save(CartItem cartItem) {
        cartItemDao.save(cartItem);
    }

    @Override
    public void edit(CartItem newCartItem) {
        CartItem oldCartItem = cartItemDao.get(newCartItem.getId());
        oldCartItem.setCart(newCartItem.getCart());
        oldCartItem.setProduct(newCartItem.getProduct());
        oldCartItem.setUnitPrice(newCartItem.getQuantity());
        oldCartItem.setQuantity(newCartItem.getQuantity());
        oldCartItem.setTotal(newCartItem.getTotal());
        cartItemDao.edit(oldCartItem);
    }

    @Override
    public boolean delete(long id) {
        return cartItemDao.delete(id);
    }

    @Override
    public CartItem get(long id) {
        return cartItemDao.get(id);
    }

    @Override
    public List<CartItem> getListCartItemsOfCart(Cart cart) {
        return cartItemDao.getListCartItemsOfCart(cart);
    }
}