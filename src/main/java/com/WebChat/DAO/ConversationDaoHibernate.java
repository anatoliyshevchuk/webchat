package com.WebChat.DAO;

import com.WebChat.Entity.Conversation;
import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;
import com.WebChat.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ConversationDaoHibernate implements ConversationDao {

    @Override
    //TODO: Refactor to new Conversation Class
    public List<Message> getConversation(int currentUser, int partnerId) {
        Session session = HibernateUtil.getOrOpenSession();
        List<Message> messages;
        session.beginTransaction();

        //Select messages sended from current user to partner
        Query query = session.createQuery("from Message m where m.messageToUser=:myId and m.messageFrom=:partnerId",Message.class);
        query.setParameter("myId",currentUser);
        query.setParameter("partnerId",partnerId);
        messages = query.getResultList();

        //And select messages sended from partner to current user
        query=session.createQuery("from Message m where m.messageToUser=:partnerId and m.messageFrom=:myId",Message.class);
        query.setParameter("myId",currentUser);
        query.setParameter("partnerId",partnerId);
        messages.addAll(query.getResultList());

        session.getTransaction().commit();
        return messages;
    }

    @Override
    public List<Conversation> getSuperficialConversationsList(User currentUser) {
        //Conversations without Lists of messages. Only 2 conversation partners
        Session session = HibernateUtil.getOrOpenSession();
        session.beginTransaction();
        Query query = session.createQuery("from Conversation c where c.currentUser=:currentUser",Conversation.class);
        query.setParameter("currentUser", currentUser);
        List<Conversation> conversationList = query.getResultList();

        session.getTransaction().commit();
        return conversationList;
    }
}
