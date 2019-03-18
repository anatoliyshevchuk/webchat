package com.WebChat.DAO;

import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.management.Query;
import java.util.List;

@Component
public class UserDaoHibernate implements UserDao {

    private SessionFactory sessionFactory = DaoConfig.getHibernateSessionFactory();

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
        User usr = new User();
        //Query query = session.createQuery("select u from User u where u.name=:name");

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
