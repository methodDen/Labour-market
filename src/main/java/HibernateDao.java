import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class HibernateDao {

    public void saveObject (Object o) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(o);
        transaction.commit();

        if (transaction == null) {
            transaction.rollback();
        }

    }
    public Object getById (String entityName, Serializable id) {
        Session session = HibernateUtils.getSessionFactory().openSession(); // required in every method
        session.beginTransaction();
        Object o = (Object)session.get(entityName, id);
        session.getTransaction().commit();
        return o;
    }
    public List<Object> getObjects(String entityName) {
        Session session = HibernateUtils.getSessionFactory().openSession(); // required in every method
        return session.createQuery(entityName, Object.class).list();
    }


}
