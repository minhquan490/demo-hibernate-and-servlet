package service.impl;

import java.sql.SQLException;

import dao.PasswordForgottenDao;
import dao.impl.PasswordForgottenDaoImpl;
import model.PasswordForgotten;
import service.PasswordForgottenService;

public class PasswordForgottenServiceImpl implements PasswordForgottenService {

    PasswordForgottenDao forgottenDao = new PasswordForgottenDaoImpl();

    @Override
    public void save(PasswordForgotten forgotten) throws SQLException {
        forgottenDao.save(forgotten);
    }

    @Override
    public PasswordForgotten get(String token) {
        return forgottenDao.get(token);
    }
}