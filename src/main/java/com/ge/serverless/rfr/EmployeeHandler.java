package com.ge.serverless.rfr;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EmployeeHandler implements RequestHandler<Request, String> {

    @Override
    public String handleRequest(Request request, Context context) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee employee = new Employee();
            employee.setId(request.id);
            employee.setFirstName(request.firstName);
            employee.setLastName(request.lastName);
            employee.setEmailId(request.emailId);
            session.save(employee);
            session.getTransaction().commit();
        }

        return String.format("Added %s %s %s %s.", request.id, request.firstName, request.lastName, request.emailId);
    }
}
