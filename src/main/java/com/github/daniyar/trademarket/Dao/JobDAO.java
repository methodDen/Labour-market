package com.github.daniyar.trademarket.Dao;

import com.github.daniyar.trademarket.Utils.DaoInterface;
import com.github.daniyar.trademarket.Utils.HibernateUtils;
import com.github.daniyar.trademarket.POJO.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
public class JobDAO implements DaoInterface<Job, Integer> {

    public JobDAO() {
    }

    @Override
    public void persist(Job entity) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Job entity) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public Job findById(Integer id) {
        Session session = HibernateUtils.getSession();
        Job job =(Job) session.get(Job.class, id);
        session.close();
        return job;
    }

    @Override
    public void delete(Job entity) {

        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Job> findAll() {
        Session session = HibernateUtils.getSession();
        List<Job> jobs= (List<Job>)session.createQuery("from Job").list();
        session.close();
        return jobs;
    }


    @Override
    public void deleteAll() {
        List<Job> jobs = findAll();
        for (Job j : jobs)
        {
            delete(j);
        }
    }
}
