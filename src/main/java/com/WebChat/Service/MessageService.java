package com.WebChat.Service;

import com.WebChat.DAO.MessageDao;
import com.WebChat.Entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private MessageDao messageDAO;

    @Autowired
    public MessageService(@Qualifier("messageDaoHibernate") MessageDao messageDAO) {
        this.messageDAO = messageDAO;
    }


    public List<Message> getMessagesByUserId(Long id) {
        return messageDAO.getMessagesByUserId(id);
    }
}
