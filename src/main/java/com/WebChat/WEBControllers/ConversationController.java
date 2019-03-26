package com.WebChat.WEBControllers;

import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;
import com.WebChat.Service.ConversationService;
import com.WebChat.Service.MessageService;
import com.WebChat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes(names={"user","Partner"})
@Scope("session")
public class ConversationController {

    //TODO:Thread safe variables or Scope==session will be enough or maybe use AtomicRef? dunno yet
    private final UserService userService;
    private final MessageService messageService;
    private final ConversationService conversationService;

    @Autowired
    public ConversationController(UserService userService, MessageService messageService, ConversationService conversationService) {
        this.userService = userService;
        this.messageService = messageService;
        this.conversationService = conversationService;
    }


    @RequestMapping("/startConversation")
    private ModelAndView startConversation(@RequestParam ("conversationPartner") String partnerUser, Model model, @SessionAttribute("user") User thisUser)
    {
        User conversationPartner = userService.getUserByUserName(partnerUser);
        ModelAndView modelAndView = new ModelAndView("main");
        if(conversationPartner==null)
        {
            modelAndView.addObject("error","No such user found");
            return modelAndView;
        }
        if(conversationPartner.getName().equals(thisUser.getName()))
        {
            modelAndView.addObject("error","Can't start conversation with yourself");
            return modelAndView;
        }

        model.addAttribute("Partner",conversationPartner);
        modelAndView.setViewName("redirect:/showConversation");
        return modelAndView;
    }


    @RequestMapping(value="/sendMessage", method= RequestMethod.POST)
    private String sendMessage(@RequestParam("message")String message, @SessionAttribute("user") User thisUser,@SessionAttribute("Partner") User conversationPartner)
    {
        messageService.sendMessage(message,thisUser,conversationPartner);
        return "redirect:/showConversation";
    }


    @RequestMapping(value = "/showConversation",method = RequestMethod.GET)
    private ModelAndView conversation(@SessionAttribute("user") User thisUser, @SessionAttribute("Partner") User conversationPartner, ModelAndView modelAndView)
    {
        //TODO:TreeSet with Data compare?
        List<Message> list = conversationService.getConversation(thisUser.getId(), conversationPartner.getId());
        messageService.sortMessageByDate(list);
        modelAndView.addObject("messagelist",list);
        modelAndView.setViewName("conversation");
        return modelAndView;
    }
}
