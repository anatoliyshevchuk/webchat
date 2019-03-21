package com.WebChat.DAO;

import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;

import java.util.List;


public interface MessageDao {

     List<Message> getMessagesByUserId(int id);
     Message getMessageById(Long id);
     Message getMessageByUser(User usr);
     void saveMessage(Message msg);
}
