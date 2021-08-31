package dao;

import java.sql.SQLException;

import model.PasswordForgotten;

public interface PasswordForgottenDao {

    void save(PasswordForgotten forgotten) throws SQLException;

    PasswordForgotten get(String token);
}