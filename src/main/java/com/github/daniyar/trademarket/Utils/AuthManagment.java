package com.github.daniyar.trademarket.Utils;

import com.github.daniyar.trademarket.POJO.Employer;
import io.javalin.Context;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class AuthManagment {


    public static Role getUserRole(Context context) {

        if (context.header("Authorization") == null) {
            return Role.ANONYMOUS;
        }

        String login = context.basicAuthCredentials().getUsername();
        String password = context.basicAuthCredentials().getPassword();

        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        String hql = " FROM Employer employer WHERE employer.email = :email";
        Query query = session.createQuery(hql);

        query.setParameter("email", login);

        List results = query.getResultList();

        if (results != null && !results.isEmpty()) {
            if (results.size() == 1) {
                Employer employer = (Employer) results.get(0);
                System.out.println(employer);
                if (BCrypt.checkpw(password, employer.getPassword()))
                {
                    return employer.getRole();
                }
                transaction.commit();
                session.close();

            }
        }
        return Role.ANONYMOUS;
    }

}
