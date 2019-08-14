package Dao;

import Utils.Settings;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Properties;
// https://dzone.com/articles/hibernate-mapping
public class EmployerDAO implements DaoInterface<EmployerDAO, Integer> {
    private Session session;
    private Transaction transaction;
    private static SessionFactory sessionFactory;
    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings = Settings.getSettings();
                configuration.addAnnotatedClass(EmployerDAO.class);
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
        EmployerDAO.sessionFactory = sessionFactory;
    }

    public EmployerDAO() {
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
    public void persist(EmployerDAO entity) {
        getSession().save(entity);
    }

    @Override
    public void update(EmployerDAO entity) {
        getSession().update(entity);
    }

    @Override
    public EmployerDAO findById(Integer id) {
        EmployerDAO employer = (EmployerDAO) getSession().get(EmployerDAO.class, id);
        return employer;
    }

    @Override
    public void delete(EmployerDAO entity) {
        getSession().delete(entity);
    }

    @Override
    public List<EmployerDAO> findAll() {
        List<EmployerDAO> employers = (List<EmployerDAO>) getSession().createQuery("from EmployerDAO");
        return employers;
    }

    @Override
    public void deleteAll() {
        List<EmployerDAO> employers = findAll();
        for (EmployerDAO e : employers)
        {
            delete(e);
        }
    }
}
