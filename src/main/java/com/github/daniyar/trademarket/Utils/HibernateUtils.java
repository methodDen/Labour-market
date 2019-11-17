package com.github.daniyar.trademarket.Utils;

import com.github.daniyar.trademarket.Dao.EmployerDAO;
import com.github.daniyar.trademarket.POJO.*;
import io.javalin.Context;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateUtils {
    static SessionFactory sessionFactory;
    private static EmployerDAO employerDAO;

    static {
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
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

}
