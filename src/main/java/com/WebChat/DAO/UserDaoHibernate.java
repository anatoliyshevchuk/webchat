package com.WebChat.DAO;

import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Component
public class UserDaoHibernate implements UserDao {

    private SessionFactory sessionFactory;

    private UserDaoHibernate() {
        sessionFactory = DaoConfigUtil.getHibernateSessionFactory();
    }

    @Override
    public List<Message> getMessagesByUserId(Long id) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User getUserByName(String loginname) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User u where u.name=:loginname",User.class);
        query.setParameter("loginname",loginname);
        User usr=null;
        try {
            usr = (User)query.getSingleResult();
        }catch(NoResultException e)
        {
            System.err.println(e);
        }
        return usr;
    }

    @Override
    public User getUserFullMessage(List<Message> listMessage) {
        return null;
    }

    @Override
    public void saveUser() {

    }
}
