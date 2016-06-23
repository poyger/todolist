package com.goldenrealestate;

import com.goldenrealestate.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTester {

    @Test
    public void testApp() {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Employee employee = new Employee();
        employee.setName("Poyan");
        employee.setAge(34);

        session.save(employee);

        session.getTransaction().commit();
        session.close();
    }
}
