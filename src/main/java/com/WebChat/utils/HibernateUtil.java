package com.WebChat.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = DaoConfigUtil.getHibernateSessionFactory();

    public static Session getOrOpenSession()
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
