package com.github.daniyar.trademarket.Dao;

import com.github.daniyar.trademarket.Utils.DaoInterface;
import com.github.daniyar.trademarket.Utils.HibernateUtils;
import com.github.daniyar.trademarket.POJO.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
public class RatingDAO implements DaoInterface<Rating, Integer> {

    public RatingDAO() {
    }

    @Override
    public void persist(Rating entity) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Rating entity) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public Rating findById(Integer id) {
        Session session = HibernateUtils.getSession();
        Rating rating =(Rating) session.get(Rating.class, id);
        return rating;
    }

    @Override
    public void delete(Rating entity) {

        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Rating> findAll() {
        Session session = HibernateUtils.getSession();
        List<Rating> ratings = (List<Rating>)session.createQuery("from Rating").list();
        return ratings;
    }


    @Override
    public void deleteAll() {
        List<Rating> ratings = findAll();
        for (Rating r : ratings)
        {
            delete(r);
        }
    }
}
