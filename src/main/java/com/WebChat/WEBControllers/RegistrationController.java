package com.WebChat.WEBControllers;

import com.WebChat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
@Scope("session")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/NewUserRegistration")
    private ModelAndView newUserRegistration(ModelAndView modelAndView, @RequestParam("loginName")String login, @RequestParam("loginPassword")String password, @RequestParam("e-mail")String email, @RequestParam("loginPassword2")String password2)
    {

        //TODO:Spring Validator
        modelAndView.setViewName("registration");
        boolean saveReady=true;
        if(userService.checkUserName(login)) {
            modelAndView.addObject("message","User already exist");
            saveReady=false;
        }
        if(!password.equals(password2)) {
            modelAndView.addObject("message","Passwords not match");
            saveReady=false;
        }

        if(saveReady) {
            userService.saveUser(login, password, email);
            modelAndView.addObject("message","Done!");
        }

        return modelAndView;
    }
}
