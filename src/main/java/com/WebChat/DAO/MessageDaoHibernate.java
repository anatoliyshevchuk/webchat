package com.WebChat.DAO;

import com.WebChat.Entity.Message;

import com.WebChat.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MessageDaoHibernate implements MessageDao  {

    private SessionFactory sessionFactory;

    public MessageDaoHibernate() {
        sessionFactory = DaoConfig.getHibernateSessionFactory();
    }

    @Override
    public List<Message> getMessagesByUserId(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session.get(User.class, id).getMessageList();
    }

    @Override
    public Message getMessageById(Long id) {
        return null;
    }

    @Override
    public Message getMessageByUser(User usr) {
        return null;
    }

    @Override
    public void saveMessage(Message msg) {

    }
}