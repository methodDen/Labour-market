package Dao;

import POJO.Company;
import POJO.Tag;
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

public class TagDAO implements DaoInterface<Tag, Integer> {
    private Session session;
    private Transaction transaction;
    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings = Settings.getSettings();
                configuration.addAnnotatedClass(Tag.class);
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

    public TagDAO() {
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
        TagDAO.sessionFactory = sessionFactory;
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
    public void persist(Tag entity) {
        getSession().save(entity);
    }

    @Override
    public void update(Tag entity) {
        getSession().update(entity);
    }

    @Override
    public Tag findById(Integer id) {
        Tag tag = (Tag) getSession().get(Tag.class, id);
        return tag;
    }

    @Override
    public void delete(Tag entity) {
        getSession().delete(entity);
    }

    @Override
    public List<Tag> findAll() {
        List<Tag> tags = (List<Tag>) getSession().createQuery("from Tag");
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
