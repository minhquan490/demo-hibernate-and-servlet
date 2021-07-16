package service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public void save(User user) throws SQLException {
        userDao.save(user);
    }

    @Override
    public void edit(User newUser) throws SQLException {
        User oldUser = userDao.get(newUser.getId());
        oldUser.setFullName(newUser.getFullName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setGender(newUser.getGender());
        oldUser.setBirthDate(newUser.getBirthDate());
        oldUser.setAddress(newUser.getAddress());
        oldUser.setPhone(newUser.getPhone());
        oldUser.setUsername(newUser.getUsername());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setRoleId(newUser.getRoleId());
        userDao.edit(oldUser);
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return userDao.delete(id);
    }

    @Override
    public User get(String username) {
        return userDao.get(username);
    }

    @Override
    public User get(long id) {
        return userDao.get(id);
    }

    @Override
    public User login(String username, String password) {
        User user = this.get(username);
        String sha256 = DigestUtils.sha256Hex(password);
        if (user != null && sha256.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public List<User> search(String username) {
        return userDao.search(username);
    }

    @Override
    public boolean register(String username, String email, String password, int roleId) throws SQLException {
        if (userDao.checkUsernameExist(username) || userDao.checkEmailExist(email)) {
            return false;
        } else {
            userDao.save(new User(email, username, password, roleId));
            return true;
        }
    }

    @Override
    public boolean checkEmailExist(String email) {
        return userDao.checkEmailExist(email);
    }

    @Override
    public boolean checkUsernameExist(String username) {
        return userDao.checkUsernameExist(username);
    }
}