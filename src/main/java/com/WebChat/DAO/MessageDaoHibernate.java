package com.WebChat.DAO;

import com.WebChat.Entity.Message;

import com.WebChat.Entity.User;
import com.WebChat.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDaoHibernate implements MessageDao  {

    @Override
    public List<Message> getMessagesByUserId(int id) {
        Session session = HibernateUtil.getSession();
        Transaction trx = session.beginTransaction();
        List<Message> messages = session.get(User.class, id).getMessageList();
        trx.commit();
        return messages;
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
        Session session = HibernateUtil.getSession();
        Transaction trx=session.beginTransaction();
        session.save(msg);
        trx.commit();
        session.close();
    }

}
