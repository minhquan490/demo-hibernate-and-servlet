package dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.StatusDao;
import model.Status;
import utils.HibernateUtil;
import utils.Log;

public class StatusDaoImpl implements StatusDao {

    @Override
    public void save(Status status) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(status);
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("StatusDaoImpl", e.getMessage(), e);
        }
    }

    @Override
    public void edit(Status status) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(status);
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("StatusDaoImpl", e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "delete Status S where S.status_id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            Log.getLog("StatusDaoImpl", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Status> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Status", Status.class).list();
        } catch (Exception e) {
            Log.getLog("StatusDaoImpl", e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Status find(long id) {
        Transaction transaction = null;
        Status status = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Status S WHERE S.status_id = :id";
            Query query = session.createQuery(hql);
            status = (Status) query.setParameter("id", id).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("StatusDaoImpl", e.getMessage(), e);
        }
        return status;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Status> getListStatusOfUser(long id) {
        List<Status> listStatus = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Status S WHERE S.cart_id = :id";
            Query query = session.createQuery(hql);
            listStatus = query.setParameter("id", id).getResultList();
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("StatusDaoImpl", e.getMessage(), e);
        }
        return listStatus;
    }
}