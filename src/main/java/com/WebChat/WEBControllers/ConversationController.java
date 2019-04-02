package com.WebChat.WEBControllers;

import com.WebChat.Entity.Conversation;
import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;
import com.WebChat.Service.ConversationService;
import com.WebChat.Service.MessageService;
import com.WebChat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes(names={"user","Conversations"})
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
    private ModelAndView startConversation(@RequestParam ("conversationPartner") String name, ModelAndView modelAndView, @SessionAttribute("user") User thisUser, @SessionAttribute("Conversations") List<Conversation> partnersList) {

        User conversationPartner = userService.getUserByUserName(name);

        modelAndView.setViewName("main");
        if(conversationPartner==null) {
            modelAndView.addObject("error","No such user found");
            return modelAndView;
        }
        if(conversationPartner.getName().equals(thisUser.getName())) {
            modelAndView.addObject("error","Can't start conversation with yourself");
            return modelAndView;
        }
        //Create new Conversation and save to db and to Session
        partnersList.add(conversationService.saveAsConversation(thisUser,conversationPartner));

        modelAndView.addObject("Conversations",partnersList);

        modelAndView.setViewName("redirect:/showConversation/"+conversationPartner.getName());
        return modelAndView;
    }


    @RequestMapping(value="/sendMessage", method= RequestMethod.POST)
    private String sendMessage(@RequestParam("message")String message, @SessionAttribute("user") User thisUser,@RequestParam("sendto") String name, @SessionAttribute("Conversations") List<Conversation> partnersList) {
        //TODO:Refactor to conversations
        User conversationPartner = seekConversationInSession(name,partnersList).getPartnerUser();

        messageService.sendMessage(message,thisUser,conversationPartner);
        return "redirect:/showConversation/"+conversationPartner.getName();
    }


    @RequestMapping(value = "/showConversation/{name}",method = RequestMethod.GET)
    private ModelAndView conversation(@SessionAttribute("user") User thisUser, ModelAndView modelAndView, @PathVariable("name") String name, @SessionAttribute("Conversations") List<Conversation> partnersList) {

        Conversation conversation = seekConversationInSession(name,partnersList);
        User conversationPartner=null;

        if(conversation==null)
        {
            conversationPartner = userService.getUserByUserName(name);
            conversationService.saveAsConversation(thisUser, conversationPartner);
        }
        List<Message> messageList = conversationService.getListMessage(thisUser.getId(), conversationPartner.getId());

        //TODO:change sort to set with compare by date?
        messageService.sortMessageByDate(messageList);

        modelAndView.addObject("messagelist",messageList);
        modelAndView.addObject("currentPartner",conversationPartner);
        modelAndView.setViewName("conversation");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteConversation/{name}",method = RequestMethod.GET)
    private ModelAndView deleteConversation(@SessionAttribute("user") User thisUser, ModelAndView modelAndView, @PathVariable("name") String name, @SessionAttribute("Conversations") List<Conversation> partnersList)
    {
        Conversation convToDelete=seekConversationInSession(name,partnersList);
        conversationService.deleteConversation(convToDelete.getCurrentUser(),convToDelete.getPartnerUser());
        partnersList.remove(convToDelete);
        modelAndView.addObject("Conversations",partnersList);
        modelAndView.setViewName("redirect:/menu");
        return modelAndView;
    }

    private Conversation seekConversationInSession(String name, List<Conversation> partnersList) {
        Conversation foundConv=null;
        for (Conversation conv : partnersList)
        {
            if(conv.getPartnerUser().getName().equals(name))
                foundConv = conv;
        }
        return foundConv;
    }

}
