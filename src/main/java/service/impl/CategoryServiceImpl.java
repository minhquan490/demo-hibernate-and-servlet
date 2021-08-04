package service.impl;

import java.sql.SQLException;
import java.util.List;

import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import model.Category;
import service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public void save(Category category) throws SQLException {
        categoryDao.save(category);
    }

    @Override
    public void edit(Category newCategory) throws SQLException {
        Category oldCategory = categoryDao.get(newCategory.getId());
        oldCategory.setName(newCategory.getName());
        oldCategory.setProducts(newCategory.getProducts());
        categoryDao.edit(oldCategory);
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return categoryDao.delete(id);
    }

    @Override
    public Category get(long id) {
        return categoryDao.get(id);
    }

    @Override
    public Category get(String name) {
        return categoryDao.get(name);
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public List<Category> searchByCategory(String keyword) {
        return categoryDao.searchByCategory(keyword);
    }
}