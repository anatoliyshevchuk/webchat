package com.WebChat.DAO;

import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.transaction.NotSupportedException;


class DaoConfig {

    private static SessionFactory factory;

    static SessionFactory getHibernateSessionFactory(){
        if(factory!=null)
            return factory;
        return createHibernateSessionFactory();
    }

    private static SessionFactory createHibernateSessionFactory() {
        factory= new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Message.class)
                .buildSessionFactory();
        return factory;
    }

    private void getJdbcTemplate() throws NotSupportedException
    {
        throw new NotSupportedException("tba");
    }
}
