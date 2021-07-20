package service;

import java.sql.SQLException;

import model.Cart;

public interface CartService {

    void add(Cart cart) throws SQLException;

    void edit(Cart cart) throws SQLException;

    boolean delete(long id) throws SQLException;

    Cart get(long id);
}