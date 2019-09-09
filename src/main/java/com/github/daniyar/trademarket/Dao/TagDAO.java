package com.github.daniyar.trademarket.Dao;

import com.github.daniyar.trademarket.Utils.DaoInterface;
import com.github.daniyar.trademarket.Utils.HibernateUtils;
import com.github.daniyar.trademarket.POJO.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
public class TagDAO implements DaoInterface<Tag, Integer> {


    public TagDAO() {
    }

    @Override
    public void persist(Tag entity) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Tag entity) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public Tag findById(Integer id) {
        Session session = HibernateUtils.getSession();
        Tag tag = (Tag) session.get(Tag.class, id);
        return tag;
    }

    @Override
    public void delete(Tag entity) {

        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Tag> findAll() {
        Session session = HibernateUtils.getSession();
        List<Tag> tags = (List<Tag>)session.createQuery("from Tag").list();
        return tags;
    }


    @Override
    public void deleteAll() {
        List<Tag> tags = findAll();
        for (Tag t : tags)
        {
            delete(t);
        }
    }
}
