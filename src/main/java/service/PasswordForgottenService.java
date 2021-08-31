package service;

import java.sql.SQLException;

import model.PasswordForgotten;

public interface PasswordForgottenService {

    void save(PasswordForgotten forgotten) throws SQLException;

    PasswordForgotten get(String token);
}