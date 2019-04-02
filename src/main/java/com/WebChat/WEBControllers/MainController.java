package com.WebChat.WEBControllers;

import com.WebChat.Entity.Conversation;
import com.WebChat.Entity.User;
import com.WebChat.Service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@SessionAttributes(names = {"user","Conversations"})
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
        List<Conversation> list = conversationService.getConversationListByUser(user);

        //add to Session for less requests to db
        modelAndView.addObject("Conversations",list);
        modelAndView.setViewName("main");
        return modelAndView;
    }
}
