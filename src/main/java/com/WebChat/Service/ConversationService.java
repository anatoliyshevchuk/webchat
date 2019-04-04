package com.WebChat.Service;

import com.WebChat.DAO.ConversationDao;
import com.WebChat.Entity.Conversation;
import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConversationService {

    private ConversationDao conversationDao;

    @Autowired
    public ConversationService(ConversationDao conversationDao) {
        this.conversationDao = conversationDao;
    }

    //TODO:Refactor Conversation saving and get, do something more clearer
    public List<Conversation> getConversationListByUser(User user) {

        List<Conversation> list = conversationDao.getConversationsList(user);

        //Delete Conversation duplicate (where Conversation open from me and to me). Leave only conversation opened from My side and new for me
        List<Conversation> toRemove = new ArrayList<>();
        for (Conversation conv : list)
        {
            if(conv.getCurrentUser().getId().equals(user.getId()))
            {
                checkNewMessagesCount(conv); //Checking how many new message I receive in my opened conversation
                for(Conversation conv2 : list)
                {
                    if(conv2.getPartnerUser().getId().equals(conv.getCurrentUser().getId()) && conv2.getCurrentUser().getId().equals(conv.getPartnerUser().getId()))
                    {
                        if(conv.getCountNewMessages()==0 || conv.isActive())
                            toRemove.add(conv2);
                    }
                }
            }
        }
        list.removeAll(toRemove);
        toRemove.clear();
        //Remove not active conversations

        for(Conversation conv : list)
        {
            if (!conv.isActive())
            {
                toRemove.add(conv);
            }
        }
        list.removeAll(toRemove);
        return list;
    }

    public List<Message> getListMessage(int myId, int PartnerId) {
        return conversationDao.getListMessage(myId, PartnerId);
    }

    public Conversation saveOrUpdateConversation(User currentUser, User partnerUser) {
        Conversation conv = getConversation(currentUser,partnerUser);
        if(conv==null) {
            conv = new Conversation(currentUser, partnerUser, new Date(), true);
            conversationDao.save(conv);
        }
        else {
            conv.setActive(true);
            conversationDao.updateConversation(conv);
        }
        return conv;
    }

    public void updateConversation(Conversation conversation) {
        Conversation conv = getConversation(conversation.getCurrentUser(),conversation.getPartnerUser());
        conv.setActive(false);
        conversationDao.updateConversation(conv);
    }

    private Conversation getConversation(User usr1, User usr2) {
        return conversationDao.getConversation(usr1, usr2);
    }

    public void updateConversationDate(Conversation conversation, Date date) {
        conversation.setLastOpenedByCurrentUser(date);
        conversationDao.updateConversation(conversation);
    }

    private void checkNewMessagesCount(Conversation conv) {
        conv.setCountNewMessages(conversationDao.checkNewMessageCount(conv));
    }


}
