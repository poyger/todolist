package com.goldenrealestate.dao;

import com.goldenrealestate.model.Employee;
import com.goldenrealestate.model.Task;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static com.goldenrealestate.util.HibernateUtil.getSession;

/**
 * User: Poyan Gerami
 * Email: poyan.gerami@eniro.com
 * Date: 25/06/16
 */
public class TaskDao {

    public static void save(Task task) {
        Session session = getSession();
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
    }

    public static List<Task> getAll() {
        Session session = getSession();
        session.beginTransaction();
        String hql = "FROM Task AS T";
        Query<Task> query = session.createQuery(hql, Task.class);
        List<Task> tasks = query.list();
        session.getTransaction().commit();
        return tasks;
    }

    public static List<Task> getTask(Employee employee) {
        Session session = getSession();
        session.beginTransaction();
        String hql = "FROM Task AS T where T.employee.id=" + employee.getId();
        Query<Task> query = session.createQuery(hql, Task.class);
        List<Task> tasks = query.list();
        session.getTransaction().commit();
        return tasks;
    }


}
