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
    private void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserByUserName(String name){
        return userDao.getUserByName(name);
    }

    public boolean checkUserNamePassword(User usr,String name,String password) {
        //TODO: Spring Security
        return usr.getName().equals(name) & usr.getPassword().equals(password);
    }

    public void saveUser(String name, String password, String email)
    {
        User newUser = new User();
        newUser.setName(name);
        newUser.setPassword(password);
        newUser.setEmail(email);

        userDao.saveUser(newUser);
    }

    public boolean checkUserName(String name){
        User usr;
        usr = userDao.getUserByName(name);
        if(usr==null)
            return false;
        return true;
    }
}
