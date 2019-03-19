package com.WebChat.Service;
import com.WebChat.DAO.*;
import com.WebChat.Entity.Message;

import com.WebChat.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private MessageDao messageDAO;
    private UserDao userDao;

    @Autowired
    void setMessageDAO(@Qualifier("messageDaoHibernate") MessageDao messageDAO)
    {
        this.messageDAO = messageDAO;
    }

    @Autowired
    void setUserDao(@Qualifier("userDaoHibernate") UserDao userDao) {
        this.userDao = userDao;
    }

    public List<Message> getMessagesByUserId(Long id) {
        return messageDAO.getMessagesByUserId(id);
    }

    public User getUserByUserName(String name){
        return userDao.getUserByName(name);
    }


    public boolean checkUserNamePassword(User usr,String name,String password)
    {
        return usr.getName().equals(name) && usr.getPassword().equals(password);
    }
}
