package com.WebChat.WEBControllers;

import com.WebChat.DAO.ConversationDao;
import com.WebChat.Entity.User;
import com.WebChat.Service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
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
        modelAndView.addObject("conversationList",conversationService.getConversationListByUser(user));
        modelAndView.setViewName("main");
        return modelAndView;
    }
}
