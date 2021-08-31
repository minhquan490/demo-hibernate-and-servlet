package dao.impl;

import java.sql.SQLException;

import javax.persistence.Query;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.PasswordForgottenDao;
import model.PasswordForgotten;
import utils.HibernateUtil;
import utils.Log;

public class PasswordForgottenDaoImpl implements PasswordForgottenDao {

    @Override
    public void save(PasswordForgotten forgotten) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(forgotten);
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("PasswordForgottenDaoImpl", e.getMessage(), e);
        }
    }

    @Override
    public PasswordForgotten get(String token) {
        Transaction transaction = null;
        PasswordForgotten forgotten = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM PasswordForgotten P WHERE P.token = :token";
            Query query = session.createQuery(hql);
            query.setParameter("token", DigestUtils.sha256Hex(token));
            forgotten = (PasswordForgotten) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            Log.getLog("PasswordForgottenDaoImpl", e.getMessage(), e);
        }
        return forgotten;
    }
    
}