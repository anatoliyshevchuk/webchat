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
    public List<Message> getListMessage(int currentUser, int partnerId) {
        Session session = HibernateUtil.getOrOpenSession();
        List<Message> messages=null;
        try{
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
        }catch (Exception e) {
            session.getTransaction().rollback();
        }
        return messages;
    }

    @Override
    public List<Conversation> getSuperficialConversationsList(User currentUser) {
        //Conversations without Lists of messages. Only 2  users
        Session session = HibernateUtil.getOrOpenSession();
        List<Conversation> conversationList=null;

        try{
            session.beginTransaction();
            Query query = session.createQuery("from Conversation c where c.currentUser=:currentUser",Conversation.class);
            query.setParameter("currentUser", currentUser);
            conversationList = query.getResultList();
            session.getTransaction().commit();
        }catch(Exception e) {
            System.err.println(e);
            session.getTransaction().rollback();
        }
        return conversationList;
    }

    @Override
    public void save(Conversation conversation) {
        Session session = HibernateUtil.getOrOpenSession();
        try{
            session.beginTransaction();
            session.save(conversation);
            session.getTransaction().commit();
        }catch(Exception e){
            System.err.println(e);
            session.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Conversation conversation) {
        Session session = HibernateUtil.getOrOpenSession();
        try{
            session.beginTransaction();
            session.delete(conversation);
            session.getTransaction().commit();
        }catch(Exception e){
            System.err.println(e);
            session.getTransaction().rollback();
        }
    }

    @Override
    public Conversation getConversation(User usr1,User usr2) {
        Session session = HibernateUtil.getOrOpenSession();
        Conversation conv = null;
        try{
            session.beginTransaction();
            Query query = session.createQuery("from Conversation c where c.currentUser=:currentUser AND c.partnerUser=:partnerUser",Conversation.class);
            query.setParameter("currentUser", usr1);
            query.setParameter("partnerUser",usr2);
            conv = (Conversation) query.getSingleResult();
            session.getTransaction().commit();
        }catch(Exception e){
            System.err.println(e);
            session.getTransaction().rollback();
        }
        return conv;
    }
}
