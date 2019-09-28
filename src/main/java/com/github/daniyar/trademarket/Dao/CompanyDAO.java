package com.github.daniyar.trademarket.Dao;

import com.github.daniyar.trademarket.Utils.DaoInterface;
import com.github.daniyar.trademarket.Utils.HibernateUtils;
import com.github.daniyar.trademarket.POJO.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
public class CompanyDAO implements DaoInterface<Company, Integer> {

    public CompanyDAO() {
    }

    @Override
    public void persist(Company entity) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Company entity) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public Company findById(Integer id) {
        Session session = HibernateUtils.getSession();
        Company company =(Company)session.get(Company.class, id);
        session.close();
        return company;
    }

    @Override
    public void delete(Company entity) {

        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Company> findAll() {
        Session session = HibernateUtils.getSession();
        List<Company> companies = (List<Company>)session.createQuery("from Company").list();
        session.close();
        return companies;
    }


    @Override
    public void deleteAll() {
        List<Company> companies = findAll();
        for (Company c : companies)
        {
            delete(c);
        }
    }
}
