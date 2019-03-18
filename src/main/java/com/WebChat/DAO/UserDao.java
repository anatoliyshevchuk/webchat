package com.WebChat.DAO;

import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;

import java.util.List;

public interface UserDao {

    List<Message> getMessagesByUserId(Long id);
    User getUserById(Long id);
    User getUserByName(String name);
    User getUserFullMessage(List<Message> listMessage);
    void saveUser();
}
