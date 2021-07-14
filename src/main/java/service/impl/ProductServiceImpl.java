package service.impl;

import java.io.File;
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
        Product oldProduct = productDao.get(newProduct.getId());
        oldProduct.setName(newProduct.getName());
        oldProduct.setCategory(newProduct.getCategory());
        oldProduct.setPrice(newProduct.getPrice());
        if (newProduct.getPicture() != null) {
            String fileName = oldProduct.getPicture();
            final String dir = "/home/quan/DataForProject/demo-hibernate-and-servlet";
            File file = new File(dir + "/" + fileName);
            if (file.exists()) {
                file.delete();
            }
            oldProduct.setPicture(newProduct.getPicture());
        }
        productDao.edit(oldProduct);
    }

    @Override
    public boolean delete(long idProduct) throws SQLException {
        return productDao.delete(idProduct);
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
        return checkProductExist(name);
    }
}