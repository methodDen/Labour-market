package Dao;

import POJO.Company;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import Utils.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.util.List;
import java.util.Properties;
// check again (unchecked)
public class CompanyDAO implements DaoInterface<Company, Integer> { // Id as String is probably unsuitable implementation
    private Session session;
    private Transaction transaction;
    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings = Settings.getSettings();
                configuration.addAnnotatedClass(Company.class);
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

    public CompanyDAO() {
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    } // noodles

    public Transaction getTransaction() {
        return transaction;
    } // noodles

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        CompanyDAO.sessionFactory = sessionFactory;
    }

    public Session openSession() { // noodles
        session = getSessionFactory().openSession();
        return session;
    }
    public Session openSessionWithTransaction() { // noodles
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
    public void persist(Company entity) {
        getSession().save(entity);
    }

    @Override
    public void update(Company entity) {
        getSession().update(entity);
    }

    @Override
    public Company findById(Integer id) {
        Company company = (Company) getSession().get(Company.class, id);
        return company;
    }

    @Override
    public void delete(Company entity) {
        getSession().delete(entity);
    }

    @Override
    public List<Company> findAll() {
        List<Company> companies = (List<Company>) getSession().createQuery("from Company").list();
        return companies;
    }

    @Override
    public void deleteAll() {
        List<Company> companyList = findAll();
        for (Company c : companyList)
        {
            delete(c);
        }
    }
}
