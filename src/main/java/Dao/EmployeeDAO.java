package Dao;

import POJO.Employee;
import Utils.Settings;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;
// check again (unchecked)
public class EmployeeDAO implements DaoInterface<Employee, Integer> {
    private Session session;
    private Transaction transaction;
    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings = Settings.getSettings();
                configuration.addAnnotatedClass(Employee.class);
                configuration.setProperties(settings);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public EmployeeDAO() {
    }

    private Session openSessionEmployee() { // noodles
        session = getSessionFactory().openSession();
        return session;
    }
    private Session openSessionWithTransactionEmployee() { // noodles
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeSession() {
        session.close();
    }
    public void closeSessionWithTransaction() {
        transaction.commit();
        session.close();
    }


    @Override
    public void persist(Employee entity) {
        getSession().save(entity);
    }

    @Override
    public void update(Employee entity) {
        getSession().update(entity);
    }

    @Override
    public Employee findById(Integer id) {
        Employee employee = (Employee) getSession().get(Employee.class, id);
        return  employee;
    }

    @Override
    public void delete(Employee entity) {
        getSession().delete(entity);
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = (List<Employee>) getSession().createQuery("from Employee").list();
        return employees;
    }

    @Override
    public void deleteAll() {
        List<Employee> employees = findAll();
        for (Employee e : employees) {
            delete(e);
        }
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        EmployeeDAO.sessionFactory = sessionFactory;
    }
}
