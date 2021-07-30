package service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import model.Product;
import service.ProductService;

public class ProductServiceImpl implements ProductService {

    ProductDao productDao = new ProductDaoImpl();

    @Override
    public void save(Product product) throws SQLException {
        productDao.save(product);
    }

    @Override
    public void edit(Product newProduct) throws SQLException {
        Product oldProduct = productDao.getCode(newProduct.getCode());
        oldProduct.setName(newProduct.getName());
        oldProduct.setCategory(newProduct.getCategory());
        oldProduct.setPrice(newProduct.getPrice());
        if (newProduct.getPicture() != null) {
            try {
                Files.deleteIfExists(Paths.get(oldProduct.getPicture()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            oldProduct.setPicture(newProduct.getPicture());
        }
        productDao.edit(oldProduct);
    }

    @Override
    public boolean delete(String code) throws SQLException {
        return productDao.delete(code);
    }

    @Override
    public Product get(long idProduct) {
        return productDao.get(idProduct);
    }

    @Override
    public Product get(String name) {
        return productDao.get(name);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public List<Product> searchByName(String name) {
        return productDao.searchByName(name);
    }

    @Override
    public boolean checkProductExist(String name) {
        return productDao.checkProductExist(name);
    }

    @Override
    public Product getCode(String code) {
        return productDao.getCode(code);
    }
}