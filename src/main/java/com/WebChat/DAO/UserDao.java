package com.WebChat.DAO;

import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;

import java.util.List;

public interface UserDao {

    User getUserById(Long id);
    User getUserByName(String name);
    void saveUser(User user);
}
