package com.WebChat.DAO;

import com.WebChat.Entity.Message;

import com.WebChat.Entity.User;
import com.WebChat.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class MessageDaoHibernate implements MessageDao  {

    @Override
    public List<Message> getMessagesByUserId(int id) {
        Session session = HibernateUtil.getOrOpenSession();
        session.beginTransaction();
        List<Message> messages = session.get(User.class, id).getMessageList();
        session.getTransaction().commit();
        return messages;
    }

    @Override
    public List<Message> getConversation(int myId, int partnerId) {

        Session session = HibernateUtil.getOrOpenSession();
        session.beginTransaction();
        //Select messages sended from current user to partner
        Query query = session.createQuery("from Message m where m.messageToUser.id=:myId and m.messageFrom=:partnerId",Message.class);
        query.setParameter("myId",myId);
        query.setParameter("partnerId",partnerId);

        List<Message> messages = query.getResultList();
        //And select messages sended from partner to current user
        query=session.createQuery("from Message m where m.messageToUser.id=:partnerId and m.messageFrom=:myId",Message.class);
        query.setParameter("myId",myId);
        query.setParameter("partnerId",partnerId);

        messages.addAll(query.getResultList());

        session.getTransaction().commit();
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
        Session session = HibernateUtil.getOrOpenSession();
        Transaction trx=session.beginTransaction();
        session.save(msg);
        trx.commit();
        session.close();
    }

}
