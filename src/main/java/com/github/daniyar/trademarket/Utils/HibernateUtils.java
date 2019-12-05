
package com.github.daniyar.trademarket.Utils; // importing packages

import com.github.daniyar.trademarket.Dao.EmployerDAO;
import com.github.daniyar.trademarket.POJO.*;
import io.javalin.Context;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateUtils { // class for creating sessions for interaction between database and program
    static SessionFactory sessionFactory; // sessionFactory to create session

    static {
        Configuration configuration = new Configuration(); // adjusting configuration from hibernate.cfg.xml file
        configuration.addAnnotatedClass(Company.class); // adding class to configuration
        configuration.addAnnotatedClass(Employee.class);   // adding class to configuration
        configuration.addAnnotatedClass(Employer.class);  // adding class to configuration
        configuration.addAnnotatedClass(Job.class);  // adding class to configuration
        configuration.addAnnotatedClass(Rating.class);  // adding class to configuration
        configuration.addAnnotatedClass(Tag.class);  // adding class to configuration
        configuration.configure(); // configuring configuration
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build()); // build session
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

}
