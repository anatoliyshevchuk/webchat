package com.WebChat.DAO;

import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;

import java.util.List;

public interface MessageDao {

     Message getMessageById(Long id);
     Message deleteMessage(Message msg);
     void saveMessage(Message msg);
}
