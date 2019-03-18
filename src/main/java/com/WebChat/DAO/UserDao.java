package com.WebChat.DAO;

import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;

import java.util.List;

public interface UserDao {

    List<Message> getMessagesByUserId();
    User getUserById();
    User getUserByName();
    User getUserFullMessage();
    void saveUser();
}
