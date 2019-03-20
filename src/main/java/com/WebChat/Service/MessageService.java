package com.WebChat.Service;

import com.WebChat.DAO.MessageDao;
import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    private MessageDao messageDAO;
    private UserService userService;

    @Autowired
    public MessageService(@Qualifier("messageDaoHibernate") MessageDao messageDAO, UserService userService) {
        this.messageDAO = messageDAO;
        this.userService = userService;
    }

    public List<Message> getMessagesByUserId(Long id) {
        return messageDAO.getMessagesByUserId(id);
    }

    public boolean sendMessage(String msg, User fromUser, User toUserName) {

        //TODO: Date Format bean?
        Message message = new Message(msg, new Date(),fromUser, toUserName);
        System.out.println(message);
        messageDAO.saveMessage(message);

        return false;
    }
}
