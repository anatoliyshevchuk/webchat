package com.WebChat.DAO;

import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageDao {

     List<Message> getMessagesByUserId(Long id);
     Message getMessageById(Long id);
     Message getMessageByUser(User usr);
     void saveMessage(Message msg);
}
