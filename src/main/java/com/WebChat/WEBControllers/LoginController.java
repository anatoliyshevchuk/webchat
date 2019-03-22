package com.WebChat.WEBControllers;

import com.WebChat.Entity.User;
import com.WebChat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@SessionAttributes("user")
@Scope("session")
public class LoginController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private ModelAndView login(@RequestParam("loginName")String login, @RequestParam("loginPassword")String password){
        User user = userService.getUserByUserName(login);
        ModelAndView model = new ModelAndView();


        // TODO: Spring Security

        if(user==null)
        {
            model.setViewName("errorLogin");
            return model;
        }
        if(userService.checkUserNamePassword(user,login,password))
        {
            model.setViewName("redirect:/menu");
            model.addObject("user",user);
        }
        else
        {
            model.setViewName("errorLogin");
        }
        return model;
    }

    @RequestMapping(value = "/logoff")
    private String logout(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

}
