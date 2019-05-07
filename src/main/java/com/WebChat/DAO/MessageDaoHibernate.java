package com.WebChat.DAO;

import com.WebChat.Entity.Message;

import com.WebChat.Entity.User;
import com.WebChat.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
class MessageDaoHibernate implements MessageDao  {

    static final Logger logger = Logger.getLogger(MessageDaoHibernate.class);

    @Override
    public Message getMessageById(Long id) {
        return null;
    }

    @Override
    public Message deleteMessage(Message msg) {
        return null;
    }

    @Override
    public boolean saveMessage(Message msg) {
        Session session = HibernateUtil.getOrOpenSession();
        boolean check=false;
        Transaction trx = null;
        try{
            trx = session.beginTransaction();
            session.save(msg);
            trx.commit();
            check=true;
        }catch(Exception e){
            check=false;
            trx.rollback();
            logger.error(e);
        }
        return check;
    }

}
