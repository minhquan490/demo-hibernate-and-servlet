package dao;

import java.sql.SQLException;
import java.util.List;

import model.Category;
import model.Product;

public interface CategoryDao {

    void save(Category category) throws SQLException;

	void edit(Category category) throws SQLException;

	boolean delete(long id) throws SQLException;

	Category get(long id);

	Category get(String name);

	List<Category> getAll();

	List<Category> searchByCategory(String keyword);

	List<Product> getListProductsFromCategory(String name);
}