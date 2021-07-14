package service;

import java.sql.SQLException;
import java.util.List;

import model.Product;

public interface ProductService {
       
    void save(Product product) throws SQLException;

    void edit(Product product) throws SQLException;

    boolean delete(long idProduct) throws SQLException;

    Product get(long idProduct);

    Product get(String name);

    List<Product> getAll();

    List<Product> searchByName(String name);

    boolean checkProductExist(String name);
}