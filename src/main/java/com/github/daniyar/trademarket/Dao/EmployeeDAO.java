package com.github.daniyar.trademarket.Dao;

import com.github.daniyar.trademarket.Utils.DaoInterface;
import com.github.daniyar.trademarket.Utils.HibernateUtils;
import com.github.daniyar.trademarket.POJO.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
public class EmployeeDAO implements DaoInterface<Employee, Integer> {

    public EmployeeDAO() {
    }


    @Override
    public void persist(Employee entity) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Employee entity) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public Employee findById(Integer id) {
        Session session = HibernateUtils.getSession();
        Employee employee =(Employee) session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void delete(Employee entity) {

        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> findAll() {
        Session session = HibernateUtils.getSession();
        List<Employee> employees = (List<Employee>)session.createQuery("from Employee").list();
        return employees;
    }


    @Override
    public void deleteAll() {
        List<Employee> employees = findAll();
        for (Employee e : employees)
        {
            delete(e);
        }
    }
}
