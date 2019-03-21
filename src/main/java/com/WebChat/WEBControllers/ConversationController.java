package com.WebChat.WEBControllers;

import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;
import com.WebChat.Service.MessageService;
import com.WebChat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@SessionAttributes("user")
@Scope("session")
public class ConversationController {

    //TODO:Thread safe variables or Scope==session will be enough or maybe use AtomicRef? dunno yet
    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    public ConversationController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }


    @RequestMapping(value="/sendMessage", method= RequestMethod.POST)
    private ModelAndView sendMessage(@RequestParam("toUser")String toUserName, @RequestParam("message")String message, @SessionAttribute("user") User fromUser)
    {
        ModelAndView modelAndView = new ModelAndView();
        User toUser =  userService.getUserByUserName(toUserName);

        messageService.sendMessage(message,fromUser,toUser);
        modelAndView.setViewName("");
        return modelAndView;
    }

    @RequestMapping(value = "/conversation/{name}",method = RequestMethod.GET)
    private ModelAndView conversation(@SessionAttribute("user") User thisUser, @PathVariable("name") String name, @ModelAttribute("conversationPartner") User conversationPartner)
    {
        System.out.println("I'M HERE 1");
        ModelAndView modelAndView = new ModelAndView("conversation/"+name);

        //TODO: make something more beautiful than 2 lists...
        System.out.println("I'M HERE 2");
        List<Message> list = messageService.getMessagesByUserId(thisUser.getId());
        System.out.println("I'M HERE 3");
        System.out.println(conversationPartner.getName() + "  " + conversationPartner.getId());
        list.addAll(messageService.getMessagesByUserId(conversationPartner.getId()));
        System.out.println("I'M HERE 4");
        Collections.sort(list);

        for (Message msg : list)
        {
            System.out.println(msg.getMessageFrom()+"   to:"+msg.getMessageToUser()+"   message:"+msg.getMessage());
        }
        modelAndView.addObject("messagelist",list);

        return modelAndView;
    }

    @RequestMapping("/startConversation")
    private ModelAndView startConversation(@PathVariable("conversationPartner") String partnerUser)
    {
        User conversationPartner = userService.getUserByUserName(partnerUser);
        System.out.println(conversationPartner);
        ModelAndView modelAndView = new ModelAndView("main");
        if(conversationPartner==null)
        {
            modelAndView.addObject("error","No such user found");
            return modelAndView;
        }
        modelAndView.addObject("conversationPartner",conversationPartner);
        modelAndView.setViewName("redirect:/conversation/"+conversationPartner.getName());
        return modelAndView;
    }
}
