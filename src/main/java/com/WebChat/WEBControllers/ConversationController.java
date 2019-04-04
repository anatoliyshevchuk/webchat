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

import java.util.Date;
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
    private ModelAndView startConversation(@RequestParam ("conversationPartner") String name, ModelAndView modelAndView, @SessionAttribute("user") User thisUser, @SessionAttribute("Conversations") List<Conversation> conversationList) {

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
        conversationList.add(conversationService.saveOrUpdateConversation(thisUser,conversationPartner));

        modelAndView.addObject("Conversations",conversationList);
        modelAndView.setViewName("redirect:/showConversation/"+conversationPartner.getName());
        return modelAndView;
    }

    @RequestMapping(value="/sendMessage", method= RequestMethod.POST)
    private String sendMessage(@RequestParam("message")String message, @SessionAttribute("user") User thisUser,@RequestParam("sendto") String name, @SessionAttribute("Conversations") List<Conversation> conversationList) {
        //TODO:Refactor to conversations
        User conversationPartner = seekConversationInSession(name,conversationList).getPartnerUser();
        messageService.sendMessage(message,thisUser,conversationPartner);
        return "redirect:/showConversation/"+conversationPartner.getName();
    }


    @RequestMapping(value = "/showConversation/{name}",method = RequestMethod.GET)
    private ModelAndView conversation(@SessionAttribute("user") User thisUser, ModelAndView modelAndView, @PathVariable("name") String name, @SessionAttribute("Conversations") List<Conversation> conversationList) {
        Conversation conversation = seekConversationInSession(name,conversationList);
        User conversationPartner;
        if(conversation==null)
        {
            conversationPartner = userService.getUserByUserName(name);
            conversationService.saveOrUpdateConversation(thisUser, conversationPartner);
        }else
        {
            conversationService.updateConversationDate(conversation, new Date());
            conversationPartner=conversation.getPartnerUser();
        }
        List<Message> messageList = conversationService.getListMessage(thisUser.getId(), conversationPartner.getId());

        //TODO:change sort to Set with compare by dates?
        messageService.sortMessageByDate(messageList);

        modelAndView.addObject("messagelist",messageList);
        modelAndView.addObject("currentPartner",conversationPartner);
        modelAndView.setViewName("conversation");
        return modelAndView;
    }


    @RequestMapping(value = "/updateConversation/{name}",method = RequestMethod.GET)
    private ModelAndView updateConversation(@SessionAttribute("user") User thisUser, ModelAndView modelAndView, @PathVariable("name") String name, @SessionAttribute("Conversations") List<Conversation> conversationList)
    {
        Conversation convToDelete=seekConversationInSession(name,conversationList);
        conversationService.updateConversation(convToDelete);
        conversationList.remove(convToDelete);
        modelAndView.addObject("Conversations",conversationList);
        modelAndView.setViewName("redirect:/menu");
        return modelAndView;
    }

    private Conversation seekConversationInSession(String name, List<Conversation> conversationList) {
        Conversation foundConv=null;
        for (Conversation conv : conversationList)
        {
            if(conv.getPartnerUser().getName().equals(name))
                foundConv = conv;
        }
        return foundConv;
    }
}
