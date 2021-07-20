package dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.UserDao;
import model.User;
import utils.HibernateUtil;
import utils.Log;

public class UserDaoImpl implements UserDao{

    private static final String EMAIL_VALID = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void save(User user) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
			Log.getLog("UserDaoImpl", e.getMessage(), e);
        }
    }

    @Override
    public void edit(User user) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (Exception e) {
			Log.getLog("UserDaoImpl", e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(long id) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "delete User U where U.user_id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
			Log.getLog("UserDaoImpl", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public User get(String username) {
        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM User U WHERE U.username = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            user = (User) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
			Log.getLog("UserDaoImpl", e.getMessage(), e);
        }
        return user;
    }

    @Override
    public User get(long id) {
        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM User U WHERE U.user_id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            user = (User) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
			Log.getLog("UserDaoImpl", e.getMessage(), e);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            Log.getLog("UserDaoImpl", e.getMessage(), e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> search(String username) {
        Transaction transaction = null;
        List<User> listSearchUsers = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM User U WHERE U.username LIKE :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", "%" + username + "%");
            listSearchUsers = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("UserDaoImpl", e.getMessage(), e);
        }
        return listSearchUsers;
    }

    @Override
    public boolean checkEmailExist(String email) {
        if (!email.isBlank() && email.matches(EMAIL_VALID)) {
            Transaction transaction = null;
            User user = null;
            try (Session session = HibernateUtil.getSession()) {
                transaction = session.beginTransaction();
                String hql = "FROM User U WHERE U.email = :email";
                Query query = session.createQuery(hql);
                query.setParameter("email", email);
                user = (User) query.getSingleResult();
                if (user != null) {
                    return true;
                }
                transaction.commit();
            } catch (Exception e) {
                Log.getLog("UserDaoImpl", e.getMessage(), e);
            }
        }
        return false;
    }

    @Override
    public boolean checkUsernameExist(String username) {
        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM User U WHERE U.username = :username";
            Query query = (Query) session.createQuery(hql).setParameter("username", username);
            user = (User) query.getSingleResult();
            if (user != null) {
                return true;
            }
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("UserDaoImpl", e.getMessage(), e);
        }
        return false;
    }
}