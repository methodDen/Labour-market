package Dao;

import POJO.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;
public class JobDAO implements DaoInterface<Job, Integer> {
    private Session session;
    private Transaction transaction;

    public JobDAO() {
    }

    public Session openCurrentSession() {
        session = getSessionFactory().openSession();
        return session;
    }

    public Session openCurrentSessionwithTransaction() {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeCurrentSession() {
        session.close();
    }

    public void closeCurrentSessionwithTransaction() {
        transaction.commit();
        session.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Employer.class);
        configuration.addAnnotatedClass(Job.class);
        configuration.addAnnotatedClass(Rating.class);
        configuration.addAnnotatedClass(Tag.class);
        configuration.configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
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

    @SuppressWarnings("unchecked")
    @Override
    public List<Job> findAll() {
        List<Job> jobs = (List<Job>)getSession().createQuery("from Job").list();
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
