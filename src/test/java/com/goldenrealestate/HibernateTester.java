package com.goldenrealestate;

import com.goldenrealestate.model.Building;
import com.goldenrealestate.model.Employee;
import com.goldenrealestate.model.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTester {

    @Test
    public void testEmployee() {
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

    @Test
    public void testTask() {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Employee employee = new Employee();
        employee.setName("Poyan");
        employee.setAge(34);
        session.save(employee);


        Building building = new Building();
        building.setName("mos");
        session.save(building);

        Task task = new Task();
        task.setBuilding(building);
        task.setEmployee(employee);
        task.setTaskDescription("cleaning");

        session.save(task);

        session.getTransaction().commit();
        session.close();



    }

}
