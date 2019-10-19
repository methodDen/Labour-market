package com.github.daniyar.trademarket.Dao;

import com.github.daniyar.trademarket.Utils.DaoInterface;
import com.github.daniyar.trademarket.Utils.HibernateUtils;
import com.github.daniyar.trademarket.POJO.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
public class EmployerDAO implements DaoInterface<Employer, Integer> {

    public EmployerDAO() {
    }


    @Override
    public void persist(Employer entity) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Employer entity) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public Employer findById(Integer id) {
        Session session = HibernateUtils.getSession();
        Employer employer =(Employer) session.get(Employer.class, id);
        session.close();
        return employer;
    }

    @Override
    public void delete(Employer entity) {

        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Employer> findAll() {
        Session session = HibernateUtils.getSession();
        List<Employer> employers= (List<Employer>)session.createQuery("from Employer").list();
        session.close();
        return employers;
    }


    @Override
    public void deleteAll() {
        List<Employer> employers = findAll();
        for (Employer e : employers)
        {
            delete(e);
        }
    }
}
