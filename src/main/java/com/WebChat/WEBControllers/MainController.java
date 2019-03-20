package com.WebChat.WEBControllers;

import com.WebChat.Entity.User;
import com.WebChat.Service.MessageService;
import com.WebChat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class MainController {

    private UserService userService;
    private MessageService messageService;

    @Autowired
    public MainController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/")
    private String MainPage() {
             return "loginPage";
    }

    @RequestMapping(value="/sendMessage", method= RequestMethod.POST)
    private ModelAndView sendMessage(@RequestParam("toUser")String toUserName, @RequestParam("message")String message, @SessionAttribute("user") User fromUser)
    {
        ModelAndView modelAndView = new ModelAndView("main");
        User toUser =  userService.getUserByUserName(toUserName);
        if(toUser==null)
        {
            modelAndView.addObject("message",message);
            modelAndView.addObject("error","No such user found");
            modelAndView.setViewName("main");
            return modelAndView;
        }
        messageService.sendMessage(message,fromUser,toUser);

        return modelAndView;
    }

    @RequestMapping(value = "/logoff")
    private String logout(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
