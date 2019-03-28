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
import java.util.Map;

@Controller
@SessionAttributes(names={"user","Partners"})
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
    private ModelAndView startConversation(@RequestParam ("conversationPartner") String name, ModelAndView modelAndView, @SessionAttribute("user") User thisUser, @SessionAttribute("Partners") Map partnersMap) {

        User conversationPartner = getPartnerFromSessionOrDb(name,partnersMap);
        modelAndView.setViewName("main");
        if(conversationPartner==null) {
            modelAndView.addObject("error","No such user found");
            return modelAndView;
        }
        if(conversationPartner.getName().equals(thisUser.getName())) {
            modelAndView.addObject("error","Can't start conversation with yourself");
            return modelAndView;
        }
        //Create new Conversation and save to db
        Conversation conversation = new Conversation(thisUser,conversationPartner);
        conversationService.save(conversation);

        //Add to Session for make less request to db
        partnersMap.put(conversationPartner.getName(),conversationPartner);
        modelAndView.addObject("Partners",partnersMap);

        modelAndView.setViewName("redirect:/showConversation/"+conversationPartner.getName());
        return modelAndView;
    }


    @RequestMapping(value="/sendMessage", method= RequestMethod.POST)
    private String sendMessage(@RequestParam("message")String message, @SessionAttribute("user") User thisUser,@RequestParam("sendto") String name, @SessionAttribute("Partners") Map partnersMap) {
        User conversationPartner = getPartnerFromSessionOrDb(name,partnersMap);

        messageService.sendMessage(message,thisUser,conversationPartner);
        return "redirect:/showConversation/"+conversationPartner.getName();
    }


    @RequestMapping(value = "/showConversation/{name}",method = RequestMethod.GET)
    private ModelAndView conversation(@SessionAttribute("user") User thisUser, ModelAndView modelAndView, @PathVariable("name") String name, @SessionAttribute("Partners") Map partnersMap) {
        User conversationPartner = getPartnerFromSessionOrDb(name,partnersMap);

        List<Message> messageList = conversationService.getListMessage(thisUser.getId(), conversationPartner.getId());
        messageService.sortMessageByDate(messageList);

        modelAndView.addObject("messagelist",messageList);
        modelAndView.addObject("currentPartner",conversationPartner);
        modelAndView.setViewName("conversation");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteConversation/{name}",method = RequestMethod.GET)
    private ModelAndView deleteConversation(@SessionAttribute("user") User thisUser, ModelAndView modelAndView, @PathVariable("name") String name, @SessionAttribute("Partners") Map<String,User> partnersMap)
    {
        Conversation conv = new Conversation(thisUser,partnersMap.get(name));
        conversationService.deleteConversation(thisUser,partnersMap.get(name));
        partnersMap.remove(name);
        modelAndView.addObject("Partners",partnersMap);
        modelAndView.setViewName("redirect:/menu");
        return modelAndView;
    }

    private User getPartnerFromSessionOrDb(String name, Map<String,User> partnersMap) {
        User conversationPartner;
        conversationPartner = partnersMap.get(name);
        if(conversationPartner==null) {
            conversationPartner = userService.getUserByUserName(name);
        }
        return conversationPartner;
    }

}
