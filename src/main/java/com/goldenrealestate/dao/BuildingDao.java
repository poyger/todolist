package com.goldenrealestate.dao;

import com.goldenrealestate.model.Building;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static com.goldenrealestate.util.HibernateUtil.getSession;

public class BuildingDao {

    public static void save(Building building) {
        Session session = getSession();
        session.beginTransaction();
        session.save(building);
        session.getTransaction().commit();
    }

    public static List<Building> getAll() {
        Session session = getSession();
        session.beginTransaction();
        String hql = "FROM Building AS E";
        Query query = session.createQuery(hql, Building.class);
        List<Building> buildings = query.list();
        session.getTransaction().commit();
        return buildings;
    }

}
