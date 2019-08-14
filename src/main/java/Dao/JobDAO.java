package Dao;

import POJO.Company;
import POJO.Job;
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

public class JobDAO implements DaoInterface<Job, Integer> {
    private Session session;
    private Transaction transaction;
    private static SessionFactory sessionFactory;

    public JobDAO() {
    }
    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings = Settings.getSettings();
                configuration.addAnnotatedClass(Job.class);
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
        JobDAO.sessionFactory = sessionFactory;
    }

    @Override
    public void persist(Job entity) {
        getSession().save(entity);
    }

    @Override
    public void update(Job entity) {
        getSession().update(entity);
    }

    @Override
    public Job findById(Integer id) {
        Job job = (Job) getSession().get(Job.class, id);
        return job;
    }

    @Override
    public void delete(Job entity) {
        getSession().delete(entity);
    }

    @Override
    public List<Job> findAll() {
        List<Job> jobs = (List<Job>) getSession().createQuery("from Job");
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
