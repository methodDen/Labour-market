package Dao;

import POJO.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;
public class RatingDAO implements DaoInterface<Rating, Integer> {
    private Session session;
    private Transaction transaction;

    public RatingDAO() {
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
    public void persist(Rating entity) {
        getSession().save(entity);
    }

    @Override
    public void update(Rating entity) {
        getSession().update(entity);
    }

    @Override
    public Rating findById(Integer id) {
        Rating rating = (Rating) getSession().get(Rating.class, id);
        return rating;

    }

    @Override
    public void delete(Rating entity) {
        getSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Rating> findAll() {
        List<Rating> ratings = (List<Rating>)getSession().createQuery("from Rating").list();
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
