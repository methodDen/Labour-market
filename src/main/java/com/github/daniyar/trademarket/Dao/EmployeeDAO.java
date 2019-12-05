package com.github.daniyar.trademarket.Dao; // importing packages

import com.github.daniyar.trademarket.Utils.DaoInterface;
import com.github.daniyar.trademarket.Utils.HibernateUtils;
import com.github.daniyar.trademarket.POJO.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
public class EmployeeDAO implements DaoInterface<Employee, Integer> { // DAO class for managing database table records for given entity via Class objects in program

    public EmployeeDAO() { // empty constructor
    }


    @Override
    public void persist(Employee entity) { // method for persisting Employee entity in database table
        Session session = HibernateUtils.getSession(); // Hibernate session for establishing connection between database and program correctly/ It has begun
        Transaction transaction = session.beginTransaction();  // Beginning transaction to make changes in database table and to run next method
        session.save(entity); // saving entity in database table
        transaction.commit(); // committing transaction - saving all changes in database table
        session.close(); // Closing session - collapsing connection between database and program
    }

    @Override
    public void update(Employee entity) { // method for updating Employee entity in database table
        Session session = HibernateUtils.getSession(); // Hibernate session for establishing connection between database and program correctly/ It has begun
        Transaction transaction = session.beginTransaction(); // Beginning transaction to make changes in database table and to to run next method
        session.update(entity); // updating entity inside database
        transaction.commit(); // committing transaction - saving all changes in database table
        session.close(); // Closing session - collapsing connection between database and program
    }

    @Override
    public Employee findById(Integer id) { // method for retrieving Employee entity using given id
        Session session = HibernateUtils.getSession(); // Hibernate session for establishing connection between database and program correctly/ It has begun
        Employee employee =(Employee) session.get(Employee.class, id); // retrieving entity by given id from database
        session.close(); // Closing session - collapsing connection between database and program
        return employee; // returning retrieved entity
    }

    @Override
    public void delete(Employee entity) { // method to delete Employee entity

        Session session = HibernateUtils.getSession(); // Hibernate session for establishing connection between database and program correctly/ It has begun
        Transaction transaction = session.beginTransaction(); // Beginning transaction to make changes in database and to to run next method
        session.delete(entity); // deleting entity from database table
        transaction.commit(); // committing transaction - saving all changes in database table
        session.close(); // Closing session - collapsing connection between database and program
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> findAll() { // method to retrieve all database table records
        Session session = HibernateUtils.getSession(); // Hibernate session for establishing connection between database and program correctly/ It has begun
        List<Employee> employees = (List<Employee>)session.createQuery("from Employee").list(); // retrieving list of entities from database table using SQL query
        session.close();  // Closing session - collapsing connection between database and program
        return employees; // returning retrieved entities
    }


    @Override
    public void deleteAll() { // deleting all records from database
        List<Employee> employees = findAll(); // retrieving all entities/records from database table
        for (Employee e : employees) // loop to delete one by one
        {
            delete(e); // delete entity
        }
    }
}
