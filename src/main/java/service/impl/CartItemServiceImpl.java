package service.impl;

import dao.CartItemDao;
import dao.impl.CartItemDaoImpl;
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
}