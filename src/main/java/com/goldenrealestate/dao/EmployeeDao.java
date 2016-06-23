package com.goldenrealestate.dao;

import com.goldenrealestate.model.Employee;
import org.hibernate.Session;

import static com.goldenrealestate.util.HibernateUtil.getSession;

public class EmployeeDao {

    public static void save(Employee employee) {
        Session session = getSession();
        session.beginTransaction();
        session.saveOrUpdate(employee);
        session.getTransaction().commit();
    }
}
