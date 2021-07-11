package dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.UserDao;
import model.User;
import utils.HibernateUtil;

public class UserDaoImpl implements UserDao{

    private static final String EMAIL_VALID = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void save(User user) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
        }
    }

    @Override
    public void edit(User user) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                return true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
        }
        return false;
    }

    @Override
    public User get(String username) {
        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM User U WHERE U.username = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            user = (User) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
        }
        return user;
    }

    @Override
    public User get(int id) {
        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM User U WHERE U.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            user = (User) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    @Override
    public List<User> search(String username) {
        Transaction transaction = null;
        List<User> users = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM User U WHERE U.username LIKE :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", "%" + username + "%");
            users = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean checkEmailExist(String email) {
        if (!email.isBlank() && email.matches(EMAIL_VALID)) {
            Transaction transaction = null;
            User user = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                String hql = "FROM User U WHERE U.email = :email";
                Query query = session.createQuery(hql);
                query.setParameter("email", email);
                user = (User) query.getSingleResult();
                transaction.commit();
                if (user != null) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
        return false;
    }

    @Override
    public boolean checkUsernameExist(String username) {
        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM User U WHERE U.username = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            user = (User) query.getSingleResult();
            transaction.commit();
            if (user == null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}