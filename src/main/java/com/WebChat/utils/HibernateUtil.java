package com.WebChat.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = DaoConfigUtil.getHibernateSessionFactory();

    //TODO: Change that shit, it not working *No CurrentSessionContext*
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
