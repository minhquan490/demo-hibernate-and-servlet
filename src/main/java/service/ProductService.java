package service;

import java.sql.SQLException;
import java.util.List;

import model.Product;

public interface ProductService {

    void save(Product product) throws SQLException;

    void edit(Product product) throws SQLException;

    boolean delete(String code) throws SQLException;

    Product get(long idProduct);

    Product get(String name);

    Product getCode(String code);

    List<Product> getAll();

    List<Product> searchByName(String name);

    List<Product> getSaleProduct(String result);

    boolean checkProductExist(String name);
}