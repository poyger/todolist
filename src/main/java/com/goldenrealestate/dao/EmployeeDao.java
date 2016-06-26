package com.goldenrealestate.dao;

import com.goldenrealestate.model.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static com.goldenrealestate.util.HibernateUtil.getSession;

public class EmployeeDao {

    public static void save(Employee employee) {
        Session session = getSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
    }

    public static List<Employee> getAll() {
        Session session = getSession();
        session.beginTransaction();
        String hql = "FROM Employee AS E";
        Query<Employee> query = session.createQuery(hql, Employee.class);
        List<Employee> employees = query.list();
        session.getTransaction().commit();
        return employees;
    }

}

