package com.klef.fsad.exam.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.klef.fsad.exam.entity.Service;

import java.util.Date;

public class ClientDemo
{
    public static void main(String[] args)
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        // INSERT RECORD
        Service s = new Service();
        s.setId(101);
        s.setName("Maintenance");
        s.setDate(new Date());
        s.setStatus("Pending");

        session.persist(s);

        tx.commit();

        System.out.println("Record Inserted Successfully");

        // UPDATE USING HQL WITH NAMED PARAMETERS
        session.beginTransaction();

        Query query = session.createQuery(
                "update Service set name=:name, status=:status where id=:id"
        );

        query.setParameter("name", "Repair");
        query.setParameter("status", "Completed");
        query.setParameter("id", 101);

        int result = query.executeUpdate();

        session.getTransaction().commit();

        System.out.println("Rows Updated: " + result);

        session.close();
        sf.close();
    }
}