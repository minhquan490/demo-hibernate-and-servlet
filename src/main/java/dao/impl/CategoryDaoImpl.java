package dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.CategoryDao;
import model.Category;
import model.Product;
import utils.HibernateUtil;
import utils.Log;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void save(Category category) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("CategoryDaoImpl", e.getMessage(), e);
        }
    }

    @Override
    public void edit(Category category) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(category);
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("CategoryDaoImpl", e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(long id) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "delete Category C where C.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
			Log.getLog("CategoryDaoImpl", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Category get(long id) {
        Transaction transaction = null;
        Category category = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Category C WHERE C.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            category = (Category) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("CategoryDaoImpl", e.getMessage(), e);
        }
        return category;
    }

    @Override
    public Category get(String name) {
        Transaction transaction = null;
        Category category = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Category C WHERE C.name = :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            category = (Category) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("CategoryDaoImpl", e.getMessage(), e);
        }
        return category;
    }

    @Override
    public List<Category> getAll() {
        try(Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Category", Category.class).list();
        } catch (Exception e) {
            Log.getLog("CategoryDaoImpl", e.getMessage(), e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Category> searchByCategory(String keyword) {
        Transaction transaction = null;
        List<Category> categories = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Category C WHERE C.name LIKE :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", "%" + keyword + "%");
            categories = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("CategoryDaoImpl", e.getMessage(), e);
        }
        return categories;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getListProductsFromCategory(String name) {
        Transaction transaction = null;
        List<Product> listProducts = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "select distinct P from Product P join P.categories cate where cate.name = :category_name";
            Query query = session.createQuery(hql).setParameter("category_name", name);
            listProducts = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("CategoryDaoImpl", e.getMessage(), e);
        }
        return listProducts;
    }
}