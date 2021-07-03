package service;

import java.sql.SQLException;
import java.util.List;

import model.User;

public interface UserService {
    
    void save(User user) throws SQLException;

    void edit(User newUser) throws SQLException;

    boolean delete(int id) throws SQLException;

    User get(String username);

    User get(int id);

    User login(String username, String password);

    List<User> getAll();

    List<User> search(String username);

    boolean register(String username, String email, String password, int roleId);

    boolean checkEmailExist(String email);

    boolean checkUsernameExist(String username);
}