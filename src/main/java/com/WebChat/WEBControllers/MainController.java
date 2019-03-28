package com.WebChat.WEBControllers;

import com.WebChat.Entity.User;
import com.WebChat.Service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@Controller
@SessionAttributes(names = {"user","Partners"})
@Scope("session")
public class MainController {

    private ConversationService conversationService;

    @Autowired
    public MainController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping("/")
    private String MainPage() {
             return "loginPage";
    }

    @RequestMapping("/menu")
    private ModelAndView menu(ModelAndView modelAndView, @SessionAttribute("user") User user)
    {
        Map<String,User> partnersMap = conversationService.getPartnersMapByUser(user);

        //add to Session for less requests to db
        modelAndView.addObject("Partners",partnersMap);
        modelAndView.setViewName("main");
        return modelAndView;
    }
}
