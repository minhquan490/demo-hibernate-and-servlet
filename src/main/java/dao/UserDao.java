package dao;

import java.sql.SQLException;
import java.util.List;

import model.User;

public interface UserDao {

    void save(User user) throws SQLException;

    void edit(User user) throws SQLException;

    boolean delete(String username) throws SQLException;

    User get(String emailOrUsername);

    User get(long id);

    List<User> getAll();

    List<User> search(String username);

    boolean checkEmailExist(String email);

    boolean checkUsernameExist(String username);
}