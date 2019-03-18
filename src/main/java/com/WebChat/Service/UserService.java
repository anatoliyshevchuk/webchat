package com.WebChat.Service;
import com.WebChat.DAO.*;
import com.WebChat.Entity.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private MessageDao messageDAO;

    @Autowired
    void setMessageDAO(@Qualifier("messageDaoHibernate") MessageDao messageDAO)
    {
        this.messageDAO=messageDAO;
    }

    public List<Message> getMessagesByUserId(Long id) {
        return messageDAO.getMessagesByUserId(id);
    }

}
