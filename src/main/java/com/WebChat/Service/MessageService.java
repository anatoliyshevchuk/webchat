package com.WebChat.Service;

import com.WebChat.DAO.MessageDao;
import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    private MessageDao messageDAO;

    @Autowired
    public MessageService(MessageDao messageDAO) {
        this.messageDAO = messageDAO;
    }

    public void sendMessage(String msg, User fromUser, User toUserName) {
        Message message = new Message(msg, new Date(), fromUser, toUserName, true);
        messageDAO.saveMessage(message);
    }

    public List<Message> sortMessageByDate(List<Message> list){
        Collections.sort(list);
        return list;
    }
}
