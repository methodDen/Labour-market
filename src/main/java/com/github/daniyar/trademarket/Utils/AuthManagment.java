package com.github.daniyar.trademarket.Utils; // importing packages

import com.github.daniyar.trademarket.POJO.Employee;
import com.github.daniyar.trademarket.POJO.Employer;
import io.javalin.Context;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class AuthManagment { // managing basic authentification with roles

    public static Role getUserRole(Context context) { // getUserRole method to get role of user

        if (context.header("Authorization") == null) { // if authorization header is empty object
            return Role.ANONYMOUS; // return role
        }

        String login = context.basicAuthCredentials().getUsername(); // assign userName from context to login
        String password = context.basicAuthCredentials().getPassword(); // assign password from context to login

        Session session1 = HibernateUtils.getSession(); // starting session
        Transaction transaction1 = session1.beginTransaction(); // beginning transaction

        String hqlForEmployer = " FROM Employer employer WHERE employer.email = :email"; // Hibernate query language query
        Query queryForEmployer = session1.createQuery(hqlForEmployer); // creating query for session
        queryForEmployer.setParameter("email", login); // setting query parameter
        List resultsForEmployers = queryForEmployer.getResultList(); // getting result list after query

        if (resultsForEmployers != null && !resultsForEmployers.isEmpty()) { // if statement for list of results
            if (resultsForEmployers.size() == 1) { // if size of result list is equal to 1...
                Employer employer = (Employer) resultsForEmployers.get(0); // get the first object out from the list
                System.out.println(employer); // log it in console
                if (BCrypt.checkpw(password, employer.getPassword())) { // compare passwords
                    return employer.getRole(); // if they are similar, return the role
                }
                transaction1.commit(); // commit transaction
                session1.close(); // collapse session
            }
        }

        Session session2 = HibernateUtils.getSession(); // starting session
        Transaction transaction2 = session2.beginTransaction(); // beginning transaction


        String hqlForEmployee = " FROM Employee employee WHERE employee.email = :email"; // Hibernate query language query
        Query queryForEmployee = session2.createQuery(hqlForEmployee); // creating query for session
        queryForEmployee.setParameter("email", login); // setting query parameter
        List resultsForEmployees = queryForEmployee.getResultList(); // getting result list after query

        if (resultsForEmployees != null && !resultsForEmployees.isEmpty()) // if statement for list of results
        {
            if (resultsForEmployees.size() == 1) // if size of result list is equal to 1...
            {
                Employee employee = (Employee) resultsForEmployees.get(0); // get the first object out from the list
                System.out.println(employee); // log it in console

                if (BCrypt.checkpw(password, employee.getPassword())) { // compare passwords
                    return employee.getRole(); // if they are similar, return the role
                }
                transaction2.commit(); // commit transaction
                session2.close(); // collapse session

            }
        }
        return Role.ANONYMOUS; // return role
    }

}
