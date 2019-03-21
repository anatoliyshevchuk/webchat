package com.WebChat.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateUtil {

    private static final SessionFactory sessionFactory = DaoConfigUtil.getHibernateSessionFactory();

    public static Session getSession()
    {
        Session session=null;
        try{
            session = sessionFactory.getCurrentSession();
        }
        catch (Exception e) {
            System.err.println(e);
        }
        if(session!=null)
            return session;
        return sessionFactory.openSession();
    }
}
