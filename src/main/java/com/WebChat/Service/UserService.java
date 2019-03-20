package com.WebChat.Service;
import com.WebChat.DAO.*;


import com.WebChat.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    private void setUserDao(@Qualifier("userDaoHibernate") UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserByUserName(String name){
        return userDao.getUserByName(name);
    }

    public boolean checkUserNamePassword(User usr,String name,String password) {
        return usr.getName().equals(name) & usr.getPassword().equals(password);
    }
}
