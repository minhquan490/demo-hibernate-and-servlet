package dao;

import java.sql.SQLException;

import model.Cart;
import model.User;

public interface CartDao {

    void add(Cart cart) throws SQLException;

    void edit(Cart cart) throws SQLException;

    boolean delete(long id) throws SQLException;

    Cart get(User user);
}