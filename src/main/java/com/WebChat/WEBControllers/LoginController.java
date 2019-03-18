package com.WebChat.WEBControllers;

import com.WebChat.Entity.User;
import com.WebChat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private ModelAndView login(@RequestParam("loginName")String login, @RequestParam("loginPassword")String password){
        User user = userService.getUserByUserName(login);
        ModelAndView model = new ModelAndView();
        //TODO: Check user by Spring Security
        if(user.getPassword().equals(password))
        {
            model.setViewName("main");
            model.addObject("user",user);
        }
        else
        {
            model.setViewName("errorLogin");
        }
        return model;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
